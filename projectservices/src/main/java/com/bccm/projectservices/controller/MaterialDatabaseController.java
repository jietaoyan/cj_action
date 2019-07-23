package com.bccm.projectservices.controller;

import com.bccm.projectservices.entity.MaterialDatabase;
import com.bccm.projectservices.entity.MaterialDatabaseDetail;
import com.bccm.projectservices.service.MaterialDatabaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MaterialDatabaseController {

    @Autowired
    MaterialDatabaseService materialDatabaseService;

    @ApiOperation(value = "按分页和搜索栏获取数据")
    @GetMapping(value = "/baseList/materialList/get", produces = {"application/json;charset=UTF-8"})
    public Page<MaterialDatabase> getList(@RequestParam(defaultValue = "0") int page
            , @RequestParam(defaultValue = "20") int size
            , @RequestParam(required = false) String type
            , @RequestParam(required = false) String flag
            , @RequestParam(required = false) String name
            , @RequestParam(required = false) String feature
            , @RequestParam(required = false) String msupplier) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "sysid"));
        orders.add(new Sort.Order(Sort.Direction.DESC, "inputdate"));
        Sort sort = Sort.by(orders);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return materialDatabaseService.getList(pageRequest, type, flag, name, feature, msupplier);
    }

    @ApiOperation(value = "删除材料信息")
    @PostMapping(value = "/baseList/materialList/delete", produces = {"application/json;charset=UTF-8"})
    public String deleteMaterialRows(@RequestBody List<MaterialDatabase> materialDatabases) {
        try {
            materialDatabaseService.deleteMaterialRows(materialDatabases);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "删除材料历史消息")
    @PostMapping(value = "/baseList/materialList/deleteDetail", produces = {"application/json;charset=UTF-8"})
    public String deleteDetailRows(@RequestBody List<MaterialDatabaseDetail> materialDatabaseDetails) {
        try {
            materialDatabaseService.deleteDetailRows(materialDatabaseDetails);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "保存材料清单")
    @PostMapping(value = "/baseList/materialList/save/{rate}", produces = {"application/json;charset=UTF-8"})
    public String saveMaterials(@RequestBody List<MaterialDatabase> materialDatabases, @PathVariable int rate) {
        try {
            String msg = "";
            MaterialDatabase materialDatabase = materialDatabases.get(0);
            //判断是否excel上传或单独修改保存。
            if (materialDatabases.size() == 1 && materialDatabase.getGuid() != null) {
                materialDatabaseService.saveMaterial(materialDatabase);
            } else {
                msg = materialDatabaseService.saveMaterials(materialDatabases, rate);
            }
            return "".equals(msg) ? "success" : msg;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "error";
        }
    }

    @ApiOperation(value = "获取材料库中所有类型")
    @GetMapping(value = "baseList/materialList/getType", produces = {"application/json;charset=UTF-8"})
    public List<String> findAllType() {
        return materialDatabaseService.findAllType();
    }

    @ApiOperation(value = "获取材料库中材料的价格明细")
    @GetMapping(value = "/baseList/materialList/{flag}/detail", produces = {"application/json;charset=UTF-8"})
    public List<MaterialDatabaseDetail> findAllByFkguid(@PathVariable(value = "flag") String flag) {
        return materialDatabaseService.findAllByFlag(flag);
    }

}
