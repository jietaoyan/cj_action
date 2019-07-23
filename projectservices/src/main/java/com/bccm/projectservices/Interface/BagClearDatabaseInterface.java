package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.BagClearDatabase;
import com.bccm.projectservices.entity.ListDatabase;
import com.bccm.projectservices.entity.MaterialDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BagClearDatabaseInterface extends JpaRepository<BagClearDatabase,String>,JpaSpecificationExecutor<BagClearDatabase> {
    List<BagClearDatabase> findAllByProjectname(String projectname);


    @Query(value="select m from BagClearDatabase m group by m.projectid,m.projectname ")
    List<BagClearDatabase> findkindOfProject();

    List<BagClearDatabase> findAllByProjectnameAndState(String ProjectId,String state);

    BagClearDatabase findByGuid(String guid);

    BagClearDatabase findByFlag(String flag);


    @Query(value="select m from BagClearDatabase m where m.name like ?1 and m.state='1'")
    List<BagClearDatabase> findAllOfName(String name);
}
