package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.UserPermissionDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserPermissionDataInterface  extends JpaRepository<UserPermissionDataEntity, Long>,JpaSpecificationExecutor<UserPermissionDataEntity> {

//    @Query(nativeQuery = true, value = "SELECT * from user_permission_data t where t.id in (SELECT t2.object_id from involve_members t2 where t2.object_type = 'project' and t2.user_id = ?1) and (case when (?2 is null or t.name like ?2) and (?3 is null or t.project_process = ?3) then 1 else 0 end)=1")
//    Page<UserPermissionDataInterface> getProjectsByUserId(String userId, String name, String projectProcess, Pageable pageable);

    @Query(value="select m from UserPermissionDataEntity m where m.inputerusername=?1 and m.permissionstate='同意' and m.permissionlevel='管理员'")
    UserPermissionDataEntity getPermissionCheck(String Inputerusername);

    @Query(value="select count(guid) from UserPermissionDataEntity m where m.inputerusername=?1 and m.permissionstate='同意' and m.permissionlevel='管理员'")
    int getPermissionCheckCount(String Inputerusername);
}
