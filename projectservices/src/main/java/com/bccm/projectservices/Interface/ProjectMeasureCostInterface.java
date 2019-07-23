package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectMeasureCostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMeasureCostInterface extends JpaRepository<ProjectMeasureCostEntity,String> {

    /**
     * 通过项目id查找项目措施费
     * @param projectid 项目id
     * @return ProjectMeasureCostEntity
     */
    ProjectMeasureCostEntity findByProjectid(String projectid);

}
