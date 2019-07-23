package com.bccm.projectservices.controller;

import com.bccm.projectservices.entity.VO.PageEntity;
import com.bccm.projectservices.service.EngineeringGuidanceService;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceEntity;
import com.bccm.projectservices.sqlInterface.EngineeringGuidanceInterface;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProjectBagController {

    @Autowired
    private EngineeringGuidanceInterface engineeringGuidanceInterface;

    @Autowired
    private EngineeringGuidanceService guidanceService;

    @ApiOperation(value = "按搜索条件和分页查找清包数据")
    @PostMapping(value = "/baseList/prjbagclear/getPrjBagclears", produces = {"application/json;charset=UTF-8"})
    public PageEntity<EngineeringGuidanceEntity> getPrjBags(@RequestBody Map<String,String> params){
        return guidanceService.getPrjBagsList(params);
    }

    @ApiOperation(value = "查找专业类型下拉选")
    @GetMapping(value = "/baseList/prjbagclear/getProfessionals/{type}", produces = {"application/json;charset=UTF-8"})
    public List<String> findAllProfessional(@PathVariable String type) {
        return guidanceService.findAllProfessional(type);
    }

    @ApiOperation(value = "查找数据来源下拉选")
    @GetMapping(value = "/baseList/prjbagclear/getDatasources/{type}", produces = {"application/json;charset=UTF-8"})
    public List<String> findAllDatasource(@PathVariable String type) {
        return guidanceService.findAllDatasource(type);
    }

    @ApiOperation(value = "查找分包模式下拉选")
    @GetMapping(value = "/baseList/prjbagclear/getSubcontractingmodes/{type}", produces = {"application/json;charset=UTF-8"})
    public List<String> findAllSubcontractingmode(@PathVariable String type) {
        return guidanceService.findAllSubcontractingmode(type);
    }


//    @ApiOperation(value = "批量保存人材机数据")
//    @GetMapping(value = "/projectPackage/selectAll")
//    public List<EngineeringGuidanceEntity> selectAll() {
//        List<EngineeringGuidanceEntity> bagClearHistories = engineeringGuidanceInterface.findAll();
//        return bagClearHistories;
//    }

    @ApiOperation(value = "资料库按搜索条件和分页查找清包数据")
    @PostMapping(value = "/baseList/prjbagclear/getPrjBagclearsList", produces = {"application/json;charset=UTF-8"})
    public PageEntity<EngineeringGuidanceEntity> getPrjBagsOfDetail(@RequestBody Map<String,String> params){
        return guidanceService.getAllPrjBagsList(params);
    }

    @ApiOperation(value = "资料库按搜索条件和分页查找施工清包数据")
    @PostMapping(value = "/baseList/prjbagclear/getDiffPrjBagclearsList", produces = {"application/json;charset=UTF-8"})
    public PageEntity<EngineeringGuidanceEntity> getDiffPrjBagsOfDetail(@RequestBody Map<String,String> params){
        return guidanceService.getAllDiffPrjBagsList(params);
    }
}
