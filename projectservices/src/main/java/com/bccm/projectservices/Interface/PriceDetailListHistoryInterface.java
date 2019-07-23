package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.PriceDetailListHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface PriceDetailListHistoryInterface extends JpaRepository<PriceDetailListHistoryEntity,String>,JpaSpecificationExecutor<PriceDetailListHistoryEntity> {
        @Query(nativeQuery = true,value="select  count(flag) as counts,name from price_detail_list_history  group by flag,name  ORDER BY counts DESC limit(0,9)" )
    @Transactional
    List<Map> findtopten();
}
