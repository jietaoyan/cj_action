package com.bccm.projectservices.Interface;

import com.bccm.projectservices.entity.PriceDetailListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public interface PriceDetailListInterface extends JpaRepository<PriceDetailListEntity,String> {


    List<PriceDetailListEntity> findAllByProjectidAndType(String projectid, String type);

    List<PriceDetailListEntity> findAllByFlagAndRootprojectid(String flag, String rootprojectid);

    List<PriceDetailListEntity> findAllByProjectidOrderBySortidAscSortidsDesc(String projectid);

    @Query(value = "delete from PriceDetailListEntity m where m.guid = ?1")
    @Modifying
    @Transactional
    void deleteByIdP(String Id);

    List<PriceDetailListEntity> findAllByGuid(String guid);

    List<PriceDetailListEntity> findAllByRootprojectid(String Rootprojectid);

    List<PriceDetailListEntity> findAllByParentid(String parentid);

    List<PriceDetailListEntity> findAllByParentidAndType(String parentid, String type);

    PriceDetailListEntity findByGuid(String guid);

    @Query(value="select Max(m.flagaddition) from  PriceDetailListEntity m  where m.rootprojectid=?1 and m.originflag=?2")
    @Transactional
    Integer findMaxFlagAddition(String rootprojectid,String flag);

    @Query(value="select  Max(m.sortid) from  PriceDetailListEntity m   where m.projectid=?1 and m.parentid=?2")
    @Transactional
    Integer  findMaxSortid(String projectId,String parentid);

    @Query(value="select  Max(m.sortids) from  PriceDetailListEntity m   where m.projectid=?1 and m.parentid=?2")
    @Transactional
    Integer  findMaxSortids(String projectId,String parentid);

    @Query(value="select sum(m.total) from PriceDetailListEntity  m where m.parentid=?1 and m.type='材料'")
    BigDecimal selectMaterialCount(String parentid);

    @Query(value="select sum(m.total) from PriceDetailListEntity  m where m.parentid=?1 and m.type='清包'")
    BigDecimal selectBagCount(String parentid);

    @Query(value="select sum(m.total) from PriceDetailListEntity  m where m.projectid=?1 and m.type='清单'")
    BigDecimal selectProjectCount(String projectid);

    @Query(value = "delete from PriceDetailListEntity m where m.projectid = ?1")
    @Modifying
    @Transactional
    void deleteAllInfobyProjectid(String projectid);

    List<PriceDetailListEntity> findAllByRootprojectidAndType(String rootprojectid, String type);
    /**
     * 查找材料汇总
     * @param Rootprojectid 根项目id
     * @return
     */
    @Query(value = "SELECT " +
            "flag as flag, " +
            "name as name, " +
            "feature as feature, " +
            "unit as unit, " +
            "price as price, " +
            "taxrate as taxrate, " +
            "remark as remark, " +
            "sum(projectamount) as projectamount, " +
            "sum(total) as total " +
            "FROM PriceDetailListEntity " +
            "where rootprojectid= ?1 and type='材料' " +
            "group by flag,name,feature,unit,price,taxrate,remark")
    List<Map<String,Object>> findMaterialByRootidaAndType(String Rootprojectid );

    /**
     *查找清包汇总
     * @param Rootprojectid 根项目id
     * @return
     */
    @Query(value = "SELECT " +
            "flag as flag, " +
            "name as name, " +
            "unit as unit, " +
            "price as price, " +
            "workcontent as workcontent," +
            "countruler as countruler, " +
            "pricefrom as pricefrom, " +
            "sum(projectamount) as projectamount, " +
            "sum(total) as total " +
            "FROM PriceDetailListEntity  " +
            "where rootprojectid=?1 and type='清包' " +
            "group by flag,name,unit,price,workcontent,countruler,pricefrom")
    List<Map<String,Object>> findBagClearByRootidaAndType(String Rootprojectid);

    //按清包或材料及起始时间查询使用次数统计
    @Query(nativeQuery = true,value = "select count(guid) as num,flag,name " +
            "from price_detail_list " +
            "where type=?1 and (?2 ='' or updatedt>?2) and (?3='' or updatedt<?3) " +
            "group by flag order by num desc limit 0,10")
    List<Map<String,Object>> findUsageByTypeAndDate(String type, String start, String end);

}
