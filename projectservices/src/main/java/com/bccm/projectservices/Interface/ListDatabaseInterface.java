package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.ListDatabase;
import com.bccm.projectservices.entity.ProjectmanageInformationMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ListDatabaseInterface extends JpaRepository<ListDatabase,String>,JpaSpecificationExecutor<ListDatabase> {
    List<ListDatabase> findAllByStandardNameAndState(String standardName,String state);

    @Query(value="select m from ListDatabase m group by m.standardName,m.standardId")
    List<ListDatabase> findAllKindsStandardName();

    List<ListDatabase> findAllByFlagLikeAndNameLikeAndUnitLikeAndPriceLike(String flag,String name ,String unit,BigDecimal price);


    List<ListDatabase> findAllByGuid(String guid);

    List<ListDatabase> findAllByParentid(String parentid);

    List<ListDatabase> findAllByStandardName(String standardName);

    void deleteAllByGuid(List<String> guids);

    List<ListDatabase> findAllByName(String name);

    @Query(value="select m from ListDatabase m where m.name like ?1 and m.state='1'")
    List<ListDatabase> findAllofName(String name);
}
