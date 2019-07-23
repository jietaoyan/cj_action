package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.*;
import com.bccm.projectservices.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectMeasureCostService {

    @Autowired
    private ProjectMeasureCostInterface projectMeasureCostInterface;

    @Autowired
    private ProjectMeasureDetailInterface projectMeasureDetailInterface;

    @Autowired
    private ListProjectEditInterface listProjectEditInterface;

    @Autowired
    private ProjectSelfMainInterface projectSelfMainInterface;

    @Autowired
    private ProjectTaxCostInterface projectTaxCostInterface;

    /**
     * 依项目id查找措施费
     *
     * @param projectid 项目id
     * @return
     */
    public ProjectMeasureCostEntity findByProjectid(String projectid) {
        ProjectMeasureCostEntity p = projectMeasureCostInterface.findByProjectid(projectid);
        if (p == null) {
            p = new ProjectMeasureCostEntity();
            p.setGuid(UUID.randomUUID().toString().replace("-", ""));
        }
        //       setNull2Zero(p);
        return p;
    }

    /**
     * 获取措施费明细
     *
     * @param guid 措施费主键
     * @return
     */
    public List<ProjectMeasureDetail> findAllDetailByGuid(String guid) {
        return projectMeasureDetailInterface.findAllByFkguid(guid);
    }

    /**
     * 保存措施费
     *
     * @param projectMeasureCostEntity 措施费
     */
    public void saveOrUpdate(ProjectMeasureCostEntity projectMeasureCostEntity) {

        if (projectMeasureCostEntity.getInputdate() == null) {
            projectMeasureCostEntity.setInputdate(LocalDateTime.now());
        }
        projectMeasureCostInterface.saveAndFlush(projectMeasureCostEntity);
        updateAllCost(projectMeasureCostEntity.getProjectid());

    }

    /**
     * 保存措施费明细
     *
     * @param pDetials
     */
    public void saveOrUpdateDetail(List<ProjectMeasureDetail> pDetials, String fkguid) {
        List<ProjectMeasureDetail> preDeatails = projectMeasureDetailInterface.findAllByFkguid(fkguid);

        if (pDetials != null && pDetials.size() > 0){
            for (ProjectMeasureDetail detail : pDetials) {
                if (detail.getGuid() == null || "".equals(detail.getGuid())) {
                    detail.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
                } else {
                    if (preDeatails.size() > 0) {
                        Iterator<ProjectMeasureDetail> it = preDeatails.iterator();
                        while (it.hasNext()){
                            ProjectMeasureDetail preDetail = it.next();
                            if(preDetail.getGuid().equals(detail.getGuid())){
                                it.remove();
                                break;
                            }
                        }
                    }
                }
            }
        }
        projectMeasureDetailInterface.deleteAll(preDeatails);
        projectMeasureDetailInterface.saveAll(pDetials);
        projectMeasureDetailInterface.flush();
    }

    /**
     * 因措施费引用了分部分项费，税金引用了分部分项费、措施费、自营成本，
     * 当该项目下的分部分项费或措施费或自营成本更新时，更新措施费和税金
     *
     * @param projectid
     */
    @Transactional
    public void updateAllCost(String projectid) {
        ListProjectEdit listProject = listProjectEditInterface.findById(projectid).orElse(new ListProjectEdit());
        ProjectMeasureCostEntity measureCost = projectMeasureCostInterface.findByProjectid(projectid);
        ProjectSelfMainEntity selfCost = projectSelfMainInterface.findByProjectid(projectid);
        ProjectTaxCostEntity taxCost = projectTaxCostInterface.findByProjectid(projectid);

        if (measureCost == null && taxCost == null) return;

        if (measureCost == null) {
            measureCost = new ProjectMeasureCostEntity();
        }
        if (selfCost == null) {
            selfCost = new ProjectSelfMainEntity();
        }
        if (taxCost == null) {
            taxCost = new ProjectTaxCostEntity();
        }
        //分部分项费
        BigDecimal projectCost = listProject.getAmount() != null ? listProject.getAmount().setScale(2) : new BigDecimal(0);
        //场内临时费
        BigDecimal measureTempCost = measureCost != null ? measureCost.getInnertempcost().setScale(2) : new BigDecimal(0);
        double measurePercentage = measureCost.getCostpercentage() != null ? measureCost.getCostpercentage() : 0;
        //安全文明施工费
        BigDecimal measureSafeCost = projectCost.add(measureTempCost).multiply(BigDecimal.valueOf(measurePercentage))
                .multiply(BigDecimal.valueOf(0.01)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        //措施项目费
        BigDecimal measureCostTotal = measureSafeCost.add(measureTempCost).setScale(2);
        //自营成本
        BigDecimal selfCostTotal = selfCost.getSelftotalcost() != null ? selfCost.getSelftotalcost().setScale(2) : BigDecimal.valueOf(0);
        //税率
        double taxRate = taxCost.getTaxrate() != null ? taxCost.getTaxrate() : 0;
        //抵扣
        BigDecimal deductioncost = taxCost.getDeductioncost() != null ? taxCost.getDeductioncost().setScale(2) : BigDecimal.valueOf(0);
        //税金
        BigDecimal taxCostTotal = projectCost.add(measureCostTotal).add(selfCostTotal).multiply(BigDecimal.valueOf(taxRate))
                .multiply(BigDecimal.valueOf(0.01)).subtract(deductioncost).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        if (measureCost.getGuid() != null) {
            measureCost.setSafecost(measureSafeCost);
            measureCost.setTotalcost(measureCostTotal);
            projectMeasureCostInterface.saveAndFlush(measureCost);
        }
        if (taxCost.getGuid() != null) {
            taxCost.setTaxtotalcost(taxCostTotal);
            projectTaxCostInterface.saveAndFlush(taxCost);
        }
    }

}
