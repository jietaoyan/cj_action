package com.bccm.projectservices.controller;

import com.bccm.projectservices.entity.PriceDetailModifyHistory;
import com.bccm.projectservices.service.PriceModifyAndUsageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "priceDetailModifyAndUsage")
@RestController
public class PriceModifyAndUsageController {

    @Autowired
    PriceModifyAndUsageService priceModifyAndUsageService;

    @ApiOperation(value = "保存清包、材料价格修改信息")
    @PostMapping(value = "/project/priceModify/save", produces = {"application/json;charset=UTF-8"})
    public void savePriceMidfy(@RequestBody PriceDetailModifyHistory priceDetailModifyHistory){

            priceModifyAndUsageService.savePriceModify(priceDetailModifyHistory);
    }

    @ApiOperation(value = "获取前十清包、材料价格修改数量总计")
    @PostMapping(value = "/project/priceModify/getModifyNum", produces = {"application/json;charset=UTF-8"})
    public List<Map<String,Object>> getModifyNum(@RequestBody Map<String,String> params){
        return priceModifyAndUsageService.getModifyNumByTypeAndDate(params);
    }

    @ApiOperation(value = "获取前十清包、材料价格使用数量总计")
    @PostMapping(value = "/project/priceModify/getUsageNum", produces = {"application/json;charset=UTF-8"})
    public List<Map<String,Object>> getUsageNum(@RequestBody Map<String,String> params){
        return priceModifyAndUsageService.getUsageNumByTypeAndDate(params);
    }

}
