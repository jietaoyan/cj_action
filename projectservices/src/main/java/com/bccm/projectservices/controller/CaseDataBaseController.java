package com.bccm.projectservices.controller;


import com.bccm.projectservices.Interface.CaseDatabaseInterface;
import com.bccm.projectservices.entity.CaseDatabaseEntity;
import com.bccm.projectservices.entity.ListDatabase;
import com.bccm.projectservices.service.CaseDatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(value = "CaseDataBaseController")
@RestController
public class CaseDataBaseController {
    @Autowired
    CaseDatabaseInterface caseDatabaseInterface;


    @Autowired
    CaseDatabaseService caseDatabaseService;

    @ApiOperation(value = "按分页和搜索栏获取数据")
    @GetMapping(value = "/caseData/getCase", produces = {"application/json;charset=UTF-8"})
    public Page<CaseDatabaseEntity> getList(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "20") int size
            , @RequestParam(required = false) String inputerusername
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) String subjectname
            , @RequestParam(value = "startdate",required =false)String startdate
            , @RequestParam(value = "enddate",required =false)String enddate) {
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        if("undefined".equals(inputerusername))
        {
            inputerusername="";
        }

        if("undefined".equals(name))
        {
            name="";
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<CaseDatabaseEntity> page1=caseDatabaseService.getList(pageRequest, inputerusername, name, subjectname, startdate,enddate);
        return page1;
    }

    @ApiOperation(value="批量保存案例数据")
    @PostMapping(value = "/case/saveInfo",produces = {"appliication/json;charset=UTF-8"})
    public String saveAll(@RequestBody List<CaseDatabaseEntity> caseDatabaseEntityList)
    {
        return caseDatabaseService.saveCaseInfo(caseDatabaseEntityList);
    }


    @ApiOperation(value="批量删除案例数据")
    @PostMapping(value = "/case/deleteInfo",produces = {"appliication/json;charset=UTF-8"})
    public String deleteAll(@RequestBody List<CaseDatabaseEntity> caseDatabaseEntityList)
    {
        return caseDatabaseService.deleteAll(caseDatabaseEntityList);
    }

    @ApiOperation(value = "获取清包关联案例数据")
    @GetMapping(value = "/caseData/getRelateCase", produces = {"application/json;charset=UTF-8"})
    public List<CaseDatabaseEntity> getListByid( @RequestParam(required = false) String relatepackid){

         return caseDatabaseInterface.findAllByRelatepackidOrderByInputdateDesc(relatepackid);
    }

    @ApiOperation(value = "获取清包关联案例数据的编码")
    @GetMapping(value = "/caseData/getRelateCaseFlag", produces = {"application/json;charset=UTF-8"})
    public List<CaseDatabaseEntity> getListByid( @RequestParam(required = false) String relatepackid,@RequestParam(required=false) String caseFlag ){

        return caseDatabaseInterface.findAllByRelatepackidAndFlagOrderByInputdateDesc(relatepackid,caseFlag);
    }
}
