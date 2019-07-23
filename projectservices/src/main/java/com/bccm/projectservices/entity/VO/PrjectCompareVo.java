package com.bccm.projectservices.entity.VO;

import java.math.BigDecimal;
import java.util.Objects;
/**
 * 用于对比模块。
 * 将工程项目信息和价格信息统一成PrjectCompareVo对象传送到前台
 */
public class PrjectCompareVo {
    private String guid;
    private String parentid;
    private String buildingprojectid;
    private String flag;    //编号
    private String type;    //类型：项目，清单
    private String projectid;
    private String projectname;     //自己的名字
    private String unit;
    private Double projectamount;
    private BigDecimal price;
    private BigDecimal total;   //项目中的amount，price中的total
    private Double projectcompareCount;
    private BigDecimal projectcomparePrice;
    private BigDecimal projectcomparePriceTotle;
    private Double degreeChangeCount;
    private Double degreeChangePrice;
    private Double degreeChangePriceTotle;
    private Double degreeChangeCountRate;
    private Double degreeChangePriceRate;
    private Double degreeChangePriceTotleRate;
    private String prjDegree;   //项目层级

    public PrjectCompareVo() {
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getBuildingprojectid() {
        return buildingprojectid;
    }

    public void setBuildingprojectid(String buildingprojectid) {
        this.buildingprojectid = buildingprojectid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getProjectamount() {
        return projectamount;
    }

    public void setProjectamount(Double projectamount) {
        this.projectamount = projectamount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Double getProjectcompareCount() {
        return projectcompareCount;
    }

    public void setProjectcompareCount(Double projectcompareCount) {
        this.projectcompareCount = projectcompareCount;
    }

    public BigDecimal getProjectcomparePrice() {
        return projectcomparePrice;
    }

    public void setProjectcomparePrice(BigDecimal projectcomparePrice) {
        this.projectcomparePrice = projectcomparePrice;
    }

    public BigDecimal getProjectcomparePriceTotle() {
        return projectcomparePriceTotle;
    }

    public void setProjectcomparePriceTotle(BigDecimal projectcomparePriceTotle) {
        this.projectcomparePriceTotle = projectcomparePriceTotle;
    }

    public Double getDegreeChangeCount() {
        return degreeChangeCount;
    }

    public void setDegreeChangeCount(Double degreeChangeCount) {
        this.degreeChangeCount = degreeChangeCount;
    }

    public Double getDegreeChangePrice() {
        return degreeChangePrice;
    }

    public void setDegreeChangePrice(Double degreeChangePrice) {
        this.degreeChangePrice = degreeChangePrice;
    }

    public Double getDegreeChangePriceTotle() {
        return degreeChangePriceTotle;
    }

    public void setDegreeChangePriceTotle(Double degreeChangePriceTotle) {
        this.degreeChangePriceTotle = degreeChangePriceTotle;
    }

    public String getPrjDegree() {
        return prjDegree;
    }

    public void setPrjDegree(String prjDegree) {
        this.prjDegree = prjDegree;
    }

    public Double getDegreeChangeCountRate() {
        return degreeChangeCountRate;
    }

    public void setDegreeChangeCountRate(Double degreeChangeCountRate) {
        this.degreeChangeCountRate = degreeChangeCountRate;
    }

    public Double getDegreeChangePriceRate() {
        return degreeChangePriceRate;
    }

    public void setDegreeChangePriceRate(Double degreeChangePriceRate) {
        this.degreeChangePriceRate = degreeChangePriceRate;
    }

    public Double getDegreeChangePriceTotleRate() {
        return degreeChangePriceTotleRate;
    }

    public void setDegreeChangePriceTotleRate(Double degreeChangePriceTotleRate) {
        this.degreeChangePriceTotleRate = degreeChangePriceTotleRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrjectCompareVo that = (PrjectCompareVo) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(buildingprojectid, that.buildingprojectid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(type, that.type) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(projectname, that.projectname) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(projectamount, that.projectamount) &&
                Objects.equals(price, that.price) &&
                Objects.equals(total, that.total) &&
                Objects.equals(projectcompareCount, that.projectcompareCount) &&
                Objects.equals(projectcomparePrice, that.projectcomparePrice) &&
                Objects.equals(projectcomparePriceTotle, that.projectcomparePriceTotle) &&
                Objects.equals(degreeChangeCount, that.degreeChangeCount) &&
                Objects.equals(degreeChangePrice, that.degreeChangePrice) &&
                Objects.equals(degreeChangePriceTotle, that.degreeChangePriceTotle) &&
                Objects.equals(degreeChangeCountRate, that.degreeChangeCountRate) &&
                Objects.equals(degreeChangePriceRate, that.degreeChangePriceRate) &&
                Objects.equals(degreeChangePriceTotleRate, that.degreeChangePriceTotleRate) &&
                Objects.equals(prjDegree, that.prjDegree);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, parentid, buildingprojectid, flag, type, projectid, projectname
                , unit, projectamount, price, total, projectcompareCount, projectcomparePrice
                , projectcomparePriceTotle, degreeChangeCount, degreeChangePrice, degreeChangePriceTotle
                , degreeChangeCountRate, degreeChangePriceRate, degreeChangePriceTotleRate, prjDegree);
    }

    @Override
    public String toString() {
        return "["+guid+","+projectname+","+total+"]\n";
    }
}
