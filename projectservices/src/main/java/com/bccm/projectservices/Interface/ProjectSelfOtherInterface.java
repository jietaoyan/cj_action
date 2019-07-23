package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectSelfOtherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectSelfOtherInterface extends JpaRepository<ProjectSelfOtherEntity,String> {


    /**
     * 通过外键查找其他自营成本list
     * @param fkguid 外键
     * @return
     */
    List<ProjectSelfOtherEntity> findAllByFkguid(String fkguid);

}
