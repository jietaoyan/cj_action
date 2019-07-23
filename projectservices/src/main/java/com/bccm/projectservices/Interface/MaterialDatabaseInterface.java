package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.MaterialDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialDatabaseInterface extends JpaRepository<MaterialDatabase,String>,JpaSpecificationExecutor<MaterialDatabase> {
    MaterialDatabase findByGuid(String guid);

    @Query(nativeQuery = true, value = "select * from material_database a WHERE (?1='' OR a.name like ?1) AND (?2='' OR a.mlocation like ?2)  OFFSET ?4 ROWS FETCH NEXT ?5 ROWS ONLY")
    List<MaterialDatabase> getBridge(String name, String mlocation, int offNum, int pageSize);

    @Query(value="select m from MaterialDatabase m where m.name like ?1")
    List<MaterialDatabase> findAllofName(String name);


    MaterialDatabase findByFlag(String flag);

    @Query(value = "select type from MaterialDatabase where type<>''  group by type")
    List<String> findAllType();
}
