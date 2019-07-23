package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectmanageUserinforEntity;
import com.bccm.projectservices.entity.ProjectmanageUserinforLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Timestamp;

public interface ProjectUserLoginTimeInterface extends JpaRepository<ProjectmanageUserinforLoginEntity, Long>,JpaSpecificationExecutor<ProjectmanageUserinforLoginEntity> {

    @Query(nativeQuery = true,value = " insert into  projectmanage_userinfor_logininfo (logintime,username,userfullname,guid,deleteflag) values (?1,?2,?3,?4,1)")
    @Modifying
    @Transactional
    void saveloginTime(Timestamp logintime, String username, String userfullname, String guid);
}
