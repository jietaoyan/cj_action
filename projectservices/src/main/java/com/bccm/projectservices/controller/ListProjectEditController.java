package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.ListProjectEditInterface;
import com.bccm.projectservices.Interface.PriceDetailListInterface;
import com.bccm.projectservices.entity.ListProjectEdit;
import com.bccm.projectservices.entity.PriceDetailListEntity;
import com.bccm.projectservices.entity.VO.PrjectCompareVo;
import com.bccm.projectservices.service.ListProjectEditService;
import com.bccm.projectservices.service.PrjectCompareVoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api(value = "ListProjectEdit")
@RestController
public class ListProjectEditController {


    @Autowired
    public ListProjectEditInterface listProjectEditInterface;

    @Autowired
    public ListProjectEditService listProjectEditService;

    @Autowired
    private PrjectCompareVoService prjectCompareVoService;

    @Autowired
    private PriceDetailListInterface priceDetailListInterface;

    @ApiOperation(value = "新建子集项目")
    @PostMapping(value = "/ListProjectEdit/saveProjectInfo",produces = {"application/json;charset=UTF-8"})
    public void saveInfo(@RequestBody ListProjectEdit listProjectEdits)
    {
        listProjectEditInterface.save(listProjectEdits);
    }

    @ApiOperation(value = "查询所有子集项目")
    @GetMapping(value = "/ListProjectEdit/getAllProjectInfo/{id}",produces = {"application/json;charset=UTF-8"})
    public List<ListProjectEdit> getAllListInfo(@PathVariable(value = "id")String id)
    {
//        return listProjectEditInterface.findAll();
       return listProjectEditService.getAllTreeProject(id);
    }

    @ApiOperation(value = "查询选中的子集项目")
    @PostMapping(value = "/ListProjectEdit/getSelectProjectInfo",produces = {"application/json;charset=UTF-8"})
    public List<ListProjectEdit> getSelectListInfo(@RequestBody List<String> guids)
    {
        List<ListProjectEdit> listProjectEdits=listProjectEditInterface.findAllById(guids);
       return listProjectEdits;
    }

    @DeleteMapping(value = "/ListProjectEdit/deleteInforByid/{id}", produces = {"application/json;charset=UTF-8"})
    public String deleteproInformationById(@PathVariable(value = "id")String id){
        listProjectEditService.deleteDepentId(id);
        priceDetailListInterface.deleteAllInfobyProjectid(id);
        return "success";
    }

    @DeleteMapping(value = "/ListProjectEdit/deleteListInforByid/{id}", produces = {"application/json;charset=UTF-8"})
    public String deleteListInformationById(@PathVariable(value = "id")String id){
        listProjectEditService.deleteDepentId(id);
        return "success";
    }

    @ApiOperation(value="获取根项目的费用")
    @ApiImplicitParam(name = "projectid", value = "根项目id", required = true)
    @GetMapping(value = "/project/PrjListCost/{projectid}",produces = {"application/json;charset=UTF-8"})
    public ListProjectEdit getOne(@PathVariable(value = "projectid") String guid){
        return listProjectEditInterface.findById(guid).orElse(new ListProjectEdit());
    }

    @ApiOperation(value="获取根项目id下所有项目和清单")
    @ApiImplicitParam(name = "projectid", value = "根项目id", required = true)
    @GetMapping(value = "project/prjCompare/{projectid}",produces = {"application/json;charset=UTF-8"})
    public List< PrjectCompareVo> getPrjsAndPriceListByRootProjectid(@PathVariable String projectid){
        return prjectCompareVoService.getProjectAndPriceByPrjRootId(projectid);
    }

    @ApiOperation(value="获取项目id及其子级下所有项目")
    @ApiImplicitParam(name = "projectid", value = "根项目id", required = true)
    @GetMapping(value = "project/pricePrjs/{projectid}",produces = {"application/json;charset=UTF-8"})
    public List<ListProjectEdit> getPrjsByRootId(@PathVariable String projectid){
//        List<ListProjectEdit> listProjectEdits = new ArrayList<>();
//        prjectCompareVoService.getAllPrjsByRootId(projectid,listProjectEdits);
//        return listProjectEdits;
        return prjectCompareVoService.getAllByPrjRootGuid(projectid);
    }
    @ApiOperation(value = "查询所有子集项目")
    @GetMapping(value = "/ListProjectEdit/checkChild/{id}",produces = {"application/json;charset=UTF-8"})
    public Boolean getAllListInfoBy(@PathVariable(value = "id")String id)
    {
        Boolean flag=false;
        List<ListProjectEdit> sons=listProjectEditInterface.findAllByParentid(id);
        if(sons!=null&&sons.size()>0)
        {
            flag=true;
        }
        return flag;
    }


}
