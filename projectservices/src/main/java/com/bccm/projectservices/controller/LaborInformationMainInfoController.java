package com.bccm.projectservices.controller;


import com.bccm.projectservices.Interface.LaborInformationMainInfoInterface;
import com.bccm.projectservices.entity.LaborInformationMainEntity;
import com.bccm.projectservices.entity.VO.PageEntity;
import com.bccm.projectservices.service.LaborInformationMainService;
import com.bccm.projectservices.sqlEntity.EngineeringGuidanceCompany;
import com.bccm.projectservices.sqlInterface.EngineeringCompanyinterface;
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

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Api(value = "LaborInformationMainInfo")
@RestController
public class LaborInformationMainInfoController {
    @Autowired
    LaborInformationMainService laborInformationMainService;

    @Autowired
    LaborInformationMainInfoInterface laborInterface;

    @Autowired
    EngineeringCompanyinterface companyinterface;

    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有company信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "prjname", value = "项目", required = false),
            @ApiImplicitParam(name = "local", value = "地点", required = false),
            @ApiImplicitParam(name = "date", value = "时间", required = false)
    })
    @GetMapping(value = "/LaborInformationMainInfo/searchCompanyInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public PageEntity<EngineeringGuidanceCompany> searchRoadInformationMainInfo(@RequestParam(value="page",required =false)int page, @RequestParam(value="size",required =false)int size,
                                                                                @RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "companyname",required =false)String companyname,
                                                                                @RequestParam(value = "type",required =false)String type)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        String prjname_val = "";
        String local_val = "";
        String type_val = "";
        if(!prjname.equals("")){
            prjname_val = "%"+prjname+"%";
        }
        if(!companyname.equals("")){
            local_val = "%"+companyname+"%";
        }
        if(!type.equals("")){
            type_val = "%"+type+"%";
        }
        PageEntity<EngineeringGuidanceCompany> pageEntity = new PageEntity<>();
        int page_val = Integer.parseInt(String.valueOf(page));
        int pageSize_val =Integer.parseInt(String.valueOf(size));
        int offNum = page_val * pageSize_val;

        List <EngineeringGuidanceCompany> roadInformations = companyinterface.getCompany(prjname_val,local_val,type_val,offNum,pageSize_val);

        int count = companyinterface.getCompanyCount(prjname_val,local_val,type_val);
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
    @GetMapping(value = "/LaborInformationMainInfo/getLaborInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<LaborInformationMainEntity> sLaborInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<LaborInformationMainEntity> laborInformations = laborInformationMainService.getLaborInformationMainInfo(pageable);
        Iterator iter = laborInformations.iterator();
        return laborInformations;
    }


    //String column1, String companyname, String type, String content, String companypath多条件查询
    @ApiOperation(value = "按条件查询所有项目信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false),
            @ApiImplicitParam(name = "prjname", value = "项目名称", required = false),
            @ApiImplicitParam(name = "companypath", value = "劳务公司地址", required = false),
            @ApiImplicitParam(name = "companyname", value = "劳务公司", required = false),
            @ApiImplicitParam(name = "type", value = "分包类型", required = false),
            @ApiImplicitParam(name = "content", value = "内容", required = false)
    })

    @GetMapping(value = "/LaborInformationMainInfo/searchLaborInformationMainInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<LaborInformationMainEntity> searchLaborInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size,
                                         @RequestParam(value = "prjname",required =false)String prjname, @RequestParam(value = "companypath",required =false)String companypath, @RequestParam(value = "companyname",required =false)String companyname,
                                         @RequestParam(value = "type",required =false)String type, @RequestParam(value = "content",required =false)String content)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "inputdate");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<LaborInformationMainEntity> laborInformations = laborInformationMainService.queryLaborInformations( prjname, companyname, type, companypath, content, pageable);
        Iterator iter = laborInformations.iterator();
        return laborInformations;
    }

    //删除
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping(value = "/LaborInformationMainInfo/deletelaborInformation", produces = {"application/json;charset=UTF-8"})
    public String deletelaborInformation(@RequestBody List<LaborInformationMainEntity> Labors){
        laborInformationMainService.deleteByGuids(Labors);
        return "success";
    }

    //保存所有数据
    @ApiOperation(value = "保存多个")
    @PostMapping(value = "/LaborInformationMainInfo/saveAllLaborInformation",produces = {"application/json;charset=UTF-8"})
    public String saveNewTarget(@RequestBody List<LaborInformationMainEntity> laborInfos)throws Exception{

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
        for(LaborInformationMainEntity laborInfo : laborInfos){
            if(laborInfo.getGuid()==""||laborInfo.getGuid()==null){
                String newGuid = UUID.randomUUID().toString().replace("-","");
                laborInfo.setGuid(newGuid);
            }
            laborInfo.setDeleteflag(1);
        }
        laborInterface.saveAll(laborInfos);
        return "success";

    }

}
