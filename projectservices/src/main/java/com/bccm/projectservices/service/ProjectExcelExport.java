package com.bccm.projectservices.service;

import com.bccm.projectservices.Interface.*;
import com.bccm.projectservices.entity.*;
import com.bccm.projectservices.entity.VO.PrjectCompareVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProjectExcelExport {

    @Autowired
    ProjectmanageInformationMainInterface projectmanageInformationMainInterface;

    @Autowired
    ListProjectEditInterface listProjectEditInterface;

    @Autowired
    PriceDetailListInterface priceDetailListInterface;

    @Autowired
    ProjectMeasureCostInterface projectMeasureCostInterface;

    @Autowired
    ProjectMeasureDetailInterface projectMeasureDetailInterface;

    @Autowired
    ProjectSelfMainInterface projectSelfMainInterface;

    @Autowired
    ProjectSelfOtherInterface projectSelfOtherInterface;

    @Autowired
    ProjectSelfCostInterface projectSelfCostInterface;

    @Autowired
    ProjectTaxCostInterface projectTaxCostInterface;

    private XSSFWorkbook EXCEL;
    private XSSFCellStyle titleStyle;
    private XSSFCellStyle title2Style;
    private XSSFCellStyle tableTitleStyle;
    private XSSFCellStyle tableCellCenterStyle;
    private XSSFCellStyle tableCellLeftStyle;
    private XSSFCellStyle tableCellRightStyle;
    private XSSFCellStyle tableCellRightMoneytStyle;
    private XSSFCellStyle tableCellRightMoneyColortStyle;
    private XSSFRow row;
    private XSSFCell cell;
    private XSSFFont font;
    private CellRangeAddress region;
    private int isAllExport = 0;//是否导出材料和清包，0不导出(5张表)，2导出（7张表）。
    private int psOhterSize = 0;
    /**
     * 单元格样式初始化
     */
    private void initCellStyle() {
        this.titleStyle = setCellStyle(HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 20, true, false
                , false, 0);
        this.title2Style = setCellStyle(HorizontalAlignment.LEFT, VerticalAlignment.CENTER, 14, false, false
                , false, 0);
        this.tableTitleStyle = setCellStyle(HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 12, false, false
                , true, 1);
        this.tableCellCenterStyle = setCellStyle(HorizontalAlignment.CENTER, VerticalAlignment.CENTER, 12, false, false
                , true, 0);
        this.tableCellLeftStyle = setCellStyle(HorizontalAlignment.LEFT, VerticalAlignment.CENTER, 12, false, false
                , true, 0);
        this.tableCellLeftStyle.setWrapText(true);//增加自动换行
        this.tableCellRightStyle = setCellStyle(HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, 12, false, false
                , true, 0);
        this.tableCellRightMoneytStyle = setCellStyle(HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, 12, false, true
                , true, 0);
        this.tableCellRightMoneyColortStyle = setCellStyle(HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, 12, false, true
                , true, 2);
    }

    /**
     * 生成单个分部分项费用表，公共方法
     *
     * @param guid  分部分项guid
     * @param index 第几个分部分项项目（从0开始）
     */
    private void setSinglePRJExcel(String guid, int index) {
        ListProjectEdit rootProjectEdit = listProjectEditInterface.findById(guid).orElse(new ListProjectEdit());
        List<ListProjectEdit> projectEdits = listProjectEditInterface.findAllByProjectid(guid);
        ProjectMeasureCostEntity pmCost = projectMeasureCostInterface.findByProjectid(rootProjectEdit.getGuid());//措施费
        List<ProjectMeasureDetail> pmDetail = null;//措施费明细
        if (pmCost != null) {
            pmDetail = projectMeasureDetailInterface.findAllByFkguid(pmCost.getGuid());
        }
        int pmDetailSize = pmDetail == null ? 0 : pmDetail.size();
        //TODO 更新自营成本费用
        ProjectSelfMainEntity selfMain = projectSelfMainInterface.findByProjectid(rootProjectEdit.getGuid());

        ProjectTaxCostEntity ptCost = projectTaxCostInterface.findByProjectid(rootProjectEdit.getGuid());//税金

        int sheetIndex = index + 1;
        XSSFSheet PrjFirstSheet = null;
        XSSFSheet PrjSecondSheet = null;
        XSSFSheet PrjThirdSheet = null;
        XSSFSheet PrjFourthSheet = null;
        XSSFSheet PrjFifthSheet = null;
        PrjFirstSheet = EXCEL.createSheet((sheetIndex) + ".1.项目费用汇总");
        if (isAllExport == 0) {
            PrjSecondSheet = EXCEL.createSheet((sheetIndex) + ".2.项目措施费");
            PrjThirdSheet = EXCEL.createSheet((sheetIndex) + ".3.自营成本");
            PrjFourthSheet = EXCEL.createSheet((sheetIndex) + ".4.税金");
            PrjFifthSheet = EXCEL.createSheet((sheetIndex) + ".5.费用汇总");

            setPrjFirstSheet(PrjFirstSheet, rootProjectEdit, projectEdits);
        } else {
            List<Map<String, Object>> bagClears = priceDetailListInterface
                    .findBagClearByRootidaAndType(rootProjectEdit.getGuid());//清包
            List<Map<String, Object>> materials = priceDetailListInterface
                    .findMaterialByRootidaAndType(rootProjectEdit.getGuid());//材料
            XSSFSheet PrjBagClearSheet = EXCEL.createSheet((sheetIndex) + ".2.清包汇总");
            XSSFSheet PrjMaterialSheet = EXCEL.createSheet((sheetIndex) + ".3.材料汇总");
            PrjSecondSheet = EXCEL.createSheet((sheetIndex) + ".4.项目措施费");
            PrjThirdSheet = EXCEL.createSheet((sheetIndex) + ".5.自营成本");
            PrjFourthSheet = EXCEL.createSheet((sheetIndex) + ".6.税金");
            PrjFifthSheet = EXCEL.createSheet((sheetIndex) + ".7.费用汇总");

            setPrjAllFirstSheet(PrjFirstSheet, rootProjectEdit, projectEdits);
            setPrjBagClearSheet(PrjBagClearSheet, rootProjectEdit, bagClears);
            setPrjMaterialSheet(PrjMaterialSheet, rootProjectEdit, materials);
        }

        setPrjSecondSheet(PrjSecondSheet, rootProjectEdit, pmCost, pmDetail, index);
        setPrjThirdSheet(PrjThirdSheet, rootProjectEdit, selfMain);
        setPrjFourthSheet(PrjFourthSheet, rootProjectEdit, ptCost, index, pmDetailSize);
        setPrjFifthSheet(PrjFifthSheet, rootProjectEdit, index, pmDetailSize);
    }

    /**
     * 建设项目生成表格，包含清包、材料
     *
     * @param id 建设项目id
     * @return
     */
    public XSSFWorkbook getPorjectAllExcelById(String id) {
        this.EXCEL = new XSSFWorkbook();
        this.isAllExport = 2;
        initCellStyle();

        ProjectmanageInformationMainEntity projectRoot = null;//建设工程
        List<ProjectmanageInformationMainEntity> projectCosts = new ArrayList<>();//测算文件

        List<ProjectmanageInformationMainEntity> rootPrjs = projectmanageInformationMainInterface.findAllByRootid(id);
        for (ProjectmanageInformationMainEntity p : rootPrjs) {
            if (p.getLayerid() == 2) {
                projectCosts.add(p);
            } else if (p.getLayerid() == 0) {
                projectRoot = p;
            }
        }

        if (projectCosts.size() <= 0) return null;
        if (projectRoot != null) {
            setFirstSheet(projectRoot, projectCosts);  //第一张表
        }

        int index = 0;
        //每个测算文件需生成表单数量，项目费用、清包汇总、材料汇总、措施费、自营成本、税金、费用汇总
        for (ProjectmanageInformationMainEntity p : projectCosts) {
            setSinglePRJExcel(p.getGuid(), index);
            index++;
        }
        return this.EXCEL;
    }

    /**
     * 建设项目生成表格，不含清包、材料
     *
     * @param id 建设项目id
     * @return
     */
    public XSSFWorkbook getPorjectExcelById(String id) {
        this.EXCEL = new XSSFWorkbook();
        this.isAllExport = 0;
        initCellStyle();

        ProjectmanageInformationMainEntity projectRoot = null;//建设工程
        List<ProjectmanageInformationMainEntity> projectCosts = new ArrayList<>();//测算文件

        List<ProjectmanageInformationMainEntity> rootPrjs = projectmanageInformationMainInterface.findAllByRootid(id);
        for (ProjectmanageInformationMainEntity p : rootPrjs) {
            if (p.getLayerid() == 2) {
                projectCosts.add(p);
            } else if (p.getLayerid() == 0) {
                projectRoot = p;
            }
        }

        if (projectCosts.size() <= 0) return null;
        if (projectRoot != null) {
            setFirstSheet(projectRoot, projectCosts);  //第一张表
        }

        int index = 0;
        //每个测算文件需生成表单数量，项目费用、措施费、自营成本、税金、费用汇总
        for (ProjectmanageInformationMainEntity p : projectCosts) {
            setSinglePRJExcel(p.getGuid(), index);
            index++;
        }
        return this.EXCEL;
    }

    /**
     * 生成单个分部分项报表，包含清包、材料(7张表)
     *
     * @param guid 根项目guid
     * @return
     */
    public XSSFWorkbook getSinglePrjAllReport(String guid) {
        this.EXCEL = new XSSFWorkbook();
        this.isAllExport = 2;
        initCellStyle();

        setSinglePRJExcel(guid, 0);
        return this.EXCEL;
    }

    /**
     * 生成单个分部分项报表，不包含清包、材料（5张表）
     *
     * @param guid 根项目guid
     * @return
     */
    public XSSFWorkbook getSinglePrjReport(String guid) {

        this.EXCEL = new XSSFWorkbook();
        this.isAllExport = 0;
        initCellStyle();

        setSinglePRJExcel(guid, 0);
        return this.EXCEL;
    }

    /**
     * 对比功能模块导出excel
     * @param prjs 对比功能数据
     * @return
     */
    public XSSFWorkbook getPrjCompareEXCEL(List<PrjectCompareVo> prjs) {
        this.EXCEL = new XSSFWorkbook();
        this.isAllExport = 0;
        initCellStyle();
        XSSFSheet sheet = this.EXCEL.createSheet("对比");
        sheet.setColumnWidth(0, 10 * 256);//序号
        sheet.setColumnWidth(1, 15 * 256);//编号
        sheet.setColumnWidth(2, 10 * 256);//类型
        sheet.setColumnWidth(3, 28 * 256);//名称
        sheet.setColumnWidth(4, 12 * 256);//单位
        sheet.setColumnWidth(5, 12 * 256);//工程量
        sheet.setColumnWidth(6, 12 * 256);//单价
        sheet.setColumnWidth(7, 15 * 256);//合价
        sheet.setColumnWidth(8, 12 * 256);//工程量
        sheet.setColumnWidth(9, 12 * 256);//单价
        sheet.setColumnWidth(10, 15 * 256);//合价
        sheet.setColumnWidth(11, 12 * 256);//工程量
        sheet.setColumnWidth(12, 12 * 256);//单价
        sheet.setColumnWidth(13, 15 * 256);//合价
        sheet.setColumnWidth(14, 12 * 256);//工程量比例
        sheet.setColumnWidth(15, 12 * 256);//单价比例
        sheet.setColumnWidth(16, 12 * 256);//合价比例
        sheet.setColumnHidden(17,true);
        sheet.setColumnHidden(18,true);

        for (int i = 0; i <= 4; i++) {
            region = new CellRangeAddress(0, 1, i, i);
            sheet.addMergedRegion(region);
        }
        for (int i = 0; i <= 2; i++) {
            region = new CellRangeAddress(0, 0, 3 * i + 5, 3 * i + 7);
            sheet.addMergedRegion(region);
        }

        String[] titles1 = {"序号", "编号", "类型", "名称", "单位", "当前项", "", "", "对比项"
                , "", "", "增减量", "", "","增减量比例", "", ""};
        setTableTitle(sheet, 0, titles1);
        String[] titles2 = {"", "", "", "", "", "工程量", "单价(元)", "合价(元)", "工程量"
                , "单价(元)", "合价(元)", "工程量", "单价(元)", "合价(元)", "工程量( % )"
                , "单价( % )", "合价( % )","GUID","PARENTID"};
        setTableTitle(sheet, 1, titles2);

        if (prjs != null) {
            int index = 1;
            for (PrjectCompareVo p : prjs) {
                row = sheet.createRow(index + 1);
                row.setHeightInPoints(25);
                setCellValue(0, index, tableCellCenterStyle);
                setCellValue(1, p.getFlag(), tableCellLeftStyle);
                setCellValue(2, p.getType(), tableCellCenterStyle);
                setCellValue(3, p.getProjectname(), tableCellLeftStyle);
                setCellValue(4, p.getUnit(), tableCellLeftStyle);
                setCellValue(5, p.getProjectamount(), tableCellRightStyle);
                setCellValue(6, p.getPrice(), tableCellRightMoneytStyle);
                setCellValue(8, p.getProjectcompareCount(), tableCellRightStyle);
                setCellValue(9, p.getProjectcomparePrice(), tableCellRightMoneytStyle);
                XSSFCellStyle percentStyle = this.EXCEL.createCellStyle();
                percentStyle.setAlignment(HorizontalAlignment.RIGHT);
                percentStyle.setDataFormat(this.EXCEL.createDataFormat().getFormat("0.00"));

                int sheetIndex = index +2;//excle中实际显示的行号
                setCellValueFormula(13, "K"+sheetIndex+"-H"+sheetIndex, tableCellRightMoneytStyle);
                setCellValueFormula(16, "(K"+sheetIndex+"-H"+sheetIndex+")*100/H"+sheetIndex, percentStyle);

                setCellValue(17, p.getGuid(), tableCellCenterStyle);
                setCellValue(18, p.getParentid(), tableCellCenterStyle);
                if ("清单".equals(p.getType())){
                    setCellValueFormula(7, "F"+sheetIndex+"*G"+sheetIndex, tableCellRightMoneytStyle);
                    setCellValueFormula(10, "I"+sheetIndex+"*J"+sheetIndex, tableCellRightMoneytStyle);
                    setCellValueFormula(11, "I"+sheetIndex+"-F"+sheetIndex, tableCellRightStyle);
                    setCellValueFormula(12, "J"+sheetIndex+"-G"+sheetIndex, tableCellRightMoneytStyle);
                    setCellValueFormula(14, "(I"+sheetIndex+"-F"+sheetIndex+")*100/F"+sheetIndex, percentStyle);
                    setCellValueFormula(15, "(J"+sheetIndex+"-G"+sheetIndex+")*100/G"+sheetIndex, percentStyle);
                }
                if ("工程".equals(p.getType())){
                    //层级累加公式
                    setCellValueFormula(7, "SUMIF($S:$S,$R"+sheetIndex+",$H:$H)", tableCellRightMoneytStyle);
                    setCellValueFormula(10, "SUMIF($S:$S,$R"+sheetIndex+",$K:$K)", tableCellRightMoneytStyle);
                }
                index++;
            }
        }
        return this.EXCEL;
    }

    /**
     * 生成项目管理中所有测算文件表格
     *
     * @param projectRoot  根项目
     * @param projectCosts 测算文件集合
     */
    private void setFirstSheet(ProjectmanageInformationMainEntity projectRoot
            , List<ProjectmanageInformationMainEntity> projectCosts) {
        XSSFSheet firstSheet = this.EXCEL.createSheet("建设项目总价表");
        firstSheet.setColumnWidth(0, 10 * 256);
        firstSheet.setColumnWidth(1, 40 * 256);
        firstSheet.setColumnWidth(2, 18 * 256);

        setSheetTitle(firstSheet, "建设项目费汇总表", "建设项目名称：" + projectRoot.getPrjname(), 2);
        String[] titles = {"序号", "项目名称", "编制总价(元)"};
        setTableTitle(firstSheet, 2, titles);

        int size = projectCosts == null ? 0 : projectCosts.size();
        for (int i = 0; i < size; i++) {
            row = firstSheet.createRow(2 + i + 1);
            row.setHeightInPoints(25);
            setCellValue(0, i + 1, tableCellCenterStyle);
            setCellValue(1, projectCosts.get(i).getPrjname(), tableCellLeftStyle);
            String rootPrjCost = "'" + (i + 1) + ".1.项目费用汇总'!H4";
            setCellValueFormula(2, rootPrjCost, tableCellRightMoneytStyle);
        }

        this.region = new CellRangeAddress(3 + size, 3 + size, 0, 1);
        firstSheet.addMergedRegion(region);

        row = firstSheet.createRow(3 + size);
        row.setHeightInPoints(25);

        setCellValue(0, "合计", tableCellRightMoneyColortStyle);
        setCellValueFormula(2, "sum(C4:C" + (size + 3) + ")", tableCellRightMoneyColortStyle);
    }

    /**
     * 项目预算书表格(含清包、材料)
     *
     * @param sheet           sheet
     * @param rootProjectEdit 根项目
     * @param projectEdits    项目集合
     */
    private void setPrjAllFirstSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit
            , List<ListProjectEdit> projectEdits) {

        //按层级排序
        LinkedList<ListProjectEdit> sortProjectEdits = sortRootPrjByParentid(projectEdits);

        initSheetWidth(sheet, rootProjectEdit);//设置表格宽度

        //一次性取出所有清单、清包、材料减少for循环中多次从数据库取数据
        List<PriceDetailListEntity> pLists = priceDetailListInterface.findAllByRootprojectidAndType(
                rootProjectEdit.getGuid(), "清单");
        List<PriceDetailListEntity> bagClears = priceDetailListInterface.findAllByRootprojectidAndType(
                rootProjectEdit.getGuid(), "清包");
        List<PriceDetailListEntity> materials = priceDetailListInterface.findAllByRootprojectidAndType(
                rootProjectEdit.getGuid(), "材料");

        Map<String, List<PriceDetailListEntity>> pListsMap = new HashMap<>();
        Map<String, List<PriceDetailListEntity>> bagClearsMap = new HashMap<>();
        Map<String, List<PriceDetailListEntity>> materialsMap = new HashMap<>();

        if (pLists != null && pLists.size() > 0) {
            setPrice2Map(pLists, pListsMap, true);
        }
        if (bagClears != null && bagClears.size() > 0) {
            setPrice2Map(bagClears, bagClearsMap, false);
        }
        if (materials != null && materials.size() > 0) {
            setPrice2Map(materials, materialsMap, false);
        }
        int index = 3;// row 行数
        int prjNo = 0;//项目数
        while (sortProjectEdits.size() > 0) {
            ListProjectEdit l = sortProjectEdits.pop();
            prjNo++;
            setPrjRows(sheet, index, prjNo, l);
            index++;
            List<PriceDetailListEntity> pdetails = pListsMap.get(l.getGuid());
            if (pdetails != null) {
                for (PriceDetailListEntity pDetail : pdetails) {
                    List<PriceDetailListEntity> bagClear = bagClearsMap.get(pDetail.getGuid());
                    List<PriceDetailListEntity> material = materialsMap.get(pDetail.getGuid());
                    int bagClearSize = bagClear == null ? 0 : bagClear.size();
                    int materialSize = material == null ? 0 : material.size();
                    int childNums = bagClearSize + materialSize;
                    setRootBagRow(sheet, index, pDetail, childNums,0);
                    index++;

                    if (bagClearSize > 0) {
                        for (PriceDetailListEntity bag : bagClear) {
                            setRootBagRow(sheet, index, bag, 0,1);
                            index++;
                        }
                    }
                    if (materialSize > 0) {
                        for (PriceDetailListEntity mat : material) {
                            setRootBagRow(sheet, index, mat, 0,1);
                            index++;
                        }
                    }
                }
            }
        }
    }

    /**
     * 项目预算书表格(不包含清包、材料)
     *
     * @param sheet           sheet
     * @param rootProjectEdit 根项目
     * @param projectEdits    项目集合
     */
    private void setPrjFirstSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit
            , List<ListProjectEdit> projectEdits) {

        initSheetWidth(sheet, rootProjectEdit);
        //按层级排序
        LinkedList<ListProjectEdit> sortProjectEdits = sortRootPrjByParentid(projectEdits);
        List<PriceDetailListEntity> pLists = priceDetailListInterface.findAllByRootprojectidAndType(
                rootProjectEdit.getGuid(), "清单");

        Map<String, List<PriceDetailListEntity>> pListsMap = new HashMap<>();

        if (pLists != null && pLists.size() > 0) {
            setPrice2Map(pLists, pListsMap, true);
        }

        int index = 3;// row 行数
        int prjNo = 0;//项目数
        while (sortProjectEdits.size() > 0) {
            ListProjectEdit l = sortProjectEdits.pop();
            prjNo++;

            setPrjRows(sheet, index, prjNo, l);

            index++;
            List<PriceDetailListEntity> pdetails = pListsMap.get(l.getGuid());
            if (pdetails != null && pdetails.size() > 0) {
                for (PriceDetailListEntity pDetail : pdetails) {
                    setRootBagRow(sheet, index, pDetail, 0,0);
                    index++;
                }
            }
        }
    }

    //对分部分项工程按层级排序
    private LinkedList<ListProjectEdit> sortRootPrjByParentid(List<ListProjectEdit> projectEdits) {
        //设计链表来储存排序后的工程结构
        LinkedList<ListProjectEdit> prjsAfterSort = new LinkedList<>();
        if (projectEdits == null) return prjsAfterSort;
        int len = projectEdits.size();
        for (int i = 0; i < len; i++) {
            ListProjectEdit temp = projectEdits.get(i);
            if ("0".equals(temp.getParentid())) {
                prjsAfterSort.add(temp);//储存根工程节点
                projectEdits.remove(temp);
                break;
            }
        }
        int loops = 0;
        //迭代器循环插入链表
        while (projectEdits.size() > 0) {
            Iterator<ListProjectEdit> iterator = projectEdits.iterator();
            while (iterator.hasNext()) {
                ListProjectEdit child = iterator.next();
                Iterator<ListProjectEdit> linkedIt = prjsAfterSort.iterator();
                while (linkedIt.hasNext()) {
                    ListProjectEdit parent = linkedIt.next();
                    if (parent.getGuid().equals(child.getParentid())) {
                        int index = prjsAfterSort.indexOf(parent);
                        int size = prjsAfterSort.size();
                        //add定位不能超过链表索引，所以添加末尾用addLast，插入用add
                        if (index == size - 1) {
                            prjsAfterSort.addLast(child);
                        } else {
                            prjsAfterSort.add(index + 1, child);
                        }
                        iterator.remove();
                        break;
                    }
                }
            }
            loops++;   //防止数据库有错误数据引入，功能设计项目层级最多应该就三级，循环两次应该就可以结束
            if (loops > 3) break;
        }
        return prjsAfterSort;
    }

    //将清单、清包、材料由list转化为Map
    private void setPrice2Map(List<PriceDetailListEntity> lists, Map<String, List<PriceDetailListEntity>> target, boolean isRootBab) {
        for (PriceDetailListEntity p : lists) {
            String key = "";
            if (isRootBab) {
                key = p.getProjectid();
            } else {
                key = p.getParentid();
            }
            if (target.containsKey(key)) {
                target.get(key).add(p);
            } else {
                List<PriceDetailListEntity> temp = new ArrayList<>();
                temp.add(p);
                target.put(key, temp);
            }
        }
    }

    //初始化分部分项预算书表头
    private void initSheetWidth(XSSFSheet sheet, ListProjectEdit rootProjectEdit) {
        sheet.setColumnWidth(0, 10 * 256);//序号
        sheet.setColumnWidth(1, 20 * 256);//编号
        sheet.setColumnWidth(2, 10 * 256);//类型
        sheet.setColumnWidth(3, 28 * 256);//名称
        sheet.setColumnWidth(4, 12 * 256);//单位
        sheet.setColumnWidth(5, 15 * 256);//工程量
        sheet.setColumnWidth(6, 15 * 256);//单价
        sheet.setColumnWidth(7, 18 * 256);//合价
        sheet.setColumnWidth(8, 18 * 256);//清包单价
        sheet.setColumnWidth(9, 18 * 256);//材料单价
        sheet.setColumnWidth(10, 18 * 256);//清包材料
        sheet.setColumnWidth(11, 18 * 256);//清包人力
        sheet.setColumnWidth(12, 18 * 256);//清包机械
        sheet.setColumnHidden(13,true);
        sheet.setColumnHidden(14,true);
        setSheetTitle(sheet, "分部分项工程费用表", "分部分项工程名称：" + rootProjectEdit.getProjectname(), 12);
        String[] titles = {"序号", "编号", "类型", "名称", "单位", "工程量", "单价(元)", "合价(元)", "清包单价(元)"
                , "材料单价(元)", "清包材料(元)", "清包人力(元)", "清包机械(元)","GUID","PARENTID"};
        setTableTitle(sheet, 2, titles);
    }

    //项目费用汇总中构建项目信息
    private void setPrjRows(XSSFSheet sheet, int index, int prjNo, ListProjectEdit l) {
        row = sheet.createRow(index);
        row.setHeightInPoints(25);
        setCellValue(0, prjNo, tableCellCenterStyle);
        setCellValue(1, "-", tableCellCenterStyle);
        setCellValue(3, l.getProjectname(), tableCellLeftStyle);
        setCellValue(3, l.getProjectname(), tableCellLeftStyle);
        setCellValueFormula(7,"SUMIF($O:$O,$N"+(index+1)+",$H:$H)",tableCellRightMoneytStyle);
        setCellValue(13, l.getGuid(), tableCellCenterStyle);
        setCellValue(14, l.getParentid(), tableCellCenterStyle);
    }

    //构建项目费用汇总中清单、清包、材料(type=0为清单,1为清包材料)
    private void setRootBagRow(XSSFSheet sheet, int index, PriceDetailListEntity pDetail, int childSize, int type) {
        row = sheet.createRow(index);
        row.setHeightInPoints(25);

        setCellValue(1, pDetail.getFlag(), tableCellCenterStyle);
        setCellValue(2, pDetail.getType(), tableCellCenterStyle);
        setCellValue(3, pDetail.getName(), tableCellLeftStyle);
        setCellValue(4, pDetail.getUnit(), tableCellCenterStyle);
        if (pDetail.getProjectamount() != null) {
            setCellValue(5, pDetail.getProjectamount(), tableCellRightMoneytStyle);
        }
        if (childSize == 0) {
            if (pDetail.getPrice() != null) {
                setCellValue(6, pDetail.getPrice().doubleValue(), tableCellRightMoneytStyle);
            }
            setCellValueFormula(7,"F" + (index + 1) + "*G" + (index + 1),tableCellRightMoneytStyle);
        } else {
            setCellValueFormula(6, "H" + (index + 1) + "/F" + (index + 1), tableCellRightMoneytStyle);
            setCellValueFormula(7, "SUM(H" + (index + 2) + ":H" + (index + 1 + childSize) + ")", tableCellRightMoneytStyle);
        }
        if (childSize != 0 && type == 0){
            setCellValueFormula(8,"SUMIF(C" + (index + 2) + ":C" + (index + 1 + childSize) + ",\"清包\",H"
                    + (index + 2) + ":H" + (index + 1 + childSize) + ")/F" + (index + 1),tableCellRightMoneytStyle);
            setCellValueFormula(9,"SUMIF(C" + (index + 2) + ":C" + (index + 1 + childSize) + ",\"材料\",H"
                    + (index + 2) + ":H" + (index + 1 + childSize) + ")/F" + (index + 1),tableCellRightMoneytStyle);
        }else {
            if (pDetail.getBagclearprice() != null) {
                setCellValue(8, pDetail.getBagclearprice().doubleValue(), tableCellRightMoneytStyle);
            }
            if (pDetail.getMaterialprice() != null) {
                setCellValue(9, pDetail.getMaterialprice().doubleValue(), tableCellRightMoneytStyle);
            }
        }
        if (pDetail.getMaterialcost() != null) {
            setCellValue(10, pDetail.getMaterialcost().doubleValue(), tableCellRightMoneytStyle);
        }
        if (pDetail.getPersoncost() != null) {
            setCellValue(11, pDetail.getPersoncost().doubleValue(), tableCellRightMoneytStyle);
        }
        if (pDetail.getMachinecost() != null) {
            setCellValue(12, pDetail.getMachinecost().doubleValue(), tableCellRightMoneytStyle);
        }
        if (type == 0){
            setCellValue(13,pDetail.getGuid(),tableCellCenterStyle);
            setCellValue(14,pDetail.getProjectid(),tableCellCenterStyle);
        }
    }

    /**
     * 材料汇总表格
     *
     * @param sheet           材料汇总表格
     * @param rootProjectEdit 根项目
     * @param materials       材料
     */
    private void setPrjMaterialSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit
            , List<Map<String, Object>> materials) {
        sheet.setColumnWidth(0, 10 * 256);//序号
        sheet.setColumnWidth(1, 15 * 256);//编号
        sheet.setColumnWidth(2, 30 * 256);//名称
        sheet.setColumnWidth(3, 30 * 256);//规格型号
        sheet.setColumnWidth(4, 12 * 256);//单位
        sheet.setColumnWidth(5, 13 * 256);//数量
        sheet.setColumnWidth(6, 15 * 256);//除税单价
        sheet.setColumnWidth(7, 18 * 256);//除税合价
        sheet.setColumnWidth(8, 13 * 256);//税率
        sheet.setColumnWidth(9, 35 * 256);//备注
        setSheetTitle(sheet, "材料汇总表", "分部分项工程名称：" + rootProjectEdit.getProjectname(), 9);

        String[] titles = {"序号", "编号", "名称", "规格型号", "单位", "数量", "除税单价(元)", "除税合价(元)", "税率", "备注"};
        setTableTitle(sheet, 2, titles);

        int index = 1;
        for (Map<String, Object> materail : materials) {
            int rowIndex = index + 2;
            row = sheet.createRow(rowIndex);
            row.setHeightInPoints(25);
            setCellValue(0, index, tableCellCenterStyle);
            setCellValue(1, isNull(materail.get("flag")), tableCellCenterStyle);
            setCellValue(2, isNull(materail.get("name")), tableCellLeftStyle);
            setCellValue(3, isNull(materail.get("feature")), tableCellLeftStyle);
            setCellValue(4, isNull(materail.get("unit")), tableCellCenterStyle);
            setCellValue(5, isNull(materail.get("projectamount")), tableCellRightStyle);
            if (materail.get("price") != null) {
                setCellValue(6, Double.parseDouble(materail.get("price").toString()), tableCellRightMoneytStyle);
            }
            if (materail.get("total") != null) {
                setCellValue(7, Double.parseDouble(materail.get("total").toString()), tableCellRightMoneytStyle);
            }
            setCellValue(8, isNull(materail.get("taxrate")), tableCellCenterStyle);
            setCellValue(9, isNull(materail.get("remark")), tableCellLeftStyle);

            index++;
        }
    }

    /**
     * 清包汇总表格
     *
     * @param sheet           清包汇总表格
     * @param rootProjectEdit 根项目
     * @param bagClears       清包
     */
    private void setPrjBagClearSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit
            , List<Map<String, Object>> bagClears) {

        sheet.setColumnWidth(0, 10 * 256);//序号
        sheet.setColumnWidth(1, 15 * 256);//编号
        sheet.setColumnWidth(2, 30 * 256);//名称
        sheet.setColumnWidth(3, 12 * 256);//单位
        sheet.setColumnWidth(4, 13 * 256);//数量
        sheet.setColumnWidth(5, 15 * 256);//除税单价
        sheet.setColumnWidth(6, 18 * 256);//除税合价
        sheet.setColumnWidth(7, 35 * 256);//工作内容
        sheet.setColumnWidth(8, 25 * 256);//计量规则
        sheet.setColumnWidth(9, 25 * 256);//数据来源
        setSheetTitle(sheet, "清包汇总表", "分部分项工程名称：" + rootProjectEdit.getProjectname(), 9);

        String[] titles = {"序号", "编号", "名称", "单位", "数量", "除税单价(元)", "除税合价(元)"
                , "包含工作内容", "计量规则", "数据来源"};
        setTableTitle(sheet, 2, titles);

        int index = 1;
        for (Map<String, Object> bagClear : bagClears) {
            int rowIndex = index + 2;
            row = sheet.createRow(rowIndex);
            row.setHeightInPoints(25);
            setCellValue(0, index, tableCellCenterStyle);
            setCellValue(1, isNull(bagClear.get("flag")), tableCellCenterStyle);
            setCellValue(2, isNull(bagClear.get("name")), tableCellLeftStyle);
            setCellValue(3, isNull(bagClear.get("unit")), tableCellCenterStyle);
            setCellValue(4, isNull(bagClear.get("projectamount")), tableCellRightStyle);

            if (bagClear.get("price") != null) {
                setCellValue(5, Double.parseDouble(bagClear.get("price").toString()), tableCellRightMoneytStyle);
            }
            if (bagClear.get("total") != null) {
                setCellValue(6, Double.parseDouble(bagClear.get("total").toString()), tableCellRightMoneytStyle);
            }

            setCellValue(7, isNull(bagClear.get("workcontent")), tableCellLeftStyle);
            setCellValue(8, isNull(bagClear.get("countruler")), tableCellLeftStyle);
            setCellValue(9, isNull(bagClear.get("pricefrom")), tableCellLeftStyle);

            index++;
        }
    }

    /**
     * 项目措施费表格
     *
     * @param sheet           sheet
     * @param rootProjectEdit 根项目
     * @param pmCost          措施费
     * @param pmDetail        措施费明细
     */
    private void setPrjSecondSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit, ProjectMeasureCostEntity pmCost
            , List<ProjectMeasureDetail> pmDetail, int sheetIndex) {

        int size = pmDetail == null ? 0 : pmDetail.size();
        if (size > 0) {
            //排序
            Collections.sort(pmDetail, Comparator.comparingInt(ProjectMeasureDetail::getSortid));
        }

        sheet.setColumnWidth(0, 10 * 256);//序号
        sheet.setColumnWidth(1, 5 * 256);//
        sheet.setColumnWidth(2, 20 * 256);//
        sheet.setColumnWidth(3, 15 * 256);//
        sheet.setColumnWidth(4, 12 * 256);//费率
        sheet.setColumnWidth(5, 15 * 256);//费用
        sheet.setColumnWidth(6, 25 * 256);//备注
        setSheetTitle(sheet, "项目措施费用表", "分部分项工程名称：" + rootProjectEdit.getProjectname(), 6);

        this.region = new CellRangeAddress(2, 2, 0, 6);//场内临时费
        sheet.addMergedRegion(region);
        this.region = new CellRangeAddress(3, 3, 1, 3);//内容
        sheet.addMergedRegion(region);

        this.region = new CellRangeAddress(size + 4, size + 4, 0, 6);//说明
        sheet.addMergedRegion(region);
        this.region = new CellRangeAddress(size + 5, size + 5, 0, 6);//安全文明费
        sheet.addMergedRegion(region);

        this.region = new CellRangeAddress(size + 6, size + 6, 0, 1);//小计
        sheet.addMergedRegion(region);
        this.region = new CellRangeAddress(size + 6, size + 6, 4, 5);//分部分项
        sheet.addMergedRegion(region);
        this.region = new CellRangeAddress(size + 7, size + 7, 0, 1);//费率
        sheet.addMergedRegion(region);
        this.region = new CellRangeAddress(size + 7, size + 7, 4, 5);//场内临时费
        sheet.addMergedRegion(region);

        this.region = new CellRangeAddress(size + 8, size + 8, 0, 6);//说明
        sheet.addMergedRegion(region);
        this.region = new CellRangeAddress(size + 9, size + 9, 0, 1);//合计
        sheet.addMergedRegion(region);
        this.region = new CellRangeAddress(size + 9, size + 9, 3, 6);
        sheet.addMergedRegion(region);

        row = sheet.createRow(2);
        row.setHeightInPoints(28);
        setCellValue(0, "场内临时费", title2Style);

        String[] titles1 = {"序号", "内容", "", "", "费率(%)", "费用(元)", "备注"};
        setTableTitle(sheet, 3, titles1);

        row = sheet.createRow(size + 4);
        row.setHeightInPoints(16);
        setCellValue(0, "说明：费率为该项费用与分部分项费用的比例");
        row = sheet.createRow(size + 5);
        row.setHeightInPoints(28);
        setCellValue(0, "安全文明施工费", title2Style);
        row = sheet.createRow(size + 8);
        row.setHeightInPoints(16);
        setCellValue(0, "安全文明施工费计算公式：小计 = ( 分部分项费用 + 场内临时费 ) × 施工费率 %");

        int index = 0;
        if (size > 0) {
            for (ProjectMeasureDetail p : pmDetail) {
                index++;
                this.region = new CellRangeAddress(index + 3, index + 3, 1, 3);
                sheet.addMergedRegion(region);
                row = sheet.createRow(index + 3);
                row.setHeightInPoints(25);
                setCellValue(0, index, tableCellCenterStyle);
                setCellValue(1, p.getName(), tableCellLeftStyle);
                setCellValue(4, isNull(p.getRate()), tableCellCenterStyle);
                if (p.getCost() != null) {
                    setCellValue(5, p.getCost().doubleValue(), tableCellRightMoneytStyle);
                } else {
                    setCellValue(5, tableCellRightMoneytStyle);
                }
                setCellValue(6, p.getRemark(), tableCellLeftStyle);
            }
        }
        row = sheet.createRow(size + 6);
        row.setHeightInPoints(25);
        setCellValue(0, "小计", tableCellRightStyle);
        setCellValueFormula(2, "(E" + (size + 7) + "+E" + (size + 8) + ")*C" + (size + 8) + "/100", tableCellRightMoneytStyle);
        setCellValue(3, "分部分项费(元)", tableCellRightStyle);
        setCellValueFormula(4, "'" + (sheetIndex + 1) + ".1.项目费用汇总'!H4", tableCellRightMoneytStyle);

        row = sheet.createRow(size + 7);
        row.setHeightInPoints(25);
        setCellValue(0, "施工费率(%)", tableCellRightStyle);
        if (pmCost != null && pmCost.getCostpercentage() != null) {
            setCellValue(2, pmCost.getCostpercentage(), tableCellRightMoneytStyle);
        } else {
            setCellValue(2, tableCellRightMoneytStyle);
        }
        setCellValue(3, "场内临时费(元)", tableCellRightStyle);
        setCellValueFormula(4, "SUM(F5:F" + (size + 4) + ")", tableCellRightMoneytStyle);

        row = sheet.createRow(size + 9);
        row.setHeightInPoints(25);
        setCellValue(0, "合计(元)", tableCellRightMoneyColortStyle);
        setCellValueFormula(2, "C" + (size + 7) + "+E" + (size + 8), tableCellRightMoneyColortStyle);
        setCellValue(3, tableCellRightMoneyColortStyle);
    }

    /**
     * 自营成本表格
     *
     * @param sheet           sheet
     * @param rootProjectEdit 根项目
     * @param selfMain          自营成本主表
     */
    private void setPrjThirdSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit, ProjectSelfMainEntity selfMain) {

        sheet.setColumnWidth(0, 10 * 256);//序号
        sheet.setColumnWidth(1, 28 * 256);//预算内容
        sheet.setColumnWidth(2, 10 * 256);//单位
        sheet.setColumnWidth(3, 12 * 256);//数量
        sheet.setColumnWidth(4, 13 * 256);//单位费用
        sheet.setColumnWidth(5, 15 * 256);//总费用
        sheet.setColumnWidth(6, 13 * 256);//工资
        sheet.setColumnWidth(7, 13 * 256);//考核
        sheet.setColumnWidth(8, 15 * 256);//工资小计
        sheet.setColumnWidth(9, 15 * 256);//生产成本
        sheet.setColumnWidth(10, 40 * 256);//备注
        setSheetTitle(sheet, "自营成本费用表", "分部分项工程名称：" + rootProjectEdit.getProjectname(), 10);

        for (int i = 0; i <= 10; i++) {
            if (i == 6) {
                region = new CellRangeAddress(2, 2, 6, 8);
                sheet.addMergedRegion(region);
            } else if (i != 7 && i != 8) {
                region = new CellRangeAddress(2, 3, i, i);
                sheet.addMergedRegion(region);
            }
        }

        String[] titles1 = {"序号", "预算内容", "单位", "数量", "单位费用(元)", "总费用(元)", "工资成本(元)", "", ""
                , "生产成本(元)", "备注"};
        setTableTitle(sheet, 2, titles1);
        String[] titles2 = {"", "", "", "", "", "", "工资", "考核", "工资小计", "", ""};
        setTableTitle(sheet, 3, titles2);

        List<ProjectSelfCostEntity> psCost = new ArrayList<>();
        List<ProjectSelfOtherEntity> psOther = new ArrayList<>();
        if (selfMain != null){
            psCost = projectSelfCostInterface.findAllByFkguid(selfMain.getGuid());
            psOther = projectSelfOtherInterface.findAllByFkguid(selfMain.getGuid());
            psOther = psOther == null ? new ArrayList<>() : psOther;
        }

        int rowIndex = 4;
        if (psCost != null && psCost.size() > 0) {
            Collections.sort(psCost, Comparator.comparingInt(ProjectSelfCostEntity::getRowid));//排序
            for (ProjectSelfCostEntity ps : psCost) {
                row = sheet.createRow(rowIndex);
                row.setHeightInPoints(25);

                setCellValue(0, ps.getIndexno(), tableCellLeftStyle);
                setCellValue(1, ps.getTitle(), tableCellLeftStyle);
                setCellValue(2, ps.getUnit(), tableCellCenterStyle);
                if (ps.getSelfnum() != null) {
                    setCellValue(3, ps.getSelfnum(), tableCellRightStyle);
                } else {
                    setCellValue(3, tableCellRightStyle);
                }
                if (ps.getSelfcost() != null) {
                    setCellValue(4, ps.getSelfcost().doubleValue(), tableCellRightMoneytStyle);
                } else {
                    setCellValue(4, tableCellRightMoneytStyle);
                }
                //总费用(元)
                switch (rowIndex) {
                    case 4:
                        setCellValueFormula(5, "F6+F11+F15+F22", tableCellRightMoneytStyle);
                        setCellValueFormula(6, "G6+G11+G15+G22", tableCellRightMoneytStyle);
                        setCellValueFormula(7, "H6+H11+H15+H22", tableCellRightMoneytStyle);
                        setCellValueFormula(8, "I6+I11+I15+I22", tableCellRightMoneytStyle);
                        setCellValueFormula(9, "J6+J11+J15+J22", tableCellRightMoneytStyle);
                        break;
                    case 5:
                        setCellValueFormula(5, "SUM(F7:F10)", tableCellRightMoneytStyle);
                        setCellValueFormula(6, "SUM(G7:G10)", tableCellRightMoneytStyle);
                        setCellValueFormula(7, "SUM(H7:H10)", tableCellRightMoneytStyle);
                        setCellValueFormula(8, "SUM(I7:I10)", tableCellRightMoneytStyle);
                        setCellValueFormula(9, "SUM(J7:J10)", tableCellRightMoneytStyle);
                        break;
                    case 10:
                        setCellValueFormula(5, "SUM(F12:F14)", tableCellRightMoneytStyle);
                        setCellValueFormula(9, "SUM(J12:J14)", tableCellRightMoneytStyle);
                        break;
                    case 14:
                        setCellValueFormula(5, "SUM(F16:F21)", tableCellRightMoneytStyle);
                        setCellValueFormula(9, "SUM(J16:J21)", tableCellRightMoneytStyle);
                        break;
                    case 21:
                        setCellValueFormula(5, "SUM(F23:F24)", tableCellRightMoneytStyle);
                        setCellValueFormula(6, "SUM(G23:G24)", tableCellRightMoneytStyle);
                        setCellValueFormula(7, "SUM(H23:H24)", tableCellRightMoneytStyle);
                        setCellValueFormula(8, "SUM(I23:I24)", tableCellRightMoneytStyle);
                        setCellValueFormula(9, "SUM(J23:J24)", tableCellRightMoneytStyle);
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 11:
                    case 12:
                    case 13:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 22:
                        setCellValueFormula(5, "D" + (rowIndex + 1) + "*E" + (rowIndex + 1), tableCellRightMoneytStyle);
                        break;
                    case 9:
                    case 23:
                        setCellValueFormula(5, "E" + (rowIndex + 1), tableCellRightMoneytStyle);
                        break;
                }
                //工资
                switch (rowIndex) {
                    case 6:
                        setCellValueFormula(6, "F7", tableCellRightMoneytStyle);
                        break;
                    case 22:
                        setCellValueFormula(6, "F23*0.5", tableCellRightMoneytStyle);
                        break;
                    case 9:
                    case 23:
                        if (ps.getSelfsalary() != null) {
                            setCellValue(6, ps.getSelfsalary().doubleValue(), tableCellRightMoneytStyle);
                        } else {
                            setCellValue(6, tableCellRightMoneytStyle);
                        }
                        break;
                }
                //考核
                switch (rowIndex) {
                    case 9:
                    case 23:
                        setCellValueFormula(7, "F" + (rowIndex + 1), tableCellRightMoneytStyle);
                        break;
                    case 6:
                    case 22:
                        if (ps.getSelfbonus() != null) {
                            setCellValue(7, ps.getSelfbonus().doubleValue(), tableCellRightMoneytStyle);
                        } else {
                            setCellValue(7, tableCellRightMoneytStyle);
                        }
                        break;
                }
                //工资小计
                switch (rowIndex) {
                    case 6:
                    case 9:
                    case 22:
                    case 23:
                        setCellValueFormula(8, "G" + (rowIndex + 1) + "+H" + (rowIndex + 1), tableCellRightMoneytStyle);
                        break;
                }
                //生产成本
                switch (rowIndex) {
                    case 7:
                    case 8:
                    case 11:
                    case 12:
                    case 13:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                        setCellValueFormula(9, "F" + (rowIndex + 1), tableCellRightMoneytStyle);
                        break;
                    case 22:
                        setCellValueFormula(9, "0.5*F" + (rowIndex + 1), tableCellRightMoneytStyle);
                        break;
                }
                setCellValue(10, ps.getRemark(), tableCellLeftStyle);

                rowIndex++;
            }
        }

        this.psOhterSize = psOther.size();

        for (int i = 0;i <= this.psOhterSize; i++){
            this.region = new CellRangeAddress(rowIndex + i + 1,rowIndex + i + 1, 6 , 9);
            sheet.addMergedRegion(this.region);
        }
        String[] titles3 = {"序号", "预算内容", "单位", "数量", "单位费用(元)", "总费用(元)", "备注"};
        setTableTitle(sheet, rowIndex + 1, titles3);

//        String sumFormula = "";
        if (this.psOhterSize > 0){
            Collections.sort(psOther,Comparator.comparingInt(ProjectSelfOtherEntity::getRowid));
            for (ProjectSelfOtherEntity other : psOther){
                row = sheet.createRow(rowIndex + 2);
                row.setHeightInPoints(25);
                setCellValue(0,other.getRowid(),tableCellCenterStyle);
                setCellValue(1,other.getTitle(),tableCellLeftStyle);
                setCellValue(2,other.getUnit(),tableCellCenterStyle);
                setCellValue(3,other.getSelfnum(),tableCellRightStyle);
                setCellValue(4,other.getSelfcost(),tableCellRightMoneytStyle);
                setCellValueFormula(5,"D"+(rowIndex + 3)+"*E"+(rowIndex + 3),tableCellRightMoneytStyle);
                setCellValue(6,other.getRemark(),tableCellLeftStyle);

//                sumFormula += "+F" + (rowIndex + 3);
                rowIndex++;
            }
        }

        row = sheet.createRow(rowIndex +3);
        row.setHeightInPoints(25);
        setCellValue(0,"合计", tableCellRightMoneyColortStyle);
//        setCellValueFormula(1,"F5"+ sumFormula,tableCellRightMoneyColortStyle);
        setCellValueFormula(1,"F5+SUM(F27:F"+(26+this.psOhterSize) +")",tableCellRightMoneyColortStyle);
        this.region = new CellRangeAddress(rowIndex + 3, rowIndex + 3, 2,9);
        sheet.addMergedRegion(this.region);
        setCellValue(2,tableCellRightMoneyColortStyle);
    }

    /**
     * 税金
     *
     * @param sheet           sheet
     * @param rootProjectEdit 根项目
     * @param ptCost          税金
     */
    private void setPrjFourthSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit
            , ProjectTaxCostEntity ptCost, int sheedIndex, int pmDetailSize) {
        sheet.setColumnWidth(0, 20 * 256);//1
        sheet.setColumnWidth(1, 15 * 256);//2
        sheet.setColumnWidth(2, 20 * 256);//3
        sheet.setColumnWidth(3, 15 * 256);//4
        setSheetTitle(sheet, "税金费用表", "分部分项工程名称：" + rootProjectEdit.getProjectname(), 3);

        row = sheet.createRow(2);
        row.setHeightInPoints(25);
        setCellValue(0, "分部分项费(元)", tableCellRightStyle);
        setCellValueFormula(1, "'" + (sheedIndex + 1) + ".1.项目费用汇总'!H4", tableCellRightMoneytStyle);

        setCellValue(2, "项目措施费(元)", tableCellRightStyle);
        setCellValueFormula(3, "'" + (sheedIndex + 1) + "." + (2 + isAllExport) + ".项目措施费'!C" + (pmDetailSize + 10), tableCellRightMoneytStyle);

        row = sheet.createRow(3);
        row.setHeightInPoints(25);
        setCellValue(0, "自营成本(元)", tableCellRightStyle);
        setCellValueFormula(1, "'" + (sheedIndex + 1) + "." + (3 + isAllExport) + ".自营成本'!B"+(28 + this.psOhterSize), tableCellRightMoneytStyle);

        setCellValue(2, "税率(%)", tableCellRightStyle);
        if (ptCost != null && ptCost.getTaxrate() != null) {
            setCellValue(3, ptCost.getTaxrate(), tableCellRightStyle);
        } else {
            setCellValue(3, tableCellRightStyle);
        }

        row = sheet.createRow(4);
        row.setHeightInPoints(25);
        setCellValue(0, "抵扣(元)", tableCellRightStyle);
        if (ptCost != null && ptCost.getDeductioncost() != null) {
            setCellValue(1, ptCost.getDeductioncost().doubleValue(), tableCellRightMoneytStyle);
        } else {
            setCellValue(1, tableCellRightMoneytStyle);
        }

        row = sheet.createRow(5);
        row.setHeightInPoints(25);
        setCellValue(0, "合计(元)", tableCellRightMoneyColortStyle);
        setCellValueFormula(1, "(B3+D3+B4)*D4/100-B5", tableCellRightMoneyColortStyle);
        setCellValue(2, tableCellRightMoneyColortStyle);
        setCellValue(3, tableCellRightMoneyColortStyle);

        region = new CellRangeAddress(6, 6, 0, 3);
        sheet.addMergedRegion(region);
        row = sheet.createRow(6);
        row.setHeightInPoints(16);
        setCellValue(0, "税金计算公式：( 分部分项费用 + 场内临时费 + 自营成本 ) × 税率 % - 抵扣", tableCellLeftStyle);
    }

    /**
     * 费用汇总表格
     *
     * @param sheet           sheet
     * @param rootProjectEdit 根项目
     */
    private void setPrjFifthSheet(XSSFSheet sheet, ListProjectEdit rootProjectEdit
            , int sheetIndex, int pmDetailSize) {
        sheet.setColumnWidth(0, 10 * 256);//1
        sheet.setColumnWidth(1, 15 * 256);//编号
        sheet.setColumnWidth(2, 35 * 256);//费用名称
        sheet.setColumnWidth(3, 15 * 256);//金额
        setSheetTitle(sheet, "分部分项费用汇总表", "分部分项工程名称：" + rootProjectEdit.getProjectname(), 3);
        String[] titles = {" ", "编号", "费用名称", "金额(元)"};
        setTableTitle(sheet, 2, titles);

        setPrjFifthSheetRows(sheet, 3, "一", "分部分项费用", "'" + (sheetIndex + 1) + ".1.项目费用汇总'!H4");
        int index = sheetIndex + 1;
        setPrjFifthSheetRows(sheet, 4, "二", "项目措施费"
                , "'" + index + "." + (2 + isAllExport) + ".项目措施费'!C" + (pmDetailSize + 10));
        setPrjFifthSheetRows(sheet, 5, " ", "场内临时费"
                , "'" + index + "." + (2 + isAllExport) + ".项目措施费'!E" + (pmDetailSize + 8));
        setPrjFifthSheetRows(sheet, 6, " ", "安全文明施工费"
                , "'" + index + "." + (2 + isAllExport) + ".项目措施费'!C" + (pmDetailSize + 7));
        setPrjFifthSheetRows(sheet, 7, "三", "自营成本"
                , "'" + index + "." + (3 + isAllExport) + ".自营成本'!B"+(28 + this.psOhterSize));
        setPrjFifthSheetRows(sheet, 8, "四", "税金"
                , "'" + index + "." + (4 + isAllExport) + ".税金'!B6");

        XSSFCellStyle style = this.EXCEL.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new Color(253, 245, 230), new DefaultIndexedColorMap()));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        row = sheet.createRow(9);
        row.setHeightInPoints(25);

        setCellValue(0, 7, style);
        setCellValue(1, "五", style);
        setCellValue(2, "合计", tableCellRightMoneyColortStyle);

        setCellValueFormula(3, "sum(D4:D9)", tableCellRightMoneyColortStyle);
    }

    /**
     * 费用汇总构建行
     *
     * @param sheet    sheet
     * @param rowIndex 行
     * @param idIndex  行
     * @param costName 名称
     * @param fomula   公式
     */
    private void setPrjFifthSheetRows(XSSFSheet sheet, int rowIndex, String idIndex, String costName, String fomula) {
        row = sheet.createRow(rowIndex);
        row.setHeightInPoints(25);
        setCellValue(0, rowIndex - 2, tableCellCenterStyle);
        setCellValue(1, idIndex, tableCellCenterStyle);

        cell = row.createCell(2);
        cell.setCellValue(costName);
        if (rowIndex == 5 || rowIndex == 6) {
            cell.setCellStyle(tableCellCenterStyle);
        } else {
            cell.setCellStyle(tableCellLeftStyle);
        }
        setCellValueFormula(3, fomula, tableCellRightMoneytStyle);
    }

    /**
     * 设置Sheet表头
     *
     * @param sheet    sheet
     * @param title    一级表头
     * @param prjTtile 项目表头
     */
    private void setSheetTitle(XSSFSheet sheet, String title, String prjTtile, int len) {
        region = new CellRangeAddress(0, 0, 0, len);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1, 1, 0, len);
        sheet.addMergedRegion(region);
        row = sheet.createRow(0);
        row.setHeightInPoints(50);

        setCellValue(0, title, titleStyle);
        row = sheet.createRow(1);
        row.setHeightInPoints(30);
        setCellValue(0, prjTtile, title2Style);
    }

    /**
     * 设置表格表头
     *
     * @param sheet    sheet
     * @param rowIndex 表头第几行
     * @param strs     表头内容
     */
    private void setTableTitle(XSSFSheet sheet, int rowIndex, String[] strs) {
        row = sheet.createRow(rowIndex);
        row.setHeightInPoints(25);
        int index = 0;
        for (String str : strs) {
            setCellValue(index, str, tableTitleStyle);
            index++;
        }
    }

    /**
     * 初始化单元格样式
     *
     * @param align          水平位置
     * @param Valign         垂直位置
     * @param fontsize       字体大小
     * @param bold           加粗
     * @param money          是否金额
     * @param border         边框
     * @param backgroudcolor 单元格背景色
     * @return
     */
    private XSSFCellStyle setCellStyle(HorizontalAlignment align, VerticalAlignment Valign
            , int fontsize, boolean bold, boolean money, boolean border, int backgroudcolor) {
        XSSFCellStyle style = this.EXCEL.createCellStyle();
        style.setAlignment(align);//水平位置
        style.setVerticalAlignment(Valign); //垂直位置
        font = this.EXCEL.createFont();
        font.setFontHeightInPoints((short) fontsize);
        font.setBold(bold);
        if (money) {
            style.setDataFormat(BuiltinFormats.getBuiltinFormat("#,##0.00"));
        }
        style.setFont(font);
        if (border) {
/*            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            XSSFColor color = new XSSFColor(new Color(100, 100, 100), new DefaultIndexedColorMap());
            style.setBottomBorderColor(color);
            style.setTopBorderColor(color);
            style.setRightBorderColor(color);
            style.setLeftBorderColor(color);*/
        }
        if (backgroudcolor == 1) {
            //表头背景
            style.setFillForegroundColor(new XSSFColor(new Color(230, 230, 230), new DefaultIndexedColorMap()));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        } else if (backgroudcolor == 2) {
            //合价背景
            style.setFillForegroundColor(new XSSFColor(new Color(253, 245, 230), new DefaultIndexedColorMap()));
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        return style;
    }

    //以下setCellValue方法用于生成单元格
    private void setCellValue(int colIndex, String value, CellStyle style) {
        cell = row.createCell(colIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void setCellValue(int colIndex, String value) {
        cell = row.createCell(colIndex);
        cell.setCellValue(value);
    }

    private void setCellValue(int colIndex, Double value, CellStyle style) {
        cell = row.createCell(colIndex);
        if (value != null) {
            cell.setCellValue(value);
        }
        cell.setCellStyle(style);
    }

    private void setCellValue(int colIndex, Float value, CellStyle style) {
        if (value != null) {
            Double temp = new Double(value);
            setCellValue(colIndex, temp, style);
        }
    }

    private void setCellValue(int colIndex, BigDecimal value, CellStyle style) {
        if (value != null) {
            setCellValue(colIndex, value.doubleValue(), style);
        }
    }

    private void setCellValue(int colIndex, Integer value, CellStyle style) {
        cell = row.createCell(colIndex);
        if (value != null) {
            cell.setCellValue(value);
        }
        cell.setCellStyle(style);
    }

    private void setCellValue(int colIndex, CellStyle style) {
        cell = row.createCell(colIndex);
        cell.setCellStyle(style);
    }

    //带公式的单元格赋值
    private void setCellValueFormula(int colIndex, String value, CellStyle style) {
        cell = row.createCell(colIndex);
        cell.setCellStyle(style);
        if (!"".equals(value)) {
            cell.setCellFormula(value);
        }
    }

    private String isNull(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
