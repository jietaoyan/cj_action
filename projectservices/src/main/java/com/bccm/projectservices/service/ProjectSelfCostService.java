package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.ProjectSelfCostInterface;
import com.bccm.projectservices.Interface.ProjectSelfMainInterface;
import com.bccm.projectservices.Interface.ProjectSelfOtherInterface;
import com.bccm.projectservices.entity.ProjectSelfCostEntity;
import com.bccm.projectservices.entity.ProjectSelfMainEntity;
import com.bccm.projectservices.entity.ProjectSelfOtherEntity;
import com.bccm.projectservices.entity.VO.ProjectSelfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectSelfCostService {

    @Autowired
    private ProjectSelfMainInterface projectSelfMainInterface;

    @Autowired
    private ProjectSelfCostInterface projectSelfCostInterface;

    @Autowired
    private ProjectSelfOtherInterface projectSelfOtherInterface;

    @Autowired
    private ProjectMeasureCostService projectMeasureCostService;

    /**
     * 按项目id查找自营成本list
     *
     * @param projectid 项目id
     * @return 自营成本list
     */
    public ProjectSelfVo findAllByProjectid(String projectid) {
        ProjectSelfVo projectSelfVo = new ProjectSelfVo();//返回包含主表、基本、其他自营成本
        ProjectSelfMainEntity selfMain = projectSelfMainInterface.findByProjectid(projectid);//主表
        List<ProjectSelfCostEntity> selfCosts = new ArrayList<>();//基本自营成本
        List<ProjectSelfOtherEntity> selfOthers = new ArrayList<>();//其他自营成本

        //主表如果不为空则继续查找子表，如果为空则新生成带主键的主表和二十条记录的基本子表
        if (selfMain != null){
            String guid = selfMain.getGuid();
            selfCosts = projectSelfCostInterface.findAllByFkguid(guid);
            selfOthers = projectSelfOtherInterface.findAllByFkguid(guid);
        }else {
            selfMain = new ProjectSelfMainEntity();
            selfMain.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
            for (int i = 0; i < 20; i++) {
                ProjectSelfCostEntity selfCost = new ProjectSelfCostEntity();
                selfCost.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
                selfCost.setRowid(i);
                selfCosts.add(selfCost);
            }
        }
        projectSelfVo.setProjectSelfMainEntity(selfMain);
        projectSelfVo.setProjectSelfCostEntities(selfCosts);
        projectSelfVo.setProjectSelfOtherEntities(selfOthers);

        return projectSelfVo;
    }

    /**
     * 按项目id和行数查找自营成本（当rowid为0时，可获取该项目的自营成本总费用）
     *
     * @param projectid 项目id
     * @return ProjectSelfCostEntity
     */
    public ProjectSelfMainEntity findByProjectidAndRowid(String projectid) {
        ProjectSelfMainEntity selfMain = projectSelfMainInterface.findByProjectid(projectid);//主表
        if (selfMain == null){
            selfMain = new ProjectSelfMainEntity();
        }
        return selfMain;
    }

    @Transactional
    public void saveAllByPorjectid(ProjectSelfVo selfCosts) {

        ProjectSelfMainEntity selfMain = selfCosts.getProjectSelfMainEntity();//主表
        List<ProjectSelfCostEntity> baseCost = selfCosts.getProjectSelfCostEntities();//基本自营成本
        List<ProjectSelfOtherEntity> selfOther = selfCosts.getProjectSelfOtherEntities();//其他自营成本

        selfMain.setInputdate(LocalDateTime.now());

        //处理自营成本其他项中的明细表
        String guid = selfMain.getGuid();
        List<ProjectSelfOtherEntity> othersExists = projectSelfOtherInterface.findAllByFkguid(guid);

        if (selfOther != null && selfOther.size() > 0){
            for (ProjectSelfOtherEntity detail : selfOther) {
                if (detail.getGuid() == null || "".equals(detail.getGuid())) {
                    detail.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
                } else {
                    if (othersExists.size() > 0) {
                        Iterator<ProjectSelfOtherEntity> it = othersExists.iterator();
                        while (it.hasNext()){
                            ProjectSelfOtherEntity preDetail = it.next();
                            if(preDetail.getGuid().equals(detail.getGuid())){
                                it.remove();
                                break;
                            }
                        }
                    }
                }
            }
        }

        String projectid = selfMain.getProjectid();
        projectSelfMainInterface.save(selfMain);
        projectSelfCostInterface.saveAll(baseCost);
        projectSelfOtherInterface.saveAll(selfOther);
        projectSelfOtherInterface.deleteAll(othersExists);
        //项目措施费和税金引用了自营成本的总价，故需更新项目措施费和税金
        projectMeasureCostService.updateAllCost(projectid);
        projectSelfCostInterface.flush();
    }

}
