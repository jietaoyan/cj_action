package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.LaborInformationMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface LaborInformationMainInfoInterface  extends JpaRepository<LaborInformationMainEntity, Long>,JpaSpecificationExecutor<LaborInformationMainEntity> {
    List<LaborInformationMainEntity> findAll();

    @Query(value = "delete from LaborInformationMainEntity m where m.guid in (?1)")
    @Modifying
    @Transactional
    void deleteByGudis(List<String> ids);


    //根据column1 companyname  type  content  companypath条件进行查询
    //List<LaborInformationMainEntity> findDistinctByColumn1AndCompanynameAndTypeAndContentAndCompanypath(String column1, String companyname, String type, String content, String companypath);
    //@Query(value = "select m.userId from LaborInformationMainEntity m where m.objectId = ?1 and m.objectType = ?2")
    //List<String> getUserIds(String objectId, String objectType);
}
