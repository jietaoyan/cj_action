package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.BagClearHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BagClearHistoryInterface extends JpaRepository<BagClearHistory,String>,JpaSpecificationExecutor<BagClearHistory> {

    @Query("select m from BagClearHistory m where m.flag=?1 order by m.collectdate desc")
    List<BagClearHistory> findRelateHistory(String flag);
}
