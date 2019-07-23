package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.BagComponentInterface;
import com.bccm.projectservices.entity.BagComponentsEntity;
import com.bccm.projectservices.service.BagComponentService;
import com.bccm.projectservices.service.PriceDetailListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(value = "BagComponentController")
@RestController
public class BagComponentController
{
    public static final Logger logger = LoggerFactory.getLogger(BagComponentController.class);
    @Autowired
    BagComponentService bagComponentService;

    @Autowired
    BagComponentInterface bagComponentInterface;

    @ApiOperation(value="批量保存人材机数据")
    @PostMapping(value = "/component/saveall",produces = {"appliication/json;charset=UTF-8"})
    public String saveAll(@RequestBody List<BagComponentsEntity> bagComponentsEntityList)
    {
        for(BagComponentsEntity bagComponentsEntity:bagComponentsEntityList)
        {
            String uuid= UUID.randomUUID().toString().replace("-","");
            bagComponentsEntity.setGuid(uuid);
        }
        try {
            bagComponentService.saveAllComponent(bagComponentsEntityList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "success";
    }

    @ApiOperation(value="批量保存人材机数据")
    @GetMapping(value = "/component/selectbyflag")
    public List<BagComponentsEntity> selectByFlag(@RequestParam(required = false) String relatePkid)
    {
        List<BagComponentsEntity> bagComponentsEntityList=bagComponentInterface.findAllByRelatepackid(relatePkid);
        return bagComponentsEntityList;
    }


    @ApiOperation(value = "删除人材机数据")
    @PostMapping(value = "/component/deleteDetail", produces = {"application/json;charset=UTF-8"})
    public String deleteDetailRows(@RequestBody List<BagComponentsEntity> BagComponentsEntity){
        try {
            bagComponentService.deleteRelateComponent(BagComponentsEntity);
            return "success";
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return "fail";
        }
    }
}
