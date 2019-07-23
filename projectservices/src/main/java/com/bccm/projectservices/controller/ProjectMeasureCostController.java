package com.bccm.projectservices.controller;

import com.bccm.projectservices.entity.ProjectMeasureCostEntity;
import com.bccm.projectservices.entity.ProjectMeasureDetail;
import com.bccm.projectservices.service.ProjectMeasureCostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "projectMeasureCost")
@RestController
public class ProjectMeasureCostController {

    @Autowired
    ProjectMeasureCostService projectMeasureCostService;

    @ApiOperation(value = "获取该项目id下的措施费")
    @ApiImplicitParam(name = "projectid", value = "项目id", required = true)
    @GetMapping(value = "/project/prjMeasure/{projectid}", produces = {"application/json;charset=UTF-8"})
    public ProjectMeasureCostEntity findByProjectid(@PathVariable String projectid) {
        return projectMeasureCostService.findByProjectid(projectid);
    }

    @ApiOperation(value = "获取措施费下的明细")
    @GetMapping(value = "/project/prjMeasure/detail/{guid}", produces = {"application/json;charset=UTF-8"})
    public List<ProjectMeasureDetail> findAllByGuid(@PathVariable String guid) {
        return projectMeasureCostService.findAllDetailByGuid(guid);
    }

    @ApiOperation(value = "保存项目措施费")
    @PostMapping(value = "/project/prjMeasure/save", produces = {"application/json;charset=UTF-8"})
    public String saveOrUpdateCost(@RequestBody ProjectMeasureCostEntity projectMeasureCostEntity) {
        try {
            projectMeasureCostService.saveOrUpdate(projectMeasureCostEntity);
            return "success";
        } catch (Exception e) {
            //        System.out.println(e.getMessage());
            return "error" + e.getMessage();
        }
    }

    @ApiOperation(value = "保存项目措施费明细")
    @PostMapping(value = "/project/prjMeasure/saveDetail/{fkguid}", produces = {"application/json;charset=UTF-8"})
    public String saveOrUpdateDetailCost(@RequestBody List<ProjectMeasureDetail> projectMeasureDetails
            ,@PathVariable String fkguid) {
        try {
            projectMeasureCostService.saveOrUpdateDetail(projectMeasureDetails,fkguid);
            return "success";
        } catch (Exception e) {
            return "error" + e.getMessage();
        }
    }

}
