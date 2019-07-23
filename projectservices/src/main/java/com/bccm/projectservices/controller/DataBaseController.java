package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.BagClearDatabaseInterface;
import com.bccm.projectservices.Interface.CaseDatabaseInterface;
import com.bccm.projectservices.Interface.ListDatabaseInterface;
import com.bccm.projectservices.Interface.MaterialDatabaseInterface;
import com.bccm.projectservices.entity.BagClearDatabase;
import com.bccm.projectservices.entity.ListDatabase;
import com.bccm.projectservices.entity.MaterialDatabase;
import com.bccm.projectservices.service.BagClearDatabaseService;
import com.bccm.projectservices.service.DataBaseService;
import com.bccm.projectservices.service.MaterialDatabaseService;
import com.bccm.projectservices.service.PriceDetailListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Api(value = "DataBaseController")
@RestController
public class DataBaseController {
    public static final Logger logger = LoggerFactory.getLogger(DataBaseController.class);
    @Autowired
    public ListDatabaseInterface listDatabaseInterface;

    @Autowired
    public BagClearDatabaseInterface bagClearDatabaseInterface;

    @Autowired
    public MaterialDatabaseInterface materialDatabaseInterface;

    @Autowired
    public DataBaseService dataBaseService;

    @Autowired
    public CaseDatabaseInterface caseDatabaseInterface;

    @Autowired
    public BagClearDatabaseService bagClearDatabaseService;

    @Autowired
    public PriceDetailListService priceDetailListService;

    @ApiOperation(value = "根据项目名获取清包数据")
    @GetMapping(value = "/database/getBagClearDatabse/{id}")
    public List<BagClearDatabase> getBagClearDatabse(@PathVariable(value = "id")String id)
    {
        return bagClearDatabaseInterface.findAllByProjectname(id);
    }

    @ApiOperation(value = "获取所有清包数据节点")
    @GetMapping(value = "/database/getAllBagClearDatabse")
    public List<BagClearDatabase> getAllBagClearDatabse()
    {
        return bagClearDatabaseInterface.findkindOfProject();
    }

//    @ApiOperation(value = "获取数据")
//    @GetMapping(value = "/database/getMaterialDatabse/{id}")
//    public List<MaterialDatabase> getMaterialDatabse(@PathVariable(value = "id")String id)
//    {
//        return materialDatabaseInterface.findAll();
//    }

    @ApiOperation(value = "获取所有材料数据")
    @GetMapping(value = "/database/getAllMaterialDatabse")
    public List<MaterialDatabase> getAllMaterialDatabse()
    {
        return materialDatabaseInterface.findAll();
    }

    @ApiOperation(value = "根据国标和使用状态取所有清单数据")
    @GetMapping(value = "/database/getListDatabse")
    public List<ListDatabase> getListDatabse(@RequestParam(value = "id")String id,@RequestParam(value = "state")String state)
    {
        return listDatabaseInterface.findAllByStandardNameAndState(id,state);
    }

    @ApiOperation(value = "根据项目名和状态获取清包数据")
    @GetMapping(value = "/database/getAllBagDatabseByProjectIdAndState")
    public List<BagClearDatabase> getBagDatabse(@RequestParam(value = "id")String id,@RequestParam(value = "state")String state)
    {
        return bagClearDatabaseInterface.findAllByProjectnameAndState(id,state);
    }


    @ApiOperation(value = "获取数据")
    @GetMapping(value = "/database/getListDatabseForPrcice/{id}")
    public List<ListDatabase> getListDatabseforprice(@PathVariable(value = "id")String id)
    {
        return listDatabaseInterface.findAllByStandardName(id);
    }



    @ApiOperation(value = "获取数据")
    @GetMapping(value = "/database/getAllListDatabse")
    public List<ListDatabase> getAllListDatabse()
    {
        return listDatabaseInterface.findAllKindsStandardName();
    }


    @ApiOperation(value = "根据条件选择清单数据库信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value="/database/selectByCondition")
    public Page<ListDatabase> getAllByDiffCondition(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size,
                                                    @RequestParam(value = "flag",required =false)String flag, @RequestParam(value = "name",required =false)String name, @RequestParam(value = "unit",required =false)String unit, @RequestParam(value = "price",required =false)String price
            , @RequestParam(value = "standname",required =false)String standname, @RequestParam(value = "treename",required =false)String treename
    )throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "flag");
        PageRequest pageable = new PageRequest(page, size, sort);
        BigDecimal priceD=new BigDecimal(0);
        if("undefined".equals(price))
        {
            price="";
        }
        if("undefined".equals(flag))
        {
            flag="";
        }
        if("undefined".equals(unit))
        {
            unit="";
        }
        if("undefined".equals(name))
        {
            name="";
        }
        if("undefined".equals(standname))
        {
            standname="";
        }
        if("undefined".equals(treename))
        {
            treename="";
        }
        Page<ListDatabase> projectInformations = dataBaseService.getAllByCond( flag, name, unit,price, pageable,standname,treename);
        Iterator iter = projectInformations.iterator();
        return projectInformations;
    }

    @ApiOperation(value = "显示所有清包数据库信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value = "/database/getSelecBagInformation", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<BagClearDatabase> getBagInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "flag");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<BagClearDatabase> laborInformations = dataBaseService.getAllBagInfoInformationMainInfo(pageable);
        Iterator iter = laborInformations.iterator();
        return laborInformations;
    }

    @ApiOperation(value = "默认显示所有清单数据库下拉框和树表结构信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value = "/database/getAllListInformation", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<ListDatabase> getAllListInfoMation(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "flag");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<ListDatabase> laborInformations = dataBaseService.getAllListInfoInformationMainInfo(pageable);
        Iterator iter = laborInformations.iterator();
        return laborInformations;
    }

    @ApiOperation(value = "默认显示所有清单数据库信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value = "/database/getSelectListInformation", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<ListDatabase> sLaborInformationMainInfo(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "flag");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<ListDatabase> laborInformations = dataBaseService.getSelectListInfoInformationMainInfo(pageable);
        Iterator iter = laborInformations.iterator();
        return laborInformations;
    }


    @ApiOperation(value = "显示所有清包数据库下拉框和树表结构信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value = "/database/getAllBagInformation", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Page<BagClearDatabase> getAllBagInfoMation(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size)throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "flag");
        PageRequest pageable = new PageRequest(page, size, sort);
        //String name = "%" + column1 + "%";
        Page<BagClearDatabase> laborInformations = dataBaseService.getSelectBagInfoInformationMainInfo(pageable);
        Iterator iter = laborInformations.iterator();
        return laborInformations;
    }

    @ApiOperation(value = "保存清单数据")
    @PostMapping(value = "/database/saveListData",produces = {"application/json;charset=UTF-8"})
    public String saveAllListDatabse(@RequestBody List<ListDatabase> listDatabases)
    {
        try
        {
            listDatabaseInterface.saveAll(listDatabases);
        }catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return "SUCCESS";
    }

    @ApiOperation(value = "保存清包数据")
    @PostMapping(value = "/database/saveBagData/{rate}",produces = {"application/json;charset=UTF-8"})
    public List<BagClearDatabase> saveAllBagDatabse(@RequestBody List<BagClearDatabase> bagClearDatabases, @PathVariable Double rate)
    {
        List<BagClearDatabase> overrateList=new ArrayList<>();
        Boolean cflag=true;
        for(BagClearDatabase bagClearDatabase:bagClearDatabases)
        {
            String flag=bagClearDatabase.getFlag();
            if(flag!=null)
            {
                BagClearDatabase compareBag=bagClearDatabaseInterface.findByFlag(flag);
                BigDecimal compareprice=compareBag.getPrice();
                BigDecimal newPrice=bagClearDatabase.getPrice();
                if((compareprice!=null&&!compareprice.equals(BigDecimal.valueOf(0))&&(newPrice!=null&&!newPrice.equals(BigDecimal.valueOf(0)))))
                {
                    BigDecimal comparerate=newPrice.divide(compareprice,1,BigDecimal.ROUND_HALF_UP);
                    Boolean flag1=true;
                    if(comparerate.subtract(new BigDecimal(1)).compareTo(new BigDecimal(0))>=0)
                    {
                        flag1=comparerate.subtract(new BigDecimal(1)).compareTo(BigDecimal.valueOf(rate/100))>=0;
                        if(flag1)
                        {
                            System.out.println(flag);
                        }
                    }
                    else
                        {
                            flag1=comparerate.subtract(new BigDecimal(1)).compareTo(BigDecimal.valueOf(rate/100))>=0;
                            System.out.println(flag);
                        }

                    if(flag1)
                    {
                        overrateList.add(bagClearDatabase);
                        cflag=false;
                    }
                }
            }

        }
        if(cflag)
        {
            try
            {
                bagClearDatabaseService.saveAll(bagClearDatabases);
            }catch (Exception e)
            {
                logger.error(e.getMessage());

            }
        }

        return overrateList;
    }



    @ApiOperation(value = "根据条件选择清包数据库信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码默认0第一页", required = false),
            @ApiImplicitParam(name = "size", value = "每页条数默认15每页十五条", required = false)
    })
    @GetMapping(value="/database/selectBagDataByCondition")
    public Page<BagClearDatabase> getBagDataByDiffCondition(@RequestParam(value="page",defaultValue = "0")int page, @RequestParam(value="size",defaultValue = "15")int size,
                                                            @RequestParam(value = "flag",required =false)String flag, @RequestParam(value = "name",required =false)String name, @RequestParam(value = "unit",required =false)String unit, @RequestParam(value = "price",required =false)String price
            , @RequestParam(value = "projectname",required =false)String projectname, @RequestParam(value = "treename",required =false)String treename,@RequestParam(value = "relateMaterialName1",required =false)String relateMaterialName1,@RequestParam(value = "relateMaterialName2",required =false)String relateMaterialName2
            ,@RequestParam(value = "pricefrom",required =false)String pricefrom
    )throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC, "flag");
        PageRequest pageable = new PageRequest(page, size, sort);
        BigDecimal priceD=new BigDecimal(0);
        if("undefined".equals(price))
        {
            price="";
        }
        if("undefined".equals(flag))
        {
            flag="";
        }
        if("undefined".equals(unit))
        {
            unit="";
        }
        if("undefined".equals(name))
        {
            name="";
        }
        if("undefined".equals(projectname))
        {
            projectname="";
        }
        if("undefined".equals(treename))
        {
            treename="";
        }if("undefined".equals(relateMaterialName1))
        {
            relateMaterialName1="";
        }if("undefined".equals(relateMaterialName2))
        {
            relateMaterialName2="";
        }
        if("undefined".equals(pricefrom))
        {
            pricefrom="";
        }
        Page<BagClearDatabase> projectInformations = dataBaseService.getBagInfoByCond( flag, name, unit,price, pageable,projectname,treename,relateMaterialName1,relateMaterialName2,pricefrom);
        Iterator iter = projectInformations.iterator();
        return projectInformations;
    }

    @PostMapping(value = "/database/deleteListDatabaseByid", produces = {"application/json;charset=UTF-8"})
    public String deleteListInformationById(@RequestBody List<ListDatabase> id){
        List<String> idlist=new ArrayList<String>();
        if(id!=null&&id.size()>0)
        {
            listDatabaseInterface.deleteAll(id);
        }
        return "success";
    }

    @GetMapping(value="/database/getMaterialByName")
    public List<MaterialDatabase> getMaterialByName(@RequestParam (value="name")String name)
    {
        List<MaterialDatabase> materialDatabaseList=new ArrayList<MaterialDatabase>();
        if(name!=null)
        {
            materialDatabaseList= materialDatabaseInterface.findAllofName("%"+name+"%");
        }
        return materialDatabaseList;
    }

    @GetMapping(value="/database/getListByName")
    public List<ListDatabase> getListByName(@RequestParam (value="name")String name,@RequestParam (value="mlocation")String mlocation)
    {
        List<ListDatabase> listDatabases=new ArrayList<ListDatabase>();
        if(name!=null)
        {
            listDatabases= listDatabaseInterface.findAllofName("%"+name+"%");
        }
        return listDatabases;
    }

    @GetMapping(value="/database/getBagByName")
    public List<BagClearDatabase> getBagByName(@RequestParam (value="name")String name)
    {
        List<BagClearDatabase> bagClearDatabaseList=new ArrayList<BagClearDatabase>();
        if(name!=null)
        {
            bagClearDatabaseList= bagClearDatabaseInterface.findAllOfName("%"+name+"%");
        }
        return bagClearDatabaseList;
    }

    @PostMapping(value = "/database/deleteList", produces = {"application/json;charset=UTF-8"})
    public String deleteListRows(@RequestBody List<ListDatabase> listDatabases) {
        try {
            listDatabaseInterface.deleteAll(listDatabases);
            return "success";
        }catch (Exception e){
            logger.error(e.getMessage());
            return "fail";
        }
    }

    @PostMapping(value = "/database/deletePackage", produces = {"application/json;charset=UTF-8"})
    public String deletePackageRows(@RequestBody List<BagClearDatabase> bagClearDatabases) {
        try {
            bagClearDatabaseInterface.deleteAll(bagClearDatabases);
            return "success";
        }catch (Exception e){
            logger.error(e.getMessage());
            return "fail";
        }
    }

    @ApiOperation(value = "按分页和搜索栏获取数据")
    @GetMapping(value = "/database/get", produces = {"application/json;charset=UTF-8"})
    public Page<MaterialDatabase> getList(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "20") int size
            , @RequestParam(required = false) String type
            , @RequestParam(required = false) String flag
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) String feature
            , @RequestParam(required = false) String msupplier) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC,"sysid"));
        orders.add(new Sort.Order(Sort.Direction.DESC,"inputdate"));
        Sort sort = Sort.by(orders);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return priceDetailListService.getMaterialList(pageRequest,type, flag, name, feature, msupplier);
    }
}
