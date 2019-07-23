package com.bccm.projectservices.entity.VO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * 增加List<String> guids属性，用于接收材料汇总，价格汇总信息
 */
public class PriceDetailListEntityVo {
    private String guid;
    private String buildingprojectid;
    private String content;
    private String flag;    //编号
    private String name;
    private String parentid;    //父节点
    private BigDecimal price;   //单价
    private Double projectamount;   //工程量
    private String projectid;       //父工程项目id
    private String remark;
    private BigDecimal total;   //合价
    private String type;        //类型（清单、清包、材料）
    private String unit;        //单位
    private Timestamp updatedt;
    private String pricefrom;       //数据来源
    private String feature;         //规格型号
    private String countruler;      //计量规则
    private String workcontent;     //包含工作内容
    private String taxrate; //税率
    private List<String> guids;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getBuildingprojectid() {
        return buildingprojectid;
    }

    public void setBuildingprojectid(String buildingprojectid) {
        this.buildingprojectid = buildingprojectid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getProjectamount() {
        return projectamount;
    }

    public void setProjectamount(Double projectamount) {
        this.projectamount = projectamount;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Timestamp getUpdatedt() {
        return updatedt;
    }

    public void setUpdatedt(Timestamp updatedt) {
        this.updatedt = updatedt;
    }

    public String getPricefrom() {
        return pricefrom;
    }

    public void setPricefrom(String pricefrom) {
        this.pricefrom = pricefrom;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getCountruler() {
        return countruler;
    }

    public void setCountruler(String countruler) {
        this.countruler = countruler;
    }

    public String getWorkcontent() {
        return workcontent;
    }

    public void setWorkcontent(String workcontent) {
        this.workcontent = workcontent;
    }

    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    public List<String> getGuids() {
        return guids;
    }

    public void setGuids(List<String> guids) {
        this.guids = guids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDetailListEntityVo that = (PriceDetailListEntityVo) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(buildingprojectid, that.buildingprojectid) &&
                Objects.equals(content, that.content) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(price, that.price) &&
                Objects.equals(projectamount, that.projectamount) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(total, that.total) &&
                Objects.equals(type, that.type) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(updatedt, that.updatedt) &&
                Objects.equals(pricefrom, that.pricefrom) &&
                Objects.equals(feature, that.feature) &&
                Objects.equals(countruler, that.countruler) &&
                Objects.equals(workcontent, that.workcontent) &&
                Objects.equals(taxrate, that.taxrate) &&
                Objects.equals(guids, that.guids);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, buildingprojectid, content, flag, name, parentid, price, projectamount, projectid, remark, total, type, unit, updatedt, pricefrom, feature, countruler, workcontent, taxrate, guids);
    }

    @Override
    public String toString() {
        return "["+guid+","+name+","+guids+"]";
    }
}
