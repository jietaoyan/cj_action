package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectSelfMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectSelfMainInterface extends JpaRepository<ProjectSelfMainEntity,String> {

    /**
     * 通过项目id查找自营成本主表
     * @param projectid
     * @return
     */
    ProjectSelfMainEntity findByProjectid(String projectid);
}
