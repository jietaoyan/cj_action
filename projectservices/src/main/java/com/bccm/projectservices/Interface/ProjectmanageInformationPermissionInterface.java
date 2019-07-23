package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ProjectmanageInformationPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface ProjectmanageInformationPermissionInterface extends JpaRepository<ProjectmanageInformationPermissionEntity, Long>,JpaSpecificationExecutor<ProjectmanageInformationPermissionEntity> {

    @Query(value = "select pip.guid from ProjectmanageInformationPermissionEntity pip where pip.fkguid = ?1 and pip.deleteflag <> -1 ")
    public String searchGuid(String fkguid);

    //展示项目管理首页的fkguids 原来的：    @Query(nativeQuery = true,value = "select dd.FKGUID from projectmanage_information_permission dd where (find_in_set( ?1,dd.sharename) and find_in_set( ?1,dd.sharedname) and dd.deleteflag <> -1) or dd.INPUTERNAME = ?1")
//    @Query(nativeQuery = true,value = "select dd.FKGUID from projectmanage_information_permission dd where (find_in_set( ?1,dd.sharedname) and dd.deleteflag <> -1) or dd.INPUTERNAME = ?1")
    @Query(value = "select dd.fkguid from ProjectmanageInformationPermissionEntity dd where dd.inputername=?1 and dd.deleteflag <> -1")
    public List<String> getAllFkguid(String username);

    //删除数据功能
    @Query(value = " delete  from ProjectmanageInformationPermissionEntity where fkguid = ?1 ")
    @Modifying
    @Transactional
    void deleteById(String id);

    //展示分享列表fkguids
//    @Query(nativeQuery = true,value = "select dd.FKGUID from projectmanage_information_permission dd where find_in_set( ?1,dd.sharename) and !find_in_set( ?1,dd.sharedname) and dd.deleteflag <> -1")
//    @Query(nativeQuery = true,value = "select dd.FKGUID  from projectmanage_information_permission dd where find_in_set( ?1,dd.sharename)  and dd.deleteflag <> -1")
//    public List<String> getSharelistFkguids(String username);

    //设置已分享字段  原来的    @Query(nativeQuery = true,value = "SELECT dd.* ,case  when find_in_set('admin2',pp.sharename) and !find_in_set('admin2',pp.sharedname) then '未分享'  else '已分享' end as isshare FROM projectmanage_information_main  dd ,projectmanage_information_permission pp where dd.guid = pp.fkguid and dd.guid in (select dd.FKGUID  from projectmanage_information_permission dd where find_in_set( 'admin2',dd.sharename)  and dd.deleteflag <> -1)")
    @Query(nativeQuery = true,value = "SELECT dd.* ,case  when find_in_set(?1,pp.sharedname) then '已分享'  else '未分享' end as isshare FROM projectmanage_information_main  dd ,projectmanage_information_permission pp where dd.guid = pp.fkguid and dd.prjname like ?2 and dd.inputerfullname like ?3 and dd.type like ?4 and dd.guid in (select dd.FKGUID  from projectmanage_information_permission dd where find_in_set( ?1,dd.sharename) and dd.deleteflag <> -1)")
    @Modifying
    @Transactional
    List<Map<String,Object>> getSharelistFkguids(String userName,String prjname,String inputerfullname,String type);



    //点击添加到项目管理后，将sharedname存入数据库
    @Query(nativeQuery = true,value = "update projectmanage_information_permission dd set dd.sharedname=concat(ifnull(dd.sharedname,''),?2) where dd.fkguid= ?1 and (!find_in_set(?3,dd.sharedname) or dd.sharedname is null)")
    @Modifying
    @Transactional
    void addSharedName(String fkguid,String usernames,String username);

    //分享项目列表点击删除后，去掉sharename中对应的用户名
    @Query(nativeQuery = true,value = "update projectmanage_information_permission dd set dd.sharename=replace(dd.sharename,?2,'') where dd.fkguid= ?1")
    @Modifying
    @Transactional
    void deleteShareName(String fkguid,String usernames);

    //找到作者的用户名
    @Query(value = "select pip.inputername from ProjectmanageInformationPermissionEntity pip where pip.fkguid = ?1 and pip.deleteflag <> -1 ")
    public String getAuthor(String id);

    //将已经添加到项目管理首页列表的用户名删除 --分享删除功能
    @Query(nativeQuery = true,value = "update projectmanage_information_permission dd set dd.sharedname=replace(dd.sharedname,?2,'') where dd.fkguid=?1")
    @Modifying
    @Transactional
    void deleteBySharedName(String id,String username);

    //多次分享时，在字段后面增加分享人
    @Query(nativeQuery = true,value = "update projectmanage_information_permission dd set dd.sharename=concat(ifnull(dd.sharename,''),?2) where dd.guid= ?1 and (!find_in_set(?3,dd.sharename) or dd.sharename is null)")
    @Modifying
    @Transactional
    void addShareName(String guid,String usernames,String username);


    //根据fkguid查询guid
    @Query(value = "select dd.guid from ProjectmanageInformationPermissionEntity dd where dd.fkguid=?1 and dd.deleteflag <> -1")
    public String getGuidByFkguid(String fkguid);


}
