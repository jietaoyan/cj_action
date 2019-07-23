package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectmanageUserinforEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;

public interface ProjectUserinforInterface extends JpaRepository<ProjectmanageUserinforEntity, Long>,JpaSpecificationExecutor<ProjectmanageUserinforEntity> {

}
