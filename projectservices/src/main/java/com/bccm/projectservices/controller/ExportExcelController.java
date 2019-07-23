package com.bccm.projectservices.controller;

import com.bccm.projectservices.entity.VO.PrjectCompareVo;
import com.bccm.projectservices.service.ProjectExcelExport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Api(value = "ExportExcel")
@RestController
public class ExportExcelController {

    @Autowired
    private ProjectExcelExport projectExcelExport;

    public static final Logger logger = LoggerFactory.getLogger(ExportExcelController.class);

    @ApiOperation(value = "下载建设项目excel报表")
    @GetMapping(value = "/ProjectmanageInfor/downLoad/{id}/{user}",produces = {"application/json;charset=UTF-8"})
    public void downLoadExcelReport(@PathVariable String id, @PathVariable String user
            , HttpServletResponse response, HttpServletRequest request) {
        String fileName = "projectcost.xlsx";
        BufferedOutputStream bufferedOut = null;
        try {
            bufferedOut = new BufferedOutputStream(response.getOutputStream());
            setResponse(response,fileName);
            XSSFWorkbook excel = null;
            synchronized (projectExcelExport){
                if ("admin".equals(user)){
                    //管理员权限
                    excel = projectExcelExport.getPorjectAllExcelById(id);
                }else {
                    excel = projectExcelExport.getPorjectExcelById(id);
                }
            }
            excel.write(bufferedOut);
            bufferedOut.flush();
        }catch (Exception e){
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally {
            closeBufferedsStream(bufferedOut);
        }
    }

    @ApiOperation(value="对比功能获取页面数据导出Excel")
    @PostMapping(value = "project/prjCompare/downLoad",produces = {"application/json;charset=UTF-8"})
    public void getPrjCompareExcel(@RequestBody List<PrjectCompareVo> prjectCompareVos
            , HttpServletResponse response, HttpServletRequest request){
        String fileName = "prjCompare.xlsx";
        BufferedOutputStream bufferedOut = null;
        try {
            bufferedOut = new BufferedOutputStream(response.getOutputStream());
            setResponse(response,fileName);
            XSSFWorkbook excel = null;
            synchronized (projectExcelExport){
                excel = projectExcelExport.getPrjCompareEXCEL(prjectCompareVos);
            }
            excel.write(bufferedOut);
            bufferedOut.flush();
        }catch (Exception e){
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally {
            closeBufferedsStream(bufferedOut);
        }
    }

    @ApiOperation(value = "下载分部分项项目excel报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "根项目guid", required = true),
            @ApiImplicitParam(name = "permission", value = "权限（admin,user）",required = true)
    })
    @GetMapping(value = "project/prjExceldownLoad/{guid}/{permission}",produces = {"application/json;charset=UTF-8"})
    public void exportPrjExcel(@PathVariable String guid, @PathVariable String permission
            , HttpServletResponse response, HttpServletRequest request){
        String fileName = "prjReport.xlsx";
        BufferedOutputStream bufferedOut = null;
        try {
            bufferedOut = new BufferedOutputStream(response.getOutputStream());
            setResponse(response,fileName);
            XSSFWorkbook excel = null;
            synchronized (projectExcelExport){
                if ("admin".equals(permission)){
                    //管理员权限
                    excel = projectExcelExport.getSinglePrjAllReport(guid);
                }else {
                    excel = projectExcelExport.getSinglePrjReport(guid);
                }
            }
            excel.write(bufferedOut);
            bufferedOut.flush();
        }catch (Exception e){
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }finally {
            closeBufferedsStream(bufferedOut);
        }
    }

    private HttpServletResponse setResponse(HttpServletResponse response,String fileName){
        //头信息设置
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        }catch (Exception e){
//            e.printStackTrace();
            logger.error(e.getMessage(),e);
        }
        return response;
    }
    //关闭输出流
    private void closeBufferedsStream(BufferedOutputStream bufferedOut){
        if (bufferedOut!= null){
            try {
                bufferedOut.close();
            } catch (IOException e) {
//                e.printStackTrace();
                logger.error(e.getMessage(),e);
            }
        }
    }
}
