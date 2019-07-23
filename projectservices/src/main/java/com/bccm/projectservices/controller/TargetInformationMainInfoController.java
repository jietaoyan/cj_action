package com.bccm.projectservices.controller;




import com.bccm.projectservices.Interface.TargetInformationMainInfoInterface;
import com.bccm.projectservices.entity.TargetInformationMainEntity;
import com.bccm.projectservices.entity.VO.PageEntity;
import com.bccm.projectservices.service.TargetInformationMainService;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceBridge;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceLine;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceRoad;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceTunnel;
import com.bccm.projectservices.sqlInterface.Engineeringbridgeinterface;
import com.bccm.projectservices.sqlInterface.Engineeringlineinterface;
import com.bccm.projectservices.sqlInterface.Engineeringroadinterface;
import com.bccm.projectservices.sqlInterface.Engineeringtunnelinterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.*;

@Api(value = "TargetInformationMainInfo")
@RestController
public class TargetInformationMainInfoController {
    @Autowired
    TargetInformationMainService targetInformationMainService;

    @Autowired
    TargetInformationMainInfoInterface targetInterface;

    @Autowired
    Engineeringtunnelinterface tunnelinterface;

    @Autowired
    Engineeringbridgeinterface bridgeinterface;

    @Autowired
    Engineeringlineinterface lineinterface;

    @Autowired
    Engineeringroadinterface roadinterface;

    @ApiOperation(value="获取tunn数据")
    @GetMapping(value = "/TargetInformationMainInfo/getTunnelInformationMainInfo")
    public List<EngineeringGuidanceTunnel> selectAll()
    {
        List<EngineeringGuidanceTunnel> bagClearHistories=tunnelinterface.findAll();
        return bagClearHistories;
    }

    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有tunnel信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "prjname", value = "项目", required = false),
            @ApiImplicitParam(name = "local", value = "地点", required = false),
            @ApiImplicitParam(name = "date", value = "时间", required = false)
    })
    @GetMapping(value = "/TargetInformationMainInfo/searchTunnelInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public PageEntity<EngineeringGuidanceTunnel> searchTunnelInformationMainInfo(@RequestParam(value="page",required =false)int page, @RequestParam(value="size",required =false)int size,
                                                                                 @RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "local",required =false)String local, @RequestParam(value = "startdate",required =false)String startdate,
                                                                                 @RequestParam(value = "enddate",required =false)String enddate, @RequestParam(value = "date",required =false)String date)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        String prjname_val = "";
        String local_val = "";
        String date_val = "";
        if(!prjname.equals("")){
            prjname_val = "%"+prjname+"%";
        }
        if(!local.equals("")){
            local_val = "%"+local+"%";
        }
        if(!date.equals("")){
            date_val = "%"+date+"%";
        }
        PageEntity<EngineeringGuidanceTunnel> pageEntity = new PageEntity<>();
        int page_val = Integer.parseInt(String.valueOf(page));
        int pageSize_val =Integer.parseInt(String.valueOf(size));
        int offNum = page_val * pageSize_val;

        List <EngineeringGuidanceTunnel> targetInformations = tunnelinterface.getTunnel(prjname_val,local_val,date_val,offNum,pageSize_val);

        int count = tunnelinterface.getTunnelCount(prjname_val,local_val,date_val);
        pageEntity.setContent(targetInformations);
        pageEntity.setTotalElements(count);
        return pageEntity;
    }


    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有bridge信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "prjname", value = "项目", required = false),
            @ApiImplicitParam(name = "local", value = "地点", required = false),
            @ApiImplicitParam(name = "date", value = "时间", required = false)
    })
    @GetMapping(value = "/TargetInformationMainInfo/searchBridgeInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public PageEntity<EngineeringGuidanceBridge> searchBridgeInformationMainInfo(@RequestParam(value="page",required =false)int page, @RequestParam(value="size",required =false)int size,
                                                                                 @RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "local",required =false)String local, @RequestParam(value = "startdate",required =false)String startdate,
                                                                                 @RequestParam(value = "enddate",required =false)String enddate, @RequestParam(value = "date",required =false)String date)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        String prjname_val = "";
        String local_val = "";
        String date_val = "";
        if(!prjname.equals("")){
            prjname_val = "%"+prjname+"%";
        }
        if(!local.equals("")){
            local_val = "%"+local+"%";
        }
        if(!date.equals("")){
            date_val = "%"+date+"%";
        }
        PageEntity<EngineeringGuidanceBridge> pageEntity = new PageEntity<>();
        int page_val = Integer.parseInt(String.valueOf(page));
        int pageSize_val =Integer.parseInt(String.valueOf(size));
        int offNum = page_val * pageSize_val;

        List <EngineeringGuidanceBridge> bridgeInformations = bridgeinterface.getBridge(prjname_val,local_val,date_val,offNum,pageSize_val);

        int count = bridgeinterface.getBridgeCount(prjname_val,local_val,date_val);
        pageEntity.setContent(bridgeInformations);
        pageEntity.setTotalElements(count);
        return pageEntity;
    }


    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有Line信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "prjname", value = "项目", required = false),
            @ApiImplicitParam(name = "local", value = "地点", required = false),
            @ApiImplicitParam(name = "date", value = "时间", required = false)
    })
    @GetMapping(value = "/TargetInformationMainInfo/searchLineInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public PageEntity<EngineeringGuidanceLine> searchLineInformationMainInfo(@RequestParam(value="page",required =false)int page, @RequestParam(value="size",required =false)int size,
                                                                             @RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "local",required =false)String local, @RequestParam(value = "startdate",required =false)String startdate,
                                                                             @RequestParam(value = "enddate",required =false)String enddate, @RequestParam(value = "date",required =false)String date)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        String prjname_val = "";
        String local_val = "";
        String date_val = "";
        if(!prjname.equals("")){
            prjname_val = "%"+prjname+"%";
        }
        if(!local.equals("")){
            local_val = "%"+local+"%";
        }
        if(!date.equals("")){
            date_val = "%"+date+"%";
        }
        PageEntity<EngineeringGuidanceLine> pageEntity = new PageEntity<>();
        int page_val = Integer.parseInt(String.valueOf(page));
        int pageSize_val =Integer.parseInt(String.valueOf(size));
        int offNum = page_val * pageSize_val;

        List <EngineeringGuidanceLine> lineInformations = lineinterface.getLine(prjname_val,local_val,date_val,offNum,pageSize_val);

        int count = lineinterface.getLineCount(prjname_val,local_val,date_val);
        pageEntity.setContent(lineInformations);
        pageEntity.setTotalElements(count);
        return pageEntity;
    }


    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有road信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "prjname", value = "项目", required = false),
            @ApiImplicitParam(name = "local", value = "地点", required = false),
            @ApiImplicitParam(name = "date", value = "时间", required = false)
    })
    @GetMapping(value = "/TargetInformationMainInfo/searchRoadInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public PageEntity<EngineeringGuidanceRoad> searchRoadInformationMainInfo(@RequestParam(value="page",required =false)int page, @RequestParam(value="size",required =false)int size,
                                                                             @RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "local",required =false)String local, @RequestParam(value = "startdate",required =false)String startdate,
                                                                             @RequestParam(value = "enddate",required =false)String enddate, @RequestParam(value = "date",required =false)String date)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        String prjname_val = "";
        String local_val = "";
        String date_val = "";
        if(!prjname.equals("")){
            prjname_val = "%"+prjname+"%";
        }
        if(!local.equals("")){
            local_val = "%"+local+"%";
        }
        if(!date.equals("")){
            date_val = "%"+date+"%";
        }
        PageEntity<EngineeringGuidanceRoad> pageEntity = new PageEntity<>();
        int page_val = Integer.parseInt(String.valueOf(page));
        int pageSize_val =Integer.parseInt(String.valueOf(size));
        int offNum = page_val * pageSize_val;

        List <EngineeringGuidanceRoad> roadInformations = roadinterface.getRoad(prjname_val,local_val,date_val,offNum,pageSize_val);

        int count = roadinterface.getRoadCount(prjname_val,local_val,date_val);
        pageEntity.setContent(roadInformations);
        pageEntity.setTotalElements(count);
        return pageEntity;
    }









    //默认显示所有劳务信息
    @ApiOperation(value = "默认显示所有项目信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value = "/TargetInformationMainInfo/getTargetInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<TargetInformationMainEntity> sTargetInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<TargetInformationMainEntity> targetInformations = targetInformationMainService.getTargetInformationMainInfo(pageable);
        Iterator iter = targetInformations.iterator();
        return targetInformations;
    }


    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有项目信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "prjname", value = "项目", required = false),
            @ApiImplicitParam(name = "name", value = "名称", required = false),
            @ApiImplicitParam(name = "inputdate", value = "时间", required = false)
    })
    @GetMapping(value = "/TargetInformationMainInfo/searchTargetInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<TargetInformationMainEntity> searchTargetInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size,
                                         @RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "name",required =false)String name, @RequestParam(value = "startdate",required =false)String startdate,
                                         @RequestParam(value = "enddate",required =false)String enddate)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<TargetInformationMainEntity> targetInformations = targetInformationMainService.queryTargetInformations( prjname, name, startdate,enddate, pageable);
        Iterator iter = targetInformations.iterator();
        return targetInformations;
    }

    //删除
    @ApiOperation(value = "删除一条数据")
    @DeleteMapping(value = "/TargetInformationMainInfo/deleteTargetInformation/", produces = {"application/json;charset=UTF-8"})
    public String deletelaborInformation(@RequestBody List<TargetInformationMainEntity> Targets){
        targetInformationMainService.deleteByGuids(Targets);
        return "success";
    }

    //保存所有数据
    @ApiOperation(value = "保存多个")
    @PostMapping(value = "/TargetInformationMainInfo/saveAllTargetInformation",produces = {"application/json;charset=UTF-8"})
    public String saveNewTarget(@RequestBody List<TargetInformationMainEntity> targetInfos)throws Exception{

        //Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        //设置用户删除标记默认0
        //targetInfo.setDeleteflag(1);
        String userName = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("X-AUTH-USERNAME");
        //targetInfo.setInputerfullname(userName);
        //projectInfo.setInputdate(nowTime);
        //projectInfo.setType(projectInfo.getType());
        //Long idparam = Long.valueOf(id);
        //projectInfo.setId(idparam);
        //targetInterface.save(targetInfo).getId().toString();
        for(TargetInformationMainEntity targetInfo : targetInfos){
            if(targetInfo.getGuid()==null||targetInfo.getGuid()==""){
                String newGuid = UUID.randomUUID().toString().replace("-","");
                targetInfo.setGuid(newGuid);
            }
            targetInfo.setDeleteflag(1);
        }
        targetInterface.saveAll(targetInfos);
        return "success";

    }

}
