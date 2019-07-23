package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.ListProjectEditInterface;
import com.bccm.projectservices.Interface.PriceDetailListInterface;
import com.bccm.projectservices.entity.ListProjectEdit;
import com.bccm.projectservices.entity.PriceDetailListEntity;
import com.bccm.projectservices.entity.VO.PrjectCompareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于将项目信息和清单信息转换为PrjectCompareVo类用于清包对比功能模块
 */
@Service
public class PrjectCompareVoService {

    @Autowired
    ListProjectEditInterface listProjectEditInterface;

    @Autowired
    PriceDetailListInterface priceDetailListInterface;

    @Transactional
    public List<PrjectCompareVo> getProjectAndPriceByPrjRootId(String id) {

        List<PrjectCompareVo> prjectCompareVos = new ArrayList<>();
        //项目层级数据
        //       Map<Integer, List<ListProjectEdit>> listProjectEdits = new HashMap<>();
        List<ListProjectEdit> listProjectEdits = new ArrayList<>();
        //清单数据
        List<PriceDetailListEntity> priceDetailLists = new ArrayList<>();
        //项目guid集合
        List<String> prjGuids = new ArrayList<>();
        listProjectEdits = getAllByPrjRootGuid(id);
//        getAllPrjsByRootId(id, listProjectEdits);
        for(ListProjectEdit l:listProjectEdits){
            prjGuids.add(l.getGuid());
        }
        for (String projectid : prjGuids) {
            List<PriceDetailListEntity> priceDtails = priceDetailListInterface.findAllByProjectidAndType(projectid, "清单");
            priceDetailLists.addAll(priceDtails);
        }

        setListPrjToCompare(listProjectEdits, prjectCompareVos);
        setPrjPriceToCompare(priceDetailLists, prjectCompareVos);
        return prjectCompareVos;


    }

    /**
     * 依根项目guid通过projectid查找所有项目
     * @param projectid 根guid
     * @return
     */
    public List<ListProjectEdit> getAllByPrjRootGuid(String projectid){
        return listProjectEditInterface.findAllByProjectid(projectid);
    }

    /**
     * 获取项目最多三个层级信息(projectid已保存有根项目guid，方法更改，可废弃)
     *
     * @param id               项目根guid
     * @param listProjectEdits 接收所有ListProjectEdit
     */
    public void getAllPrjsByRootId(String id, List<ListProjectEdit> listProjectEdits) {
        try {
            ListProjectEdit bootPrj = listProjectEditInterface.findById(id).orElse(null);
            listProjectEdits.add(bootPrj);
            List<ListProjectEdit> secondPrjs = listProjectEditInterface.findAllByParentid(id);
            if (secondPrjs.size() > 0) {
                listProjectEdits.addAll(secondPrjs);
                for (ListProjectEdit secondPrj : secondPrjs) {
                    String secondGuid = secondPrj.getGuid();
                    List<ListProjectEdit> thirdPrjs = listProjectEditInterface.findAllByParentid(secondGuid);
                    if (thirdPrjs.size() > 0) {
                        listProjectEdits.addAll(thirdPrjs);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    /**
     * 将项目信息转换为prjectCompareVos
     *
     * @param listProjectEdits 项目结构
     * @param prjectCompareVos prjectCompareVos
     */
    private void setListPrjToCompare(List<ListProjectEdit> listProjectEdits, List<PrjectCompareVo> prjectCompareVos) {
        for (ListProjectEdit prj : listProjectEdits) {
            PrjectCompareVo prjVo = new PrjectCompareVo();
            prjVo.setGuid(prj.getGuid());
            prjVo.setParentid(prj.getParentid());
            prjVo.setFlag(prj.getProjectname());
            prjVo.setTotal(prj.getAmount());
            prjVo.setType("工程");
            prjVo.setProjectname(prj.getProjectname());
            prjectCompareVos.add(prjVo);
        }
    }

    /**
     * 将清单信息转换为prjectCompareVos
     * @param priceDetailLists 清单信息
     * @param prjectCompareVos prjectCompareVos
     */
    private void setPrjPriceToCompare(List<PriceDetailListEntity> priceDetailLists, List<PrjectCompareVo> prjectCompareVos) {
        for (PriceDetailListEntity pDetail :priceDetailLists){
            PrjectCompareVo prjVo = new PrjectCompareVo();
            prjVo.setGuid(pDetail.getGuid());
            prjVo.setParentid(pDetail.getProjectid());
            prjVo.setFlag(pDetail.getFlag());
            prjVo.setType(pDetail.getType());
            prjVo.setProjectname(pDetail.getName());
            prjVo.setUnit(pDetail.getUnit());
            prjVo.setProjectamount(pDetail.getProjectamount());
            prjVo.setPrice(pDetail.getPrice());
            prjVo.setTotal(pDetail.getTotal());
            prjVo.setPrjDegree("4");

            prjectCompareVos.add(prjVo);
        }
    }
}
