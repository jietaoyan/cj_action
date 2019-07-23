package com.bccm.projectservices.controller;

import com.bccm.projectservices.entity.ProjectTaxCostEntity;
import com.bccm.projectservices.service.ProjectExcelExport;
import com.bccm.projectservices.service.ProjectTaxCostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

@Api(value = "ProjectTaxCost")
@RestController
public class ProjectTaxCostController {

    @Autowired
    private ProjectTaxCostService projectTaxCostService;

    @Autowired
    private ProjectExcelExport projectExcelExport;

    @ApiOperation(value = "获取该项目id下的税金")
    @ApiImplicitParam(name = "projectid",value = "项目id", required = true)
    @GetMapping(value = "/project/prjTaxCost/{projectid}", produces = {"application/json;charset=UTF-8"})
    public ProjectTaxCostEntity findByProjectid(@PathVariable String projectid){
        System.out.print(projectid);
        return projectTaxCostService.findByProjectid(projectid);
    }

    @ApiOperation(value = "保存项目税金")
    @ApiImplicitParam(name = "ProjectTaxCostEntity",value = "项目税金", required = true)
    @PostMapping(value = "/project/prjTaxCost/save" , produces = {"application/json;charset=UTF-8"})
    public String saveOrUpdate(@RequestBody ProjectTaxCostEntity p){
        try {
            projectTaxCostService.saveProjectTaxCost(p);
            return "success";
        }catch (Exception e){
            return "error";
        }
    }
}
