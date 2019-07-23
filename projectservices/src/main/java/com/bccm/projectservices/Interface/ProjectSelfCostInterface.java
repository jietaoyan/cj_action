package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectSelfCostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectSelfCostInterface extends JpaRepository<ProjectSelfCostEntity,String>{

    /**
     * 通过项目id查找自营成本list
     * @param projectid 项目id
     * @return list
     */
//    List<ProjectSelfCostEntity> findByProjectid(String projectid);

    /**
     * 通过外键查找自营成本list
     * @param fkguid 外键
     * @return
     */
    List<ProjectSelfCostEntity> findAllByFkguid(String fkguid);

//    ProjectSelfCostEntity findByProjectidAndRowid(String projectid, int rowid);
}
