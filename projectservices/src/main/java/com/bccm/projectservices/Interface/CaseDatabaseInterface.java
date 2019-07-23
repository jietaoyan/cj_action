package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.CaseDatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaseDatabaseInterface extends JpaRepository<CaseDatabaseEntity,String>,JpaSpecificationExecutor<CaseDatabaseEntity> {

    CaseDatabaseEntity findByGuid(String guid);


    List<CaseDatabaseEntity> findAllByRelatepackidOrderByInputdateDesc(String relatepackid);

    List<CaseDatabaseEntity> findAllByRelatepackidAndFlagOrderByInputdateDesc(String relatepackid,String flag);
}
