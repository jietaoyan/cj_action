package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectmanageInformationMainEntity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface ProjectmanageInformationMainInterface extends JpaRepository<ProjectmanageInformationMainEntity, Long>,JpaSpecificationExecutor<ProjectmanageInformationMainEntity> {

    @Query(value = " delete  from ProjectmanageInformationMainEntity where guid = ?1 or parentid = ?1")
    @Modifying
    @Transactional
    void deleteById(String id);

    ProjectmanageInformationMainEntity findByGuid(String guid);

    //设置已分享字段  原来的    @Query(nativeQuery = true,value = "SELECT dd.* ,case  when find_in_set('admin2',pp.sharename) and !find_in_set('admin2',pp.sharedname) then '未分享'  else '已分享' end as isshare FROM projectmanage_information_main  dd ,projectmanage_information_permission pp where dd.guid = pp.fkguid and dd.guid in (select dd.FKGUID  from projectmanage_information_permission dd where find_in_set( 'admin2',dd.sharename)  and dd.deleteflag <> -1)")
    @Query(nativeQuery = true,value = "SELECT dd.* ,case  when find_in_set(?1,pp.sharedname) then '已分享'  else '未分享' end as isshare FROM projectmanage_information_main  dd ,projectmanage_information_permission pp where dd.guid = pp.fkguid and dd.guid in (select dd.FKGUID  from projectmanage_information_permission dd where find_in_set( ?1,dd.sharename)  and dd.deleteflag <> -1)")
    @Modifying
    @Transactional
    List<Map<String,Object>> getAllAndShare(String userName);


    @Query(nativeQuery = true,value = "SELECT * " +
            "FROM bccm.projectmanage_information_main ,(select getProjectsFromProjectmanage(?1) getguids) t " +
            "where find_in_set(id,getguids)")
    List<ProjectmanageInformationMainEntity> findAllByRootid(String id);


  }
