package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.*;
import com.bccm.projectservices.entity.*;
import com.bccm.projectservices.entity.VO.PriceDetailListEntityVo;
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
import java.util.*;

@Service
public class PriceDetailListService {

    public static final Logger logger = LoggerFactory.getLogger(PriceDetailListService.class);
    @Autowired
    private PriceDetailListInterface priceDetailListInterface;

    @Autowired
    private ListProjectEditInterface listProjectEditInterface;

    @Autowired
    private MaterialDatabaseInterface materialDatabaseInterface;

    @Autowired
    private BagClearDatabaseInterface bagClearDatabaseInterface;

    @Autowired
    private ProjectmanageInformationMainInterface projectmanageInformationMainInterface;

    @Autowired
    private BagComponentInterface bagComponentInterface;

    @Autowired
    private PriceDetailListHistoryInterface priceDetailListHistoryInterface;
    //更新费用汇总中的措施费和税金
    @Autowired
    private ProjectMeasureCostService projectMeasureCostService;

    static Boolean isBagClearExist=false;

    static List<ListProjectEdit> listProjectEditsList = new ArrayList<>();


    /**
     * 依项目id集合和类型（清包，材料）合并同类项返回数据
     *
     * @param projectids 项目ids集合
     * @param type       类型
     * @return 清单列表
     */
    @Transactional
    public List<PriceDetailListEntity> findAllByPrjIdAndType(List<String> projectids, String type) {
        List<PriceDetailListEntity> pListReturn = new ArrayList<>();
        for (String projectid : projectids) {
            pListReturn.addAll(priceDetailListInterface.findAllByProjectidAndType(projectid, type));
        }
        return pListReturn;
    }

    /**
     * 更新材料汇总、价格汇总功能模块信息
     *
     * @param pVo         PriceDetailListEntityVo
     * @param priceUpdate 是否更新价格
     */
    @Transactional
    public void updatePricesByGuids(PriceDetailListEntityVo pVo, int priceUpdate) {
        List<String> guids = pVo.getGuids();
        List<PriceDetailListEntity> pDetails = new ArrayList<>();
        for (String guid : guids) {
            PriceDetailListEntity pDetail = priceDetailListInterface.findById(guid).orElse(new PriceDetailListEntity());
            transformPVo2P(pDetail, pVo);
            pDetails.add(pDetail);
//            System.err.println("PriceDetailListService:" + pDetail);
//            System.err.println(pVo);
        }
        //如果涉及价格调整，则调用saveAndHandel更新项目所有涉及的价格
        if (priceUpdate == 0) {
            priceDetailListInterface.saveAll(pDetails);
        } else if (priceUpdate == 1) {
            for (PriceDetailListEntity p : pDetails) {
                saveAndHandel(p);
            }
        } else  {

        }
        priceDetailListInterface.flush();
    }
    //材料汇总、价格汇总功能模块中用于转换对象用
    private void transformPVo2P(PriceDetailListEntity p, PriceDetailListEntityVo pVo) {
        p.setName(pVo.getName());
        p.setFeature(pVo.getFeature());
        p.setUnit(pVo.getUnit());
        p.setPrice(pVo.getPrice());
        //TODO
//        p.setTotal(pVo.getTotal());
        p.setTaxrate(pVo.getTaxrate());
        p.setRemark(pVo.getRemark());
        p.setWorkcontent(pVo.getWorkcontent());
        p.setCountruler(pVo.getCountruler());
        p.setPricefrom(pVo.getPricefrom());
    }

    /**
     * 获取某节点下所有的子节点的清单列表
     */
    public List<PriceDetailListEntity> getAllBySelected(String id) {
        List<PriceDetailListEntity> priceDetailListEntitieList = new ArrayList<>();
        listProjectEditsList = new ArrayList<>();
        List<ListProjectEdit> listProjectEditList = listProjectEditInterface.findAll();
        List<ListProjectEdit> allSonProjectEditLists = findAllSon(listProjectEditList, id);
        if (allSonProjectEditLists != null && allSonProjectEditLists.size() == 0) {
            allSonProjectEditLists = listProjectEditInterface.findAllByGuid(id);
        }
        for (ListProjectEdit allSonProjectEdit : allSonProjectEditLists) {
            priceDetailListEntitieList.addAll(priceDetailListInterface.findAllByProjectidOrderBySortidAscSortidsDesc(allSonProjectEdit.getGuid()));
        }
        return priceDetailListEntitieList;
    }

    public void saveAndHandel(PriceDetailListEntity priceDetailList) {
        String rootProjectid=priceDetailList.getRootprojectid();
        String flag=priceDetailList.getFlag();
        priceDetailList = updateToNew(priceDetailList);
        String type = priceDetailList.getType();
        List<PriceDetailListEntity> AllSonList = new ArrayList<PriceDetailListEntity>();
        PriceDetailListEntity priceDetailListEntitys = null;
        Double parentAmount = null;
        ListProjectEdit listProjectEdit = listProjectEditInterface.getOne(priceDetailList.getProjectid());

        if (("清单").equals(type)) {
            priceDetailListEntitys=priceDetailList;
            priceDetailListInterface.save(priceDetailList);
            AllSonList = priceDetailListInterface.findAllByParentid(priceDetailList.getGuid());
            parentAmount = priceDetailList.getProjectamount();

        } else {
//计算总价后存入数据库
            BigDecimal priceTotal = priceDetailList.getPrice() == null ? BigDecimal.valueOf(0) : priceDetailList.getPrice().multiply(BigDecimal.valueOf(priceDetailList.getProjectamount() == null ? 0 : priceDetailList.getProjectamount()));
            priceDetailList.setTotal(priceTotal);
//把关联的材料同步加进去
            priceDetailListEntitys = priceDetailListInterface.findByGuid(priceDetailList.getParentid());
            if (priceDetailListEntitys != null) {
                parentAmount = priceDetailListEntitys.getProjectamount();
            }
            if(("清包").equals(type))
            {
                if("行业级清包".equals(priceDetailList.getState()))
                {
//                    获取相关材料信息
                    BagClearDatabase materialDatabase=bagClearDatabaseInterface.findByFlag(flag);
                    String materialId=materialDatabase.getRelatematerialid();
                    String materialMainId=materialDatabase.getRelatematerialid2();
                    MaterialDatabase materialDatabase1=materialDatabaseInterface.findByFlag(materialId);
                    MaterialDatabase materialDatabase2=materialDatabaseInterface.findByFlag(materialMainId);
                    if(materialDatabase1!=null)
                    {
                        dataTransform(materialDatabase1,priceDetailList);
                    }
                    if(materialDatabase2!=null)
                    {
                        dataTransform(materialDatabase2,priceDetailList);
                    }

                    List<BagComponentsEntity> bagComponentsEntities=bagComponentInterface.findAllByRelatepackid(flag);
                    BigDecimal personCost=new BigDecimal(0);
                    BigDecimal machineCost=new BigDecimal(0);
                    BigDecimal materialCost=new BigDecimal(0);
                    for(BagComponentsEntity bagComponents:bagComponentsEntities)
                    {
                        Double packageAmountOri=bagComponents.getAmount();
                        BigDecimal packageAmount=new BigDecimal(0);
                        if(null!=packageAmountOri)
                        {
                            packageAmount=new BigDecimal(packageAmountOri);
                        }
                        if("人".equals(bagComponents.getType()))
                        {
                            BigDecimal personPrice=bagComponents.getPrice()==null?new BigDecimal(0):bagComponents.getPrice();
                            personCost=personCost.add(personPrice.multiply(packageAmount));
                        }else if("材".equals(bagComponents.getType()))
                        {
                            materialCost=materialCost.add(bagComponents.getPrice().multiply(packageAmount));
                        }else if("机".equals(bagComponents.getType()))
                        {
                            machineCost=machineCost.add(bagComponents.getPrice().multiply(packageAmount));
                        }
                    }
                    Double projectAmount=priceDetailList.getProjectamount();
                    if(projectAmount!=null)
                    {
                        personCost=personCost.multiply(BigDecimal.valueOf(projectAmount));
                        machineCost=machineCost.multiply(BigDecimal.valueOf(projectAmount));
                        materialCost=materialCost.multiply(BigDecimal.valueOf(projectAmount));
                    }else
                        {
                            personCost=new BigDecimal("0");
                            machineCost=new BigDecimal("0");
                            materialCost=new BigDecimal("0");
                        }
                    priceDetailList.setPersoncost(personCost);
                    priceDetailList.setMachinecost(machineCost);
                    priceDetailList.setMaterialcost(materialCost);
                }else if("项目级清包".equals(priceDetailList.getType()))
                {
                    
                }
            }
            priceDetailListInterface.save(priceDetailList);
            //修改为只保存价格修改
//            priceDetailListHistoryInterface.save(copyPriceDetail(priceDetailList));
            AllSonList = priceDetailListInterface.findAllByParentid(priceDetailList.getParentid());
        }

//        清单总价
        BigDecimal ProjectTotal = new BigDecimal(0);
//        清单材料单价
        BigDecimal ProjectMaterialPrice = new BigDecimal(0);
//        清单材料总价
        BigDecimal ProjectMaterialTotal = new BigDecimal(0);
//        清单单价
        BigDecimal ProjectPrice = new BigDecimal(0);
//        清单清包单价
        BigDecimal ProjectBagClearPrice = new BigDecimal(0);
//        清单清包总价
        BigDecimal ProjectBagClearTotal = new BigDecimal(0);
//        清单下人总和
        BigDecimal PackagePersonTotal=new BigDecimal(0);
//        清单下材总和
        BigDecimal PackageMaterialTotal=new BigDecimal(0);
//        清单下机器总和
        BigDecimal PackageMachineTotal=new BigDecimal(0);
        if(AllSonList!=null&&AllSonList.size()>0)
        {
            for (PriceDetailListEntity sonDetail : AllSonList) {
                ProjectTotal = ProjectTotal.add(sonDetail.getTotal()==null?new BigDecimal(0):sonDetail.getTotal());
                if (("清包").equals(sonDetail.getType())) {
                    ProjectBagClearTotal = ProjectBagClearTotal.add(sonDetail.getTotal()==null?new BigDecimal(0):sonDetail.getTotal());
                    PackagePersonTotal=PackagePersonTotal.add(sonDetail.getPersoncost()==null?new BigDecimal(0):sonDetail.getPersoncost());
                    PackageMaterialTotal=PackageMaterialTotal.add(sonDetail.getMaterialcost()==null?new BigDecimal(0):sonDetail.getMaterialcost());
                    PackageMachineTotal=PackageMachineTotal.add(sonDetail.getMachinecost()==null?new BigDecimal(0):sonDetail.getMachinecost());
                } else if (("材料").equals(sonDetail.getType())) {
                    ProjectMaterialTotal = ProjectMaterialTotal.add(sonDetail.getTotal()==null?new BigDecimal(0):sonDetail.getTotal());
                }
            }
        }
        if (parentAmount != null) {
            ProjectMaterialPrice = ProjectMaterialTotal.divide(BigDecimal.valueOf(parentAmount == null ? 0 : parentAmount), 2, BigDecimal.ROUND_HALF_UP);
            ProjectBagClearPrice = ProjectBagClearTotal.divide(BigDecimal.valueOf(parentAmount == null ? 0 : parentAmount), 2, BigDecimal.ROUND_HALF_UP);
            ProjectPrice = ProjectTotal.divide(BigDecimal.valueOf(parentAmount == null ? 0 : parentAmount), 2, BigDecimal.ROUND_HALF_UP);
        }
        //获取子节点的合价
        if (("清单").equals(type)) {
            priceDetailList.setMaterialprice(ProjectMaterialPrice);
            priceDetailList.setBagclearprice(ProjectBagClearPrice);
            priceDetailList.setPrice(ProjectPrice);
            priceDetailList.setTotal(ProjectTotal);
            priceDetailList.setPersoncost(PackagePersonTotal);
            priceDetailList.setMaterialcost(PackageMaterialTotal);
            priceDetailList.setMachinecost(PackageMachineTotal);
            priceDetailListInterface.save(priceDetailList);
        } else {
            priceDetailListEntitys.setMaterialprice(ProjectMaterialPrice);
            priceDetailListEntitys.setBagclearprice(ProjectBagClearPrice);
            priceDetailListEntitys.setPrice(ProjectPrice);
            priceDetailListEntitys.setTotal(ProjectTotal);
            priceDetailListEntitys.setPersoncost(PackagePersonTotal);
            priceDetailListEntitys.setMaterialcost(PackageMaterialTotal);
            priceDetailListEntitys.setMachinecost(PackageMachineTotal);
            priceDetailListInterface.save(priceDetailListEntitys);

        }
        BigDecimal projectTotal = new BigDecimal(0);

        List<PriceDetailListEntity> allDetail = priceDetailListInterface.findAllByProjectidOrderBySortidAscSortidsDesc(priceDetailList.getProjectid());
        for (PriceDetailListEntity priceDetailListEntity : allDetail) {
            if ("清单".equals(priceDetailListEntity.getType())) {
                projectTotal = projectTotal.add(priceDetailListEntity.getTotal() == null ? new BigDecimal(0) : priceDetailListEntity.getTotal());
            }
        }
        listProjectEdit.setAmount(projectTotal);
        listProjectEditInterface.save(listProjectEdit);
        if("0".equals(listProjectEdit.getParentid()))
        {
            ProjectmanageInformationMainEntity projectmanageInformationMainEntity=projectmanageInformationMainInterface.findByGuid(listProjectEdit.getProjectid());
            projectmanageInformationMainEntity.setMoney(listProjectEdit.getAmount().toString());
            projectmanageInformationMainInterface.save(projectmanageInformationMainEntity);
        }
//        更新结构信息
        while (listProjectEdit != null && !("0").equals(listProjectEdit.getParentid())) {
            BigDecimal sonTotal = new BigDecimal(0);
            List<ListProjectEdit> allProject = listProjectEditInterface.findAllByParentid(listProjectEdit.getParentid());
            for (ListProjectEdit listProjectEdit1 : allProject)
            {
                sonTotal = sonTotal.add(listProjectEdit1.getAmount()==null?new BigDecimal(0):listProjectEdit1.getAmount());
            }
            listProjectEdit = listProjectEditInterface.getOne(listProjectEdit.getParentid());
            listProjectEdit.setAmount(sonTotal);
            if("0".equals(listProjectEdit.getParentid()))
            {
                ProjectmanageInformationMainEntity projectmanageInformationMainEntity=projectmanageInformationMainInterface.findByGuid(listProjectEdit.getProjectid());
                projectmanageInformationMainEntity.setMoney(sonTotal.toString());
                projectmanageInformationMainInterface.save(projectmanageInformationMainEntity);
            }
            listProjectEditInterface.save(listProjectEdit);
        }
        //根项目分部分项费用更新后更新费用汇总中的措施费和税金
        projectMeasureCostService.updateAllCost(priceDetailList.getProjectid());
    }


    public List<ListProjectEdit> findAllSon(List<ListProjectEdit> listProjectEdits, String id) {
        for (ListProjectEdit listProjectEdit : listProjectEdits) {
            if (listProjectEdit.getParentid() != null && listProjectEdit.getParentid().equals(id)) {
                findAllSon(listProjectEdits, listProjectEdit.getGuid());
                listProjectEditsList.add(listProjectEdit);
            }
        }
        return listProjectEditsList;
    }


    public PriceDetailListEntity updateToNew(PriceDetailListEntity listProjectEdit) {
        PriceDetailListEntity priceDetailListEntity = priceDetailListInterface.findByGuid(listProjectEdit.getGuid());
        Integer sortid=priceDetailListInterface.findMaxSortid(listProjectEdit.getProjectid(),listProjectEdit.getParentid());
        isBagClearExist=false;
        if (priceDetailListEntity != null) {
            isBagClearExist=true;
            priceDetailListEntity.setFlag(listProjectEdit.getFlag());
            priceDetailListEntity.setProjectamount(listProjectEdit.getProjectamount());
            priceDetailListEntity.setPrice(listProjectEdit.getPrice());
            priceDetailListEntity.setProjectamount(listProjectEdit.getProjectamount());
            priceDetailListEntity.setFeature(listProjectEdit.getFeature());
            priceDetailListEntity.setName(listProjectEdit.getName());
            priceDetailListEntity.setUnit(listProjectEdit.getUnit());
            return priceDetailListEntity;
        }
//获取最大的自动排序后缀
        else  if("清单".equals(listProjectEdit.getType()))
        {
            Integer maxFlagAddition=priceDetailListInterface.findMaxFlagAddition(listProjectEdit.getRootprojectid(),listProjectEdit.getOriginflag());

            if(maxFlagAddition!=null)
            {
                listProjectEdit.setFlag(flagAdditionHandel(listProjectEdit.getOriginflag(),maxFlagAddition));
                listProjectEdit.setOriginflag(listProjectEdit.getOriginflag());
                listProjectEdit.setFlagaddition((++maxFlagAddition));
            }else {
                listProjectEdit.setOriginflag(listProjectEdit.getFlag());
                listProjectEdit.setFlag(flagAdditionHandel(listProjectEdit.getOriginflag(), 0));
                listProjectEdit.setFlagaddition(1);
            }
//排序逻辑
                Integer allListCount=priceDetailListInterface.findMaxSortid(listProjectEdit.getProjectid(),"0");
                listProjectEdit.setSortid((allListCount==null?0:(++allListCount)));

        }else
        {
            Integer MaxId=priceDetailListInterface.findMaxSortid(listProjectEdit.getProjectid(),listProjectEdit.getParentid());
            listProjectEdit.setSortid(MaxId==null?0:(++MaxId));
        }

        return listProjectEdit;

    }
    //自动编号逻辑 flag+flagaddition
    public String flagAdditionHandel(String flag,int addition)
    {
        addition++;
        if(addition<10)
        {
            flag+="00"+addition;
        }else if(addition<100)
        {
            flag+="0"+addition;
        }
        return flag;
    }

    //  重新计算所有删除后的结构值
    public void CountAgain(Set<String> allGuids,Set<String> parentGuid)
    {
        List<PriceDetailListEntity> priceDetailListEntities=new ArrayList<PriceDetailListEntity>();
        List<PriceDetailListEntity> lists=new ArrayList<PriceDetailListEntity>();
        List<PriceDetailListEntity> bags=new ArrayList<PriceDetailListEntity>();
        List<PriceDetailListEntity> materials=new ArrayList<PriceDetailListEntity>();
        Set<String> allListGuid=new HashSet<String>();
        for(String projectId:allGuids)
        {
            priceDetailListEntities.addAll(priceDetailListInterface.findAllByProjectidOrderBySortidAscSortidsDesc(projectId));
        }
        for(PriceDetailListEntity priceDetailListEntity:priceDetailListEntities)
        {

            if("清单".equals(priceDetailListEntity.getType()))
            {
                lists.add(priceDetailListEntity);
            }
        }
        BigDecimal projectTotal=new BigDecimal(0);
        for(PriceDetailListEntity priceDetailListEntity:lists)
        {
            if(parentGuid.contains(priceDetailListEntity.getGuid()))
            {
                BigDecimal bagTotal=priceDetailListInterface.selectBagCount(priceDetailListEntity.getGuid());
                BigDecimal material=priceDetailListInterface.selectMaterialCount(priceDetailListEntity.getGuid());
                if(bagTotal==null)
                {
                    bagTotal=new BigDecimal(0);
                }
                if(material==null)
                {
                    material=new BigDecimal(0);
                }
                Double projectAmount=priceDetailListEntity.getProjectamount();
                if (projectAmount != null) {
                    priceDetailListEntity.setMaterialprice(material.divide(BigDecimal.valueOf(projectAmount == null ? 0 : projectAmount), 2, BigDecimal.ROUND_HALF_UP));
                    priceDetailListEntity.setBagclearprice(bagTotal.divide(BigDecimal.valueOf(projectAmount == null ? 0 : projectAmount), 2, BigDecimal.ROUND_HALF_UP));
                    BigDecimal total=bagTotal.add(material);
                    priceDetailListEntity.setPrice(total.divide(BigDecimal.valueOf(projectAmount == null ? 0 : projectAmount), 2, BigDecimal.ROUND_HALF_UP));
                    priceDetailListEntity.setTotal(total);
                    priceDetailListInterface.save(priceDetailListEntity);
                }
            }

        }
        for(String projectGuid :allGuids)
        {
            BigDecimal listAccount=priceDetailListInterface.selectProjectCount(projectGuid);
            ListProjectEdit listProjectEdit=listProjectEditInterface.findByGuid(projectGuid);
            listProjectEdit.setAmount(listAccount);
            listProjectEditInterface.save(listProjectEdit);
            if("0".equals(listProjectEdit.getParentid()))
            {
                ProjectmanageInformationMainEntity projectmanageInformationMainEntity=projectmanageInformationMainInterface.findByGuid(listProjectEdit.getProjectid());
                projectmanageInformationMainEntity.setMoney(listProjectEdit.getAmount().toString());
                projectmanageInformationMainInterface.save(projectmanageInformationMainEntity);
            }
            while (listProjectEdit != null && !("0").equals(listProjectEdit.getParentid())) {
                BigDecimal sonTotal = new BigDecimal(0);
                List<ListProjectEdit> allProject = listProjectEditInterface.findAllByParentid(listProjectEdit.getParentid());
                for (ListProjectEdit listProjectEdit1 : allProject)
                {
                    sonTotal = sonTotal.add(listProjectEdit1.getAmount()==null?new BigDecimal(0):listProjectEdit1.getAmount());
                }
                listProjectEdit = listProjectEditInterface.getOne(listProjectEdit.getParentid());
                if("0".equals(listProjectEdit.getParentid()))
                {
                    ProjectmanageInformationMainEntity projectmanageInformationMainEntity=projectmanageInformationMainInterface.findByGuid(listProjectEdit.getProjectid());
                    projectmanageInformationMainEntity.setMoney(sonTotal.toString());
                    projectmanageInformationMainInterface.save(projectmanageInformationMainEntity);
                }
                listProjectEdit.setAmount(sonTotal);
                listProjectEditInterface.save(listProjectEdit);
            }
        }
    }


    /**
     *处理批量上传的清单数据
     */
    public List<PriceDetailListEntity> HandelListForPrice(List<PriceDetailListEntity> priceDetailListEntityList)
    {
        List<PriceDetailListEntity> priceDetailListEntityLists=new ArrayList<>();
        for(PriceDetailListEntity priceDetailListEntity:priceDetailListEntityList)
        {
            List<PriceDetailListEntity> priceDetailListEntities=priceDetailListInterface.findAllByFlagAndRootprojectid(priceDetailListEntity.getFlag(),priceDetailListEntity.getRootprojectid());
            if(priceDetailListEntities!=null&&priceDetailListEntities.size()>0)
            {
                priceDetailListEntityLists.add(priceDetailListEntities.get(0));
            }else
                {
                    String flag=priceDetailListEntity.getFlag();
                    if(flag!=null&&flag.trim().length()==12)
                    {
                        String guid=UUID.randomUUID().toString().replace("-","");
                        priceDetailListEntity.setGuid(guid);
                        priceDetailListInterface.save(priceDetailListEntity);
                    }
                }
        }
        return priceDetailListEntityLists;
    }

    public void dataTransform(MaterialDatabase materialDatabase1,PriceDetailListEntity priceDetailList)
    {
        String uuid1= UUID.randomUUID().toString().replaceAll("-", "");
        PriceDetailListEntity priceDetailListEntity=new PriceDetailListEntity();
        priceDetailListEntity.setGuid(uuid1);
        priceDetailListEntity.setFlag(materialDatabase1.getFlag());
        priceDetailListEntity.setName(materialDatabase1.getName());
        priceDetailListEntity.setUnit(materialDatabase1.getUnit());
        priceDetailListEntity.setProjectamount(materialDatabase1.getProjectamount());
        priceDetailListEntity.setParentid(priceDetailList.getParentid());
        priceDetailListEntity.setProjectname(priceDetailList.getProjectname());
        priceDetailListEntity.setProjectid(priceDetailList.getProjectid());
        priceDetailListEntity.setPrice(materialDatabase1.getPrice());
        priceDetailListEntity.setType("材料");
        priceDetailListInterface.save(updateToNew(priceDetailListEntity));
    }

    public void thecopyList(String id,String pastedId)
    {
       PriceDetailListEntity priceDetailListEntity=priceDetailListInterface.findByGuid(id);
       List<PriceDetailListEntity> listEntities=priceDetailListInterface.findAllByParentid(id);
       List<PriceDetailListEntity> listEntities1=new ArrayList<>();
       PriceDetailListEntity pastepriceDetailListEntity=priceDetailListInterface.findByGuid(pastedId);
       PriceDetailListEntity tempPriceDetailListEntity1=copyPrice(priceDetailListEntity);
       PriceDetailListEntity tempPriceDetailListEntity2=copyPrice(pastepriceDetailListEntity);
       String oritype=priceDetailListEntity.getType();
       String pastype=pastepriceDetailListEntity.getType();
       if("清单".equals(oritype))
       {
           String uuid=UUID.randomUUID().toString().replace("-","").substring(0,5);
           String newguid=tempPriceDetailListEntity1.getGuid()+uuid;
           String projectid=tempPriceDetailListEntity2.getProjectid();
           tempPriceDetailListEntity1.setGuid(newguid);
           tempPriceDetailListEntity1.setTotal(BigDecimal.valueOf(0));
           tempPriceDetailListEntity1.setFlagaddition((tempPriceDetailListEntity1.getFlagaddition()+1));
           tempPriceDetailListEntity1.setProjectid(projectid);
           saveAndHandel(tempPriceDetailListEntity1);
           for(PriceDetailListEntity priceDetailListEntity1:listEntities) {
               PriceDetailListEntity priceDetailListEntity11=copyPrice(priceDetailListEntity1);
               String uuid2 = UUID.randomUUID().toString().replace("-", "");
               priceDetailListEntity11.setGuid(uuid2);
               priceDetailListEntity11.setParentid(newguid);
               priceDetailListEntity11.setProjectid(projectid);
               saveAndHandel(priceDetailListEntity11);
           }
       }else {
           String parentid = "";
           Double amount = new Double(0);

           if ("清单".equals(pastype)) {
               parentid = tempPriceDetailListEntity2.getGuid();
               amount = tempPriceDetailListEntity2.getProjectamount();
           } else {
               parentid = tempPriceDetailListEntity2.getParentid();
               PriceDetailListEntity priceDetailListEntity1 = priceDetailListInterface.findByGuid(parentid);
               amount = priceDetailListEntity1.getProjectamount();
           }
           String uuid3 = UUID.randomUUID().toString().replace("-", "");
           tempPriceDetailListEntity1.setGuid(uuid3);
           tempPriceDetailListEntity1.setParentid(parentid);
           tempPriceDetailListEntity1.setProjectid(tempPriceDetailListEntity2.getProjectid());
           tempPriceDetailListEntity1.setProjectamount(amount);
           saveAndHandel(tempPriceDetailListEntity1);
       }
    }


    public String copyList(String id,String pastedId)
    {
        List<PriceDetailListEntity> priceDetailListEntities=priceDetailListInterface.findAllByProjectidOrderBySortidAscSortidsDesc(id);
        List<PriceDetailListEntity> newPricelist=new ArrayList<>();
        String uuid=UUID.randomUUID().toString().replace("-","").substring(0,3);
        ListProjectEdit listProjectEdit=listProjectEditInterface.findByGuid(id);
        listProjectEdit=copyProjPrice(listProjectEdit);
        listProjectEdit.setGuid(listProjectEdit.getGuid()+uuid);
        listProjectEdit.setParentid(pastedId);
        listProjectEditInterface.save(listProjectEdit);
        while (listProjectEdit != null && !("0").equals(listProjectEdit.getParentid())) {
            BigDecimal sonTotal = new BigDecimal(0);
            List<ListProjectEdit> allProject = listProjectEditInterface.findAllByParentid(listProjectEdit.getParentid());
            for (ListProjectEdit listProjectEdit1 : allProject)
            {
                sonTotal = sonTotal.add(listProjectEdit1.getAmount()==null?new BigDecimal(0):listProjectEdit1.getAmount());
            }
            listProjectEdit = listProjectEditInterface.getOne(listProjectEdit.getParentid());
            if("0".equals(listProjectEdit.getParentid()))
            {
                ProjectmanageInformationMainEntity projectmanageInformationMainEntity=projectmanageInformationMainInterface.findByGuid(listProjectEdit.getProjectid());
                projectmanageInformationMainEntity.setMoney(sonTotal.toString());
                projectmanageInformationMainInterface.save(projectmanageInformationMainEntity);
            }
            listProjectEdit.setAmount(sonTotal);
            listProjectEditInterface.save(listProjectEdit);
        }
        for(PriceDetailListEntity priceDetailListEntity:priceDetailListEntities)
        {
            PriceDetailListEntity priceDetailListEntity1=copyPrice(priceDetailListEntity);
            String rootProjectId=priceDetailListEntity1.getRootprojectid();
            if("0".equals(priceDetailListEntity1.getParentid()))
            {
                priceDetailListEntity1.setGuid(priceDetailListEntity1.getGuid()+uuid);
                priceDetailListEntity1.setProjectid(priceDetailListEntity1.getProjectid()+uuid);
            }else
                {
                    priceDetailListEntity1.setGuid(priceDetailListEntity1.getGuid()+uuid);
                    priceDetailListEntity1.setProjectid(priceDetailListEntity1.getProjectid()+uuid);
                    priceDetailListEntity1.setParentid(priceDetailListEntity1.getParentid()+uuid);
                }
                String flag=priceDetailListEntity1.getFlag();
            if(flag!=null&&flag.length()==12)
            {
                int i=1;
                String flagAddition=flag.substring(10,12);
                int flagAdditions=0;
                try{
                    flagAdditions=Integer.valueOf(flagAddition);
                }catch(Exception e)
                {
                    logger.error(e.getMessage(),e);
                }
                String originFlag=flag.substring(0,9);
                Integer maxNum=priceDetailListInterface.findMaxFlagAddition(rootProjectId,originFlag);
                maxNum=maxNum==null?0:maxNum;
                if(flagAdditions>maxNum)
                {
                    maxNum=(flagAdditions-1);
                }
                priceDetailListEntity1.setOriginflag(originFlag);
                priceDetailListEntity1.setFlagaddition(++maxNum);
                priceDetailListEntity1.setFlag(flagAdditionHandel(originFlag,maxNum));
                priceDetailListEntity1.setSortid(i);
                try {
                    priceDetailListInterface.save(priceDetailListEntity1);
                } catch (Exception e) {
                    logger.error(e.getMessage(),e);
                }
            }
            priceDetailListInterface.save(priceDetailListEntity1);
        }
        return "success";
    }

    public PriceDetailListEntity copyPrice(PriceDetailListEntity priceDetailListEntity)
    {
        PriceDetailListEntity priceDetailListEntity1=null;
        if(priceDetailListEntity!=null)
        {
            priceDetailListEntity1=new PriceDetailListEntity(
                    priceDetailListEntity.getGuid(),
                    priceDetailListEntity.getBagclearprice(),
                    priceDetailListEntity.getBuildingprojectid(),
                    priceDetailListEntity.getContent(),
                    priceDetailListEntity.getCountruler(),
                    priceDetailListEntity.getFeature(),
                    priceDetailListEntity.getFlag(),
                    priceDetailListEntity.getInputerdeptid(),
                    priceDetailListEntity.getInputerdeptname(),
                    priceDetailListEntity.getInputerdeptpathid(),
                    priceDetailListEntity.getInputerfullname(),
                    priceDetailListEntity.getInputername(),
                    priceDetailListEntity.getMaterialprice(),
                    priceDetailListEntity.getName(),
                    priceDetailListEntity.getParentid(),
                    priceDetailListEntity.getPrice(),
                    priceDetailListEntity.getPricefrom(),
                    priceDetailListEntity.getProjectamount(),
                    priceDetailListEntity.getProjectid(),
                    priceDetailListEntity.getProjectname(),
                    priceDetailListEntity.getProjectnameapart(),
                    priceDetailListEntity.getProjectnameapartid(),
                    priceDetailListEntity.getRelatematerialamount(),
                    priceDetailListEntity.getRelatematerialdetailamount(),
                    priceDetailListEntity.getFeature(),
                    priceDetailListEntity.getRelatematerialdetailname(),
                    priceDetailListEntity.getRelatematerialid(),
                    priceDetailListEntity.getRelatematerialname(),
                    priceDetailListEntity.getRemark(),
                    priceDetailListEntity.getState(),
                    priceDetailListEntity.getSysid(),
                    priceDetailListEntity.getTotal(),
                    priceDetailListEntity.getType(),
                    priceDetailListEntity.getUnit(),
                    priceDetailListEntity.getUpdatebyuserfullname(),
                    priceDetailListEntity.getUpdatebyusername(),
                    priceDetailListEntity.getUpdatedt(),
                    priceDetailListEntity.getWorkcontent(),
                    priceDetailListEntity.getTaxrate(),
                    priceDetailListEntity.getSortid(),
                    priceDetailListEntity.getFlagaddition(),
                    priceDetailListEntity.getOriginflag(),
                    priceDetailListEntity.getSortids(),
                    priceDetailListEntity.getRootprojectid(),
                    priceDetailListEntity.getPersoncost(),
                    priceDetailListEntity.getMaterialcost(),
                    priceDetailListEntity.getMachinecost()
            );
        }
        return priceDetailListEntity1;
    }

    /*
    public PriceDetailListHistoryEntity copyPriceDetail(PriceDetailListEntity priceDetailListEntity)
    {
        String uuid1= UUID.randomUUID().toString().replaceAll("-", "");
        PriceDetailListHistoryEntity priceDetailListEntity1=null;
        if(priceDetailListEntity!=null)
        {
            priceDetailListEntity1=new PriceDetailListHistoryEntity(
                    uuid1,
                    priceDetailListEntity.getBagclearprice(),
                    priceDetailListEntity.getBuildingprojectid(),
                    priceDetailListEntity.getContent(),
                    priceDetailListEntity.getCountruler(),
                    priceDetailListEntity.getFeature(),
                    priceDetailListEntity.getFlag(),
                    priceDetailListEntity.getInputerdeptid(),
                    priceDetailListEntity.getInputerdeptname(),
                    priceDetailListEntity.getInputerdeptpathid(),
                    priceDetailListEntity.getInputerfullname(),
                    priceDetailListEntity.getInputername(),
                    priceDetailListEntity.getMaterialprice(),
                    priceDetailListEntity.getName(),
                    priceDetailListEntity.getParentid(),
                    priceDetailListEntity.getPrice(),
                    priceDetailListEntity.getPricefrom(),
                    priceDetailListEntity.getProjectamount(),
                    priceDetailListEntity.getProjectid(),
                    priceDetailListEntity.getProjectname(),
                    priceDetailListEntity.getProjectnameapart(),
                    priceDetailListEntity.getProjectnameapartid(),
                    priceDetailListEntity.getRelatematerialamount(),
                    priceDetailListEntity.getRelatematerialdetailamount(),
                    priceDetailListEntity.getFeature(),
                    priceDetailListEntity.getRelatematerialdetailname(),
                    priceDetailListEntity.getRelatematerialid(),
                    priceDetailListEntity.getRelatematerialname(),
                    priceDetailListEntity.getRemark(),
                    priceDetailListEntity.getState(),
                    priceDetailListEntity.getSysid(),
                    priceDetailListEntity.getTotal(),
                    priceDetailListEntity.getType(),
                    priceDetailListEntity.getUnit(),
                    priceDetailListEntity.getUpdatebyuserfullname(),
                    priceDetailListEntity.getUpdatebyusername(),
                    priceDetailListEntity.getUpdatedt(),
                    priceDetailListEntity.getWorkcontent(),
                    priceDetailListEntity.getTaxrate(),
                    priceDetailListEntity.getSortid(),
                    priceDetailListEntity.getFlagaddition(),
                    priceDetailListEntity.getOriginflag(),
                    priceDetailListEntity.getSortids(),
                    priceDetailListEntity.getRootprojectid(),
                    priceDetailListEntity.getPersoncost(),
                    priceDetailListEntity.getMaterialcost(),
                    priceDetailListEntity.getMachinecost()
            );
        }
        return priceDetailListEntity1;
    }
*/
    public ListProjectEdit copyProjPrice(ListProjectEdit listProjectEdit)
    {
        ListProjectEdit listProjectEdit1=null;
        if(listProjectEdit!=null) {
            listProjectEdit1=new ListProjectEdit(
                    listProjectEdit.getGuid(),
                    listProjectEdit.getSysid(),
                    listProjectEdit.getState(),
                    listProjectEdit.getParentid(),
                    listProjectEdit.getAmount(),
                    listProjectEdit.getRemark(),
                    listProjectEdit.getInputerdeptname(),
                    listProjectEdit.getInputername(),
                    listProjectEdit.getInputerfullname(),
                    listProjectEdit.getProjectname(),
                    listProjectEdit.getProjectnameapart(),
                    listProjectEdit.getFlag(),
                    listProjectEdit.getProjectid(),
                    listProjectEdit.getProjectnameapartid()
                    );
        }
        return listProjectEdit1;
    }


    public Page<MaterialDatabase> getMaterialList(PageRequest pageRequest, String type, String flag, String name, String feature, String msupplier) {
        return materialDatabaseInterface.findAll((Root<MaterialDatabase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)-> {
                    Path<String> typePath = root.get("type");
                    Path<String> flagPath = root.get("flag");
                    Path<String> namePath = root.get("name");
                    Path<String> featurePath = root.get("feature");
                    Path<String> msupplierPath = root.get("mlocation");
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
}
