package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectMeasureDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectMeasureDetailInterface extends JpaRepository<ProjectMeasureDetail,String> {

    /**
     * 依外键查找措施费明细
     * @param fkguid 措施费主键
     * @return
     */
    List<ProjectMeasureDetail> findAllByFkguid(String fkguid);

}
