package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.UserPermissionDataInterface;
import com.bccm.projectservices.entity.UserPermissionDataEntity;
import com.bccm.projectservices.service.ProjectmanageInformationMainService;
import com.bccm.projectservices.service.UserPermissionDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "UserPermission")
@RestController
public class UserPermissionController {

    public static final Logger logger = LoggerFactory.getLogger(ProjectmanageInformationMainService.class);

    @Autowired
    UserPermissionDataInterface userPermissionDataInterface;

    @Autowired
    UserPermissionDataService userPermissionDataService;

    @ApiOperation(value="获取所有未审批数据")
    @GetMapping(value = "/UserPermission/getAllPermission")
    public List<UserPermissionDataEntity> getAllPermission()
    {
        return userPermissionDataInterface.findAll();
    }

    @ApiOperation(value = "按分页和搜索栏获取数据")
    @GetMapping(value = "/UserPermission/getPermissionedInfo", produces = {"application/json;charset=UTF-8"})
    public Page<UserPermissionDataEntity> getList(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "20") int size
            , @RequestParam(required = false) String inputerfullname
            , @RequestParam(required = false) String permissionstate
            , @RequestParam(required = false) String permissionlevel
            , @RequestParam(value = "startdate",required =false)String startdate
            , @RequestParam(value = "enddate",required =false)String enddate) {
        Sort sort = new Sort(Sort.Direction.DESC, "inputdatetime");
        if("undefined".equals(inputerfullname))
        {
            inputerfullname="";
        }

        if("undefined".equals(permissionstate))
        {
            permissionstate="";
        }

        if("undefined".equals(permissionlevel))
        {
            permissionlevel="";
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<UserPermissionDataEntity> page1=userPermissionDataService.getList(pageRequest, inputerfullname, permissionstate, permissionlevel, startdate,enddate);
        return page1;
    }

    @ApiOperation(value = "按分页和搜索栏获取数据")
    @GetMapping(value = "/UserPermission/getUsePermissionedInfo", produces = {"application/json;charset=UTF-8"})
    public Page<UserPermissionDataEntity> getUserList(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "20") int size
            , @RequestParam(required = false) String inputerusername
            , @RequestParam(required = false) String permissionstate
            , @RequestParam(required = false) String permissionlevel
            , @RequestParam(value = "startdate",required =false)String startdate
            , @RequestParam(value = "enddate",required =false)String enddate) {
        Sort sort = new Sort(Sort.Direction.DESC, "inputdatetime");
        if("undefined".equals(inputerusername))
        {
            inputerusername="";
        }

        if("undefined".equals(permissionstate))
        {
            permissionstate="";
        }

        if("undefined".equals(permissionlevel))
        {
            permissionlevel="";
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<UserPermissionDataEntity> page1=userPermissionDataService.getUseList(pageRequest, inputerusername, permissionstate, permissionlevel, startdate,enddate);
        return page1;
    }



    @ApiOperation(value="批量保存授权数据")
    @PostMapping(value = "/UserPermission/savePermission",produces = {"appliication/json;charset=UTF-8"})
    public String saveAll(@RequestBody List<UserPermissionDataEntity> caseDatabaseEntityList)
    {
        try {
            userPermissionDataService.savePermission(caseDatabaseEntityList);
        } catch (Exception e) {

            //e.printStackTrace();
            logger.error(e.getMessage(),e);
            return "Fail";
        }
        return "SUCCESS";
    }


    @ApiOperation(value="权限检测数据")
    @GetMapping(value = "/UserPermission/permissionCheck")
    public Boolean permissioncheck(@RequestParam(value = "inputusername") String inputusername)
    {
        Boolean flag=false;
        UserPermissionDataEntity userPermissionDataEntity=userPermissionDataInterface.getPermissionCheck(inputusername);
        if(userPermissionDataEntity!=null)
        {
            flag=true;
        }
        return flag;
    }

    @ApiOperation(value="权限检测")
    @GetMapping(value = "/UserPermission/permissionCheckCount")
    public Boolean permissioncheckCount(@RequestParam(value = "inputusername") String inputusername)
    {
        Boolean flag=false;
        int count =userPermissionDataInterface.getPermissionCheckCount(inputusername);
        if(count > 0)
        {
            flag=true;
        }
        return flag;
    }
}
