package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.ProjectUserLoginTimeInterface;
import com.bccm.projectservices.Interface.ProjectUserinforInterface;
import com.bccm.projectservices.entity.ProjectmanageUserinforEntity;
import com.bccm.projectservices.entity.ProjectmanageUserinforLoginEntity;
import com.bccm.projectservices.service.ProjectmanageUserinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Api(value = "ProjectmanageUserinfo")
@RestController
public class ProjectmanageUserinfoController {
    @Autowired
    ProjectmanageUserinfoService ProjectUserinfoservice;

    @Autowired
    ProjectUserinforInterface  userinfointerface;

    @Autowired
    ProjectUserLoginTimeInterface logintimeinterface;
    //默认显示所有用户信息
    @ApiOperation(value = "默认显示所有用户信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value = "/UserInformationMainInfo/getProjectUserInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<ProjectmanageUserinforEntity> getInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size)throws Exception{
        //Sort sort = new Sort(Sort.Direction.ASC, "username");
        Sort sort = new Sort(Sort.Direction.DESC, "logintime");
        PageRequest pageable = new PageRequest(page, size, sort);
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        Page<ProjectmanageUserinforEntity> userInformations = ProjectUserinfoservice.getProjectmanageUserInfo(pageable,userName);
        Iterator iter = userInformations.iterator();
        return userInformations;
    }

    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有用户信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "username", value = "用户名", required = false),
            @ApiImplicitParam(name = "userfullname", value = "姓名", required = false),
    })
    @GetMapping(value = "/UserInformationMainInfo/searchProjectUserInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<ProjectmanageUserinforEntity> searchTargetInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size,
                                                                             @RequestParam(value = "username",required =false)String username, @RequestParam(value = "userfullname",required =false)String userfullname)throws Exception{
        Sort sort = new Sort(Sort.Direction.ASC, "username");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<ProjectmanageUserinforEntity> userInformations = ProjectUserinfoservice.queryProjectUserinfos( username, userfullname, pageable);
        Iterator iter = userInformations.iterator();
        return userInformations;

    }

    //保存用户登陆时间
    @ApiOperation(value = "保存登陆时间")
    @PostMapping(value = "/UserInformationMainInfo/saveUserLogintime",produces = {"application/json;charset=UTF-8"})
    public String saveUserLogintime(@RequestBody Map<String ,Object> infos)throws Exception
    {
        JSONObject jsobject = new JSONObject(infos);
        String username = jsobject.getString("username");
        String userfullname = jsobject.getString("userfullname");
        if(username!=null&&username!=""){
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            String guid = UUID.randomUUID().toString().replace("-","");
            logintimeinterface.saveloginTime(nowTime,username,userfullname,guid);
            return "success";
        }else{
            return "defeat";
        }
    }

    //默认显示所有用户登陆时间
    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有用户信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "username", value = "用户名", required = false),
            @ApiImplicitParam(name = "userfullname", value = "姓名", required = false),
    })
    @GetMapping(value = "/UserInformationMainInfo/searchUserLoginTime", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<ProjectmanageUserinforLoginEntity> searchLoginTime(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size,
                                                                              @RequestParam(value = "username",required =false)String username, @RequestParam(value = "userfullname",required =false)String userfullname)throws Exception{
        Sort sort = new Sort(Sort.Direction.ASC, "username");
        PageRequest pageable = new PageRequest(page, size, sort);
        Page<ProjectmanageUserinforLoginEntity> userInformations = ProjectUserinfoservice.queryUserLogininfos( username, userfullname, pageable);
        Iterator iter = userInformations.iterator();
        return userInformations;

    }
}
