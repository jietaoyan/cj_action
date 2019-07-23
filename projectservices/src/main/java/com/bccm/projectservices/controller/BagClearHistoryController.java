package com.bccm.projectservices.controller;

import com.bccm.projectservices.Interface.BagClearHistoryInterface;
import com.bccm.projectservices.entity.BagClearHistory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BagClearHistoryController {

    @Autowired
    public BagClearHistoryInterface bagClearHistoryInterface;

    @ApiOperation(value="批量保存人材机数据")
    @GetMapping(value = "/history/selectbyflag")
    public List<BagClearHistory> selectByFlag(@RequestParam(required = false) String relatePkid)
    {
        List<BagClearHistory> bagClearHistories=bagClearHistoryInterface.findRelateHistory(relatePkid);
        return bagClearHistories;
    }
}
