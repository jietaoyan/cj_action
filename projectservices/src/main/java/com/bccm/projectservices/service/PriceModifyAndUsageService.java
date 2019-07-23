package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.PriceDetailListInterface;
import com.bccm.projectservices.Interface.PriceDetailModifyHistoryInterface;
import com.bccm.projectservices.entity.PriceDetailModifyHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.EditorKit;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PriceModifyAndUsageService {

    @Autowired
    PriceDetailModifyHistoryInterface priceDetailModifyHistoryInterface;

    @Autowired
    PriceDetailListInterface priceDetailListInterface;

    private final Logger logger = LoggerFactory.getLogger(PriceModifyAndUsageService.class);

    //保存清包或材料价格修改信息
    public void savePriceModify(PriceDetailModifyHistory priceDetailModifyHistory) {
        priceDetailModifyHistory.setGuid(UUID.randomUUID().toString().replaceAll("-",""));
        priceDetailModifyHistory.setUpdatedt(new Timestamp(System.currentTimeMillis()));
        try {
            priceDetailModifyHistoryInterface.saveAndFlush(priceDetailModifyHistory);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    public List<Map<String,Object>> getModifyNumByTypeAndDate(Map<String,String> params){
        return getPrices(params,1);
    }

    public List<Map<String,Object>> getUsageNumByTypeAndDate(Map<String,String> params){
        return getPrices(params,2);
    }

    private List<Map<String,Object>> getPrices(Map<String,String> params, int modifyOrUsage){
        String type = params.get("type");
        String start = params.get("start");
        String end = params.get("end");
        start = start == null ? "" : start;
        end = end == null ? "" : end;
        if (modifyOrUsage == 1){
            return priceDetailModifyHistoryInterface.findUsageByTypeAndDate(type,start, end);
        }else {
            return priceDetailListInterface.findUsageByTypeAndDate(type,start, end);
        }
    }

}
