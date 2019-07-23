package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.ListProjectEditInterface;
import com.bccm.projectservices.Interface.PriceDetailListInterface;
import com.bccm.projectservices.entity.PriceDetailListEntity;
import com.bccm.projectservices.entity.VO.PriceDetailListEntityVo;
import com.bccm.projectservices.service.PriceDetailListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(value = "priceDetail")
@RestController
public class PriceDetailListController {

    @Autowired
    private PriceDetailListService priceDetailListService;

    static List<String> affectedList=new ArrayList<String>();

    @Autowired
    private ListProjectEditInterface listProjectEditInterface;

    @Autowired
    public PriceDetailListInterface priceDetailListInterface;

    @ApiOperation(value = "按类型获取清包、材料信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "guids",value = "工程项目guids", required = true),
            @ApiImplicitParam(name = "type", value = "类型", required = true)
    })
    @PostMapping(value = "/project/pricePrjs/{type}",produces = {"application/json;charset=UTF-8"})
    public List<PriceDetailListEntity> getPricesByPrjsAndType(@RequestBody List<String> guids , @PathVariable String type){
        return priceDetailListService.findAllByPrjIdAndType(guids,type);
    }
    @ApiOperation(value = "保存材料汇总、价格调整中修改的数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "priceDetailListEntityVo",value = "PriceDetailListEntityVo", required = true),
            @ApiImplicitParam(name = "priceChange", value = "是否需更新价格", required = true)
    })
    @PostMapping(value = "/project/pricePrjs/updatePrices/{priceChange}",produces = {"application/json;charset=UTF-8"})
    public String updatePricesByGuids(
            @RequestBody PriceDetailListEntityVo priceDetailListEntityVo,
            @PathVariable(value = "priceChange") int priceChange){
        try {
            priceDetailListService.updatePricesByGuids(priceDetailListEntityVo,priceChange);
            return "success";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @ApiOperation(value = "保存数据")
    @PostMapping(value = "/project/saveInfo")
    public String saveAllInfo(@RequestBody PriceDetailListEntity priceDetailList)
    {
        priceDetailListService.saveAndHandel(priceDetailList);
        return "SUCCESS";
    }

    @ApiOperation(value = "获取数据")
    @GetMapping(value = "/project/getAllSelected/{id}")
    public List<PriceDetailListEntity> getAllSelected(@PathVariable(value = "id")String id)
    {
        return priceDetailListService.getAllBySelected(id);
    }

    @ApiOperation(value = "删除数据")
    @ApiImplicitParam(name = "id",value = "编号",required = true)
    @PostMapping(value = "/project/deleteByid", produces = {"application/json;charset=UTF-8"})
    public String deleteInforById(@RequestBody List<PriceDetailListEntity> id){
//        affectedList=id;
        Set<String> projectsGuid=new HashSet<String>();
        Set<String> parentGuid=new HashSet<String>();
        for(PriceDetailListEntity allGuid:id)
        {
            String guid=allGuid.getGuid();
            priceDetailListInterface.deleteByIdP(guid);
            projectsGuid.add(allGuid.getProjectid());
            if(!"清单".equals(allGuid.getType()))
            {
                parentGuid.add(allGuid.getParentid());
            }
            else
            {
                List<PriceDetailListEntity> priceDetailListEntities=priceDetailListInterface.findAllByParentid(allGuid.getGuid());
                for(PriceDetailListEntity priceDetailListEntity:priceDetailListEntities)
                {
                    priceDetailListInterface.deleteByIdP(priceDetailListEntity.getGuid());
                }
            }
        }
        priceDetailListService.CountAgain(projectsGuid,parentGuid);
        return "success";
    }

    @ApiOperation(value = "清单详情数据批量导入")
    @PostMapping(value = "/project/saveList", produces = {"application/json;charset=UTF-8"})
    public List<PriceDetailListEntity> saveListForPriceDetail(@RequestBody  List<PriceDetailListEntity> priceDetailListEntityList)
    {
        String returnWord="";
        List<PriceDetailListEntity> priceDetailListEntities=new ArrayList<PriceDetailListEntity>();
        if(priceDetailListEntityList !=null&&priceDetailListEntityList.size()>0)
        {
            priceDetailListEntities=priceDetailListService.HandelListForPrice(priceDetailListEntityList);
        }
         return priceDetailListEntities;
    }


    @ApiOperation(value = "获取数据")
    @GetMapping(value = "/project/getListSelected/{id}")
    public Boolean getListSelected(@PathVariable(value = "id")String id)
    {
        boolean flag=false;
        List<PriceDetailListEntity> priceDetailListEntities=priceDetailListInterface.findAllByProjectidOrderBySortidAscSortidsDesc(id);
        if(priceDetailListEntities!=null&&priceDetailListEntities.size()>0)
        {
            flag=true;
        }
        return flag;
    }

    @ApiOperation(value = "复制项目结构数据")
    @GetMapping(value = "/project/copyListSelected")
    public String copyListSelected(@RequestParam(value = "id")String id,@RequestParam(value="pastedId")String pastedId)
    {
        return priceDetailListService.copyList(id,pastedId);
    }

    @ApiOperation(value = "复制清单数据")
    @GetMapping(value = "/project/copyAllListDetail")
    public void copyAllListSelected(@RequestParam(value = "id")String id,@RequestParam(value="pastedId")String pastedId)
    {
        priceDetailListService.thecopyList(id,pastedId);
    }

}
