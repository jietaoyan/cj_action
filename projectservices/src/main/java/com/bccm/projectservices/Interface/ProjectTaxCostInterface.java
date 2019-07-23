package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectTaxCostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTaxCostInterface extends JpaRepository<ProjectTaxCostEntity,String> {
    /**
     * 通过项目id查找税金
     * @param projectid 项目id
     * @return 税金
     */
    ProjectTaxCostEntity findByProjectid(String projectid);
}
