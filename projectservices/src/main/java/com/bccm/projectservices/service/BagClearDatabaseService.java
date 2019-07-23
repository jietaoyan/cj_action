package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.BagClearDatabaseInterface;
import com.bccm.projectservices.Interface.BagClearHistoryInterface;
import com.bccm.projectservices.controller.BagComponentController;
import com.bccm.projectservices.entity.BagClearDatabase;
import com.bccm.projectservices.entity.BagClearHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BagClearDatabaseService {
    public static final Logger logger = LoggerFactory.getLogger(BagClearDatabaseService.class);
    @Autowired
    BagClearDatabaseInterface bagClearDatabaseInterface;

    @Autowired
    BagClearHistoryInterface bagClearHistoryInterface;

    @Transactional
    public String saveAll(List<BagClearDatabase> bagClearDatabases)
    {
        List<BagClearHistory> bagClearHistories=new ArrayList<>();
        List<BagClearDatabase> removeList=new ArrayList<>();
        for(BagClearDatabase bagClearDatabase:bagClearDatabases)
        {
            BagClearDatabase oldBag=bagClearDatabaseInterface.findByGuid(bagClearDatabase.getGuid());
            if(oldBag!=null)
            {
                Date oldBageDate=oldBag.getCollectdate();
                Date compareDate=bagClearDatabase.getCollectdate();
                System.out.println(compareDate);
                System.out.println(oldBageDate);
                if(compareDate!=null&&oldBageDate!=null&&compareDate.compareTo(oldBageDate) >= 0)
                {
                    bagClearHistories.add(dataTransform(oldBag));
                }
                else if(compareDate!=null&&oldBageDate!=null&&compareDate.compareTo(oldBageDate)<0)
                    {
                        bagClearHistories.add(dataTransform(bagClearDatabase));
                        removeList.add(bagClearDatabase);
                    }
            }
        }

        bagClearDatabases.removeAll(removeList);
        try {
            bagClearHistoryInterface.saveAll(bagClearHistories);
            bagClearDatabaseInterface.saveAll(bagClearDatabases);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "数据插入异常，请检查具体模板格式！";
        }
        return "SUCCESS";
    }

    public BagClearHistory dataTransform(BagClearDatabase bagClearDatabase)
    {
        BagClearHistory bagClearHistory=new BagClearHistory();
        if(bagClearDatabase!=null)
        {
            bagClearHistory.setGuid(UUID.randomUUID().toString().replaceAll("-", ""));
            bagClearHistory.setCountruler(bagClearDatabase.getCountruler());
            bagClearHistory.setFlag(bagClearDatabase.getFlag());
            bagClearHistory.setName(bagClearDatabase.getName());
            bagClearHistory.setParentid(bagClearDatabase.getParentid());
            bagClearHistory.setPricefrom(bagClearDatabase.getPricefrom());
            bagClearHistory.setPrice(bagClearDatabase.getPrice());
            bagClearHistory.setProjectamount(bagClearDatabase.getProjectamount());
            bagClearHistory.setProjectid(bagClearDatabase.getProjectid());
            bagClearHistory.setProjectname(bagClearDatabase.getProjectname());
            bagClearHistory.setRelatematerialamount(bagClearDatabase.getRelatematerialamount());
            bagClearHistory.setRelatematerialamount2(bagClearDatabase.getRelatematerialamount2());
            bagClearHistory.setRelatematerialid(bagClearDatabase.getRelatematerialid());
            bagClearHistory.setRelatematerialid2(bagClearDatabase.getRelatematerialid2());
            bagClearHistory.setRelatematerialname(bagClearDatabase.getRelatematerialname());
            bagClearHistory.setRelatematerialname2(bagClearDatabase.getRelatematerialname2());
            bagClearHistory.setState(bagClearDatabase.getState());
            bagClearHistory.setSysid(bagClearDatabase.getSysid());
            bagClearHistory.setUnit(bagClearDatabase.getUnit());
            bagClearHistory.setWorkcontent(bagClearDatabase.getWorkcontent());
            bagClearHistory.setInputdate(bagClearDatabase.getInputdate());
            bagClearHistory.setCollectdate(bagClearDatabase.getCollectdate());
        }
        return bagClearHistory;
    }
}
