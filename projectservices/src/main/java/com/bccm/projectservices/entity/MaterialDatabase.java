package com.bccm.projectservices.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name= "material_database", schema = "bccm")
public class MaterialDatabase {

    private Integer sysid;
    private String guid;
    private String feature;
    private String flag;
    private LocalDateTime inputdate;
    private String inputerdeptid;
    private String inputerdeptname;
    private String inputerdeptpathid;
    private String inputerfullname;
    private String inputername;
    private Integer isdelete;
    private String name;
    private String parentid;
    private BigDecimal price;
    private String pricefrom;
    private Double projectamount;
    private String remark;
    private String state;
    private String taxrate;
    private String unit;
    private String mlocation;//产地
    private BigDecimal pretaxprice;//不含税出厂价
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp mdate;//时间
    private String plocation;//项目所在地
    private String msupplier;
    private String msuppliertel;//联系方式
    private String mcomponent;//配件信息
    private String type;    //类型

    @Basic
    @Column(name = "sysid", nullable = false)
    public int getSysid() {
        return sysid;
    }

    public void setSysid(int sysid) {
        this.sysid = sysid;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "guid", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "feature", nullable = true, length = 255)
    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Basic
    @Column(name = "flag", nullable = true, length = 255)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "inputdate", nullable = true)
    public LocalDateTime getInputdate() {
        return inputdate;
    }

    public void setInputdate(LocalDateTime inputdate) {
        this.inputdate = inputdate;
    }

    @Basic
    @Column(name = "inputerdeptid", nullable = true, length = 45)
    public String getInputerdeptid() {
        return inputerdeptid;
    }

    public void setInputerdeptid(String inputerdeptid) {
        this.inputerdeptid = inputerdeptid;
    }

    @Basic
    @Column(name = "inputerdeptname", nullable = true, length = 255)
    public String getInputerdeptname() {
        return inputerdeptname;
    }

    public void setInputerdeptname(String inputerdeptname) {
        this.inputerdeptname = inputerdeptname;
    }

    @Basic
    @Column(name = "inputerdeptpathid", nullable = true, length = 45)
    public String getInputerdeptpathid() {
        return inputerdeptpathid;
    }

    public void setInputerdeptpathid(String inputerdeptpathid) {
        this.inputerdeptpathid = inputerdeptpathid;
    }

    @Basic
    @Column(name = "inputerfullname", nullable = true, length = 45)
    public String getInputerfullname() {
        return inputerfullname;
    }

    public void setInputerfullname(String inputerfullname) {
        this.inputerfullname = inputerfullname;
    }

    @Basic
    @Column(name = "inputername", nullable = true, length = 45)
    public String getInputername() {
        return inputername;
    }

    public void setInputername(String inputername) {
        this.inputername = inputername;
    }

    @Basic
    @Column(name = "isdelete", nullable = true)
    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parentid", nullable = true, length = 45)
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "pricefrom", nullable = true, length = 255)
    public String getPricefrom() {
        return pricefrom;
    }

    public void setPricefrom(String pricefrom) {
        this.pricefrom = pricefrom;
    }

    @Basic
    @Column(name = "projectamount", nullable = true, precision = 0)
    public Double getProjectamount() {
        return projectamount;
    }

    public void setProjectamount(Double projectamount) {
        this.projectamount = projectamount;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "taxrate", nullable = true, length = 45)
    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 45)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "mlocation", nullable = true, length = 255)
    public String getMlocation() {
        return mlocation;
    }

    public void setMlocation(String mlocation) {
        this.mlocation = mlocation;
    }

    @Basic
    @Column(name = "pretaxprice", nullable = true, precision = 0)
    public BigDecimal getPretaxprice() {
        return pretaxprice;
    }

    public void setPretaxprice(BigDecimal pretaxprice) {
        this.pretaxprice = pretaxprice;
    }

    @Basic
    @Column(name = "mdate", nullable = true)
    public Timestamp getMdate() {
        return mdate;
    }

    public void setMdate(Timestamp mdate) {
        this.mdate = mdate;
    }

    @Basic
    @Column(name = "plocation", nullable = true, length = 255)
    public String getPlocation() {
        return plocation;
    }

    public void setPlocation(String plocation) {
        this.plocation = plocation;
    }

    @Basic
    @Column(name = "msupplier", nullable = true, length = 255)
    public String getMsupplier() {
        return msupplier;
    }

    public void setMsupplier(String msupplier) {
        this.msupplier = msupplier;
    }

    @Basic
    @Column(name = "msuppliertel", nullable = true, length = 45)
    public String getMsuppliertel() {
        return msuppliertel;
    }

    public void setMsuppliertel(String msuppliertel) {
        this.msuppliertel = msuppliertel;
    }

    @Basic
    @Column(name = "mcomponent", nullable = true, length = 500)
    public String getMcomponent() {
        return mcomponent;
    }

    public void setMcomponent(String mcomponent) {
        this.mcomponent = mcomponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialDatabase that = (MaterialDatabase) o;
        return sysid == that.sysid &&
                Objects.equals(guid, that.guid) &&
                Objects.equals(feature, that.feature) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(inputerdeptid, that.inputerdeptid) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(inputerdeptpathid, that.inputerdeptpathid) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(isdelete, that.isdelete) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(price, that.price) &&
                Objects.equals(pricefrom, that.pricefrom) &&
                Objects.equals(projectamount, that.projectamount) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(state, that.state) &&
                Objects.equals(taxrate, that.taxrate) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(mlocation, that.mlocation) &&
                Objects.equals(pretaxprice, that.pretaxprice) &&
                Objects.equals(mdate, that.mdate) &&
                Objects.equals(plocation, that.plocation) &&
                Objects.equals(msupplier, that.msupplier) &&
                Objects.equals(msuppliertel, that.msuppliertel) &&
                Objects.equals(mcomponent, that.mcomponent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sysid, guid, feature, flag, inputdate, inputerdeptid, inputerdeptname, inputerdeptpathid, inputerfullname, inputername, isdelete, name, parentid, price, pricefrom, projectamount, remark, state, taxrate, unit, mlocation, pretaxprice, mdate, plocation, msupplier, msuppliertel, mcomponent);
    }

    @Override
    public String toString() {
        return flag +"-"+ name +"-"+price+"-"+mdate +"\n";
    }
}
