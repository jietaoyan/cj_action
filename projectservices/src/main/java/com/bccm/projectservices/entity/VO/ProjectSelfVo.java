package com.bccm.projectservices.entity.VO;

import com.bccm.projectservices.entity.ProjectSelfCostEntity;
import com.bccm.projectservices.entity.ProjectSelfMainEntity;
import com.bccm.projectservices.entity.ProjectSelfOtherEntity;

import java.util.List;

/**
 * 自营成本工具类，用于将自营成本主表子表统一于前台交互
 */
public class ProjectSelfVo {

    private ProjectSelfMainEntity projectSelfMainEntity;
    private List<ProjectSelfCostEntity> projectSelfCostEntities;
    private List<ProjectSelfOtherEntity> projectSelfOtherEntities;

    public ProjectSelfMainEntity getProjectSelfMainEntity() {
        return projectSelfMainEntity;
    }

    public void setProjectSelfMainEntity(ProjectSelfMainEntity projectSelfMainEntity) {
        this.projectSelfMainEntity = projectSelfMainEntity;
    }

    public List<ProjectSelfCostEntity> getProjectSelfCostEntities() {
        return projectSelfCostEntities;
    }

    public void setProjectSelfCostEntities(List<ProjectSelfCostEntity> projectSelfCostEntities) {
        this.projectSelfCostEntities = projectSelfCostEntities;
    }

    public List<ProjectSelfOtherEntity> getProjectSelfOtherEntities() {
        return projectSelfOtherEntities;
    }

    public void setProjectSelfOtherEntities(List<ProjectSelfOtherEntity> projectSelfOtherEntities) {
        this.projectSelfOtherEntities = projectSelfOtherEntities;
    }
}
