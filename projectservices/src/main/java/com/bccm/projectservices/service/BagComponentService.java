package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.BagComponentInterface;
import com.bccm.projectservices.entity.BagComponentsEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BagComponentService {
    public static final Logger logger = LoggerFactory.getLogger(BagComponentService.class);
    @Autowired
    BagComponentInterface bagComponentInterface;

    public String saveAllComponent(List<BagComponentsEntity> bagComponentsEntityList)
    {
        String returnWord="";
        for(BagComponentsEntity bagComponentsEntity:bagComponentsEntityList)
        {
            if(bagComponentsEntity.getGuid()==null)
            {
                String guid= UUID.randomUUID().toString().replace("-","");
                bagComponentsEntity.setGuid(guid);
                Date inputDate= Date.valueOf(LocalDate.now());
                bagComponentsEntity.setInputdate(inputDate);
            }
        }

        try {
            bagComponentInterface.saveAll(bagComponentsEntityList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return returnWord;
        }
        returnWord="SUCCESS";
        return returnWord;
    }


    @Transactional
    public String deleteRelateComponent(List<BagComponentsEntity> bagComponentsEntityList){
        try {
            bagComponentInterface.deleteAll(bagComponentsEntityList);
            bagComponentInterface.flush();
            return "success";
        }catch (Exception e){
            logger.error(e.getMessage());
            return "删除失败";
        }
    }
}
