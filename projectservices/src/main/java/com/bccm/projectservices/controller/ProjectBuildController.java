package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.ProjectBuildMainEntityInterface;
import com.bccm.projectservices.entity.ProjectBuildMainInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "ProjectBuild")
@RestController
public class ProjectBuildController {

    @Autowired
    public ProjectBuildMainEntityInterface projectBuildMainEntityInterface;

    @ApiOperation(value = "测试接口")
    @GetMapping(value = "/getProjectList",produces = {"application/json;charset=UTF-8"})
    public List<ProjectBuildMainInfo> test(){
        ProjectBuildMainInfo projectBuildMainEntity=new ProjectBuildMainInfo();
        List<ProjectBuildMainInfo> projectBuildMainEntityList=projectBuildMainEntityInterface.findAll();
        return projectBuildMainEntityList;
    }
    @ApiOperation(value="数据插入")
    @PostMapping(value="/insertInfo",produces = {"application/json;charset=UTF-8"})
    public void Insert(@RequestBody List<ProjectBuildMainInfo> projectBuildMainEntityList)
    {
        projectBuildMainEntityInterface.saveAll(projectBuildMainEntityList);
    }
}
