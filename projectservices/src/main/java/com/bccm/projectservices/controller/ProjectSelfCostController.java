package com.bccm.projectservices.controller;

import com.bccm.projectservices.entity.ProjectSelfCostEntity;
import com.bccm.projectservices.entity.ProjectSelfMainEntity;
import com.bccm.projectservices.entity.VO.ProjectSelfVo;
import com.bccm.projectservices.service.ProjectSelfCostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ProjectSelfCost")
@RestController
public class ProjectSelfCostController {

    @Autowired
    private ProjectSelfCostService projectSelfCostService;

    @ApiOperation(value = "获取该项目id下的自营成本主表、基本表、其他表信息")
    @ApiImplicitParam(name = "projectid", value = "项目id", required = true)
    @GetMapping(value = "/project/prjSelfCost/{projectid}", produces = {"application/json;charset=UTF-8"})
    public ProjectSelfVo findAllByProjectid(@PathVariable String projectid) {

        return projectSelfCostService.findAllByProjectid(projectid);
    }

    @ApiOperation(value = "获取该项目id下的自营成本总费用")
    @ApiImplicitParam(name = "projectid", value = "项目id", required = true)
    @GetMapping(value = "/project/prjTaxCost/relative/{projectid}", produces = {"application/json;charset=UTF-8"})
    public ProjectSelfMainEntity findByProjectidAndRowid(@PathVariable String projectid) {
        return projectSelfCostService.findByProjectidAndRowid(projectid);
    }

    @ApiOperation(value = "保存该项目id下的自营成本")
    @ApiImplicitParam(name = "selfCosts", value = "自营成本集合", required = true)
    @PostMapping(value = "/project/prjSelfCost/save", produces = {"application/json;charset=UTF-8"})
    public String saveAllByProjectid(@RequestBody ProjectSelfVo selfCosts) {
        try {
            projectSelfCostService.saveAllByPorjectid(selfCosts);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}
