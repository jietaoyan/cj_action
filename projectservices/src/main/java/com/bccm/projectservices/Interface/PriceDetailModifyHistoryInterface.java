package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.PriceDetailModifyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface PriceDetailModifyHistoryInterface extends JpaRepository<PriceDetailModifyHistory,String> {


    //按清包或材料及起始时间查询修改次数数量
    @Query(nativeQuery = true,value = "select count(guid) as num, flag ,name " +
            "from price_detail_modify_history " +
            "where type=?1 and (?2 ='' or updatedt>?2) and (?3='' or updatedt<?3) " +
            "group by flag order by num desc limit 0,10")
    List<Map<String,Object>> findUsageByTypeAndDate(String type, String start, String end);

}
