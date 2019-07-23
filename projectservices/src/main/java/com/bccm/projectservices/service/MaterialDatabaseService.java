package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.MaterialDatabaseDetailInterface;
import com.bccm.projectservices.Interface.MaterialDatabaseInterface;
import com.bccm.projectservices.entity.MaterialDatabase;
import com.bccm.projectservices.entity.MaterialDatabaseDetail;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class MaterialDatabaseService {

    @Autowired
    MaterialDatabaseInterface materialDatabaseInterface;

    @Autowired
    private MaterialDatabaseDetailInterface materialDatabaseDetailInterface;

    private static final Logger logger = LoggerFactory.getLogger(MaterialDatabaseService.class);

    public Page<MaterialDatabase> getList(PageRequest pageRequest, String type, String flag, String name, String feature, String msupplier) {
        return materialDatabaseInterface.findAll((Root<MaterialDatabase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
                    Path<String> typePath = root.get("type");
                    Path<String> flagPath = root.get("flag");
                    Path<String> namePath = root.get("name");
                    Path<String> featurePath = root.get("feature");
                    Path<String> msupplierPath = root.get("msupplier");
                    List<Predicate> predicates = new ArrayList<>();
                    if (StringUtils.isNotEmpty(type)) {
                        predicates.add(criteriaBuilder.equal(typePath, type));
                    }
                    if (StringUtils.isNotEmpty(flag)) {
                        predicates.add(criteriaBuilder.like(flagPath, "%" + flag + "%"));
                    }
                    if (StringUtils.isNotEmpty(name)) {
                        predicates.add(criteriaBuilder.like(namePath, "%" + name + "%"));
                    }
                    if (StringUtils.isNotEmpty(feature)) {
                        predicates.add(criteriaBuilder.like(featurePath, "%" + feature + "%"));
                    }
                    if (StringUtils.isNotEmpty(msupplier)) {
                        predicates.add(criteriaBuilder.like(msupplierPath, "%" + msupplier + "%"));
                    }
                    predicates.add(criteriaBuilder.notEqual(root.get("isdelete"), "1"));
                    Predicate[] predicates1 = new Predicate[predicates.size()];
                    return criteriaBuilder.and(predicates.toArray(predicates1));
                }
                , pageRequest);
    }

    @Transactional
    public void deleteMaterialRows(List<MaterialDatabase> materialDatabases) {
        try {
            materialDatabaseInterface.deleteAll(materialDatabases);
            materialDatabaseInterface.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    //删除历史数据
    @Transactional
    public void deleteDetailRows(List<MaterialDatabaseDetail> details) {
        try {
            materialDatabaseDetailInterface.deleteAll(details);
            materialDatabaseDetailInterface.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    //保存单个材料信息
    public void saveMaterial(MaterialDatabase materialDatabase) throws Exception {
        materialDatabaseInterface.save(materialDatabase);
    }

    /**
     * 保存excel上传信息，以单价判断超阈值的材料不存储
     *
     * @param materialDatabases 材料信息
     * @param comparePercent    阈值
     */
    @Transactional
    public String saveMaterials(List<MaterialDatabase> materialDatabases, int comparePercent) {
        //保存材料历史信息
        List<MaterialDatabaseDetail> mDetails = new ArrayList<>();
        //保存主表信息
        List<MaterialDatabase> mMaterials = new ArrayList<>();
        //用来比较准备存到主表的材料信息，flag为键值
        Map<String, MaterialDatabase> updateMMaterials = new HashMap<>();
        //原主表数据，按flag为键值，用来对比excel表中数据的阈值
        Map<String, MaterialDatabase> preMMaterials = new HashMap<>();
        //返回数据超阈值数据信息，已flag和name。超阈值的材料不存储
        StringBuilder returnMsg = new StringBuilder();
        try {
            for (MaterialDatabase excelM : materialDatabases) {
                String flag = excelM.getFlag();
                if (excelM.getMdate() == null) {
                    excelM.setMdate(new Timestamp(System.currentTimeMillis()));
                }
                Timestamp excelMDate = excelM.getMdate();
                MaterialDatabase materialDatabase = null;
                //将同flag的主表中的数据查出，并放入preMMaterials
                if (!preMMaterials.containsKey(flag)) {
                    materialDatabase = materialDatabaseInterface.findByFlag(flag);
                    preMMaterials.put(flag, materialDatabase);
                    if (!updateMMaterials.containsKey(flag) && materialDatabase != null) {
                        updateMMaterials.put(flag, materialDatabase);
                    }
                } else {
                    materialDatabase = preMMaterials.get(flag);
                }

                if (materialDatabase != null) {
                    int comparePrice = ((excelM.getPrice().subtract(materialDatabase.getPrice()))
                            .multiply(new BigDecimal(100)).divide(materialDatabase.getPrice(),1,BigDecimal.ROUND_HALF_UP))
                            .intValue();
                    if (comparePrice > comparePercent) {
                        returnMsg.append("材料名称为“" + excelM.getName() + "”" +
                                "时间为“"+excelM.getMdate()+"”的单价超出设定阈值，请修改后再上传<br/>");
                    } else {
                        MaterialDatabase updateMMaterialDetail = updateMMaterials.get(flag);
                        Timestamp date = updateMMaterialDetail.getMdate();
                        if (excelMDate.compareTo(date) > 0) {
                            //将excelM的主键设为主表中现有的主键，实现保存时直接更新
                            excelM.setGuid(updateMMaterialDetail.getGuid());
                            excelM.setInputdate(LocalDateTime.now());
                            excelM.setIsdelete(0);

                            add2MDetail(updateMMaterialDetail, mDetails);
                            updateMMaterials.put(flag, excelM);
                        } else {
                            if (excelMDate.compareTo(date) == 0) {
                                excelMDate.setTime(excelMDate.getTime() - 24 * 60 * 60 * 1000);
                                excelM.setMdate(excelMDate);
                            }
                            add2MDetail(excelM, mDetails);
                        }
                    }
                } else {
                    excelM.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
                    excelM.setInputdate(LocalDateTime.now());
                    excelM.setIsdelete(0);

                    if (!updateMMaterials.containsKey(flag)) {
                        updateMMaterials.put(flag, excelM);
                    } else {
                        MaterialDatabase updateMMaterialDetail = updateMMaterials.get(flag);
                        Timestamp date = updateMMaterialDetail.getMdate();
                        if (excelMDate.compareTo(date) > 0) {
                            add2MDetail(updateMMaterialDetail, mDetails);
                            updateMMaterials.put(flag, excelM);
                        } else {
                            if (excelMDate.compareTo(date) == 0) {
                                excelMDate.setTime(excelMDate.getTime() - 24 * 60 * 60 * 1000);
                                excelM.setMdate(excelMDate);
                            }
                            add2MDetail(excelM, mDetails);
                        }
                    }
                }
            }
            Set<String> keys = updateMMaterials.keySet();
            for (String key : keys) {
                mMaterials.add(updateMMaterials.get(key));
            }
            materialDatabaseInterface.saveAll(mMaterials);
            materialDatabaseDetailInterface.saveAll(mDetails);
            materialDatabaseInterface.flush();
            materialDatabaseDetailInterface.flush();
            return returnMsg.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return e.getMessage();
        }
    }

    private void add2MDetail(MaterialDatabase excelM, List<MaterialDatabaseDetail> mDetails) {
        MaterialDatabaseDetail materialDatabaseDetail = new MaterialDatabaseDetail();
        material2Detail(excelM, materialDatabaseDetail);
        mDetails.add(materialDatabaseDetail);
    }

    //查找材料所有类型
    public List<String> findAllType() {
        return materialDatabaseInterface.findAllType();
    }

    //依材料guid查找明细表中的价格明细
    public List<MaterialDatabaseDetail> findAllByFlag(String flag) {
        return materialDatabaseDetailInterface.findAllByFlag(flag);
    }

    //保存材料的价格明细
    public void saveAll(List<MaterialDatabaseDetail> mDetails) {
        materialDatabaseDetailInterface.saveAll(mDetails);
        materialDatabaseDetailInterface.flush();
    }

    private MaterialDatabaseDetail material2Detail(MaterialDatabase materail, MaterialDatabaseDetail mDetail) {
        mDetail.setFlag(materail.getFlag());
        mDetail.setPrice(materail.getPrice());
        mDetail.setPretaxprice(materail.getPretaxprice());
        mDetail.setPlocation(materail.getPlocation());
        mDetail.setMsupplier(materail.getMsupplier());
        mDetail.setMdate(materail.getMdate());
        mDetail.setPricefrom(materail.getPricefrom());
        mDetail.setTaxrate(materail.getTaxrate());
        mDetail.setMlocation(materail.getMlocation());
        mDetail.setMsuppliertel(materail.getMsuppliertel());
        mDetail.setMcomponent(materail.getMcomponent());
        mDetail.setRemark(materail.getRemark());
        mDetail.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
        return mDetail;
    }

}
