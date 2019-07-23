package com.bccm.projectservices.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "bag_clear_database", schema = "bccm", catalog = "")
public class BagClearDatabase {
    private String guid;
    private String countruler;
    private String flag;
    private String name;
    private String parentid;
    private BigDecimal price;
    private String pricefrom;
    private Double projectamount;
    private String projectid;
    private String projectname;
    private String relatematerialamount;
    private String relatematerialamount2;
    private String relatematerialid;
    private String relatematerialid2;
    private String relatematerialname;
    private String relatematerialname2;
    private String state;
    private Long sysid;
    private String unit;
    private String workcontent;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date inputdate;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date collectdate;
    private String packtype;

    @Id
    @Column(name = "guid", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "countruler", nullable = true, length = 255)
    public String getCountruler() {
        return countruler;
    }

    public void setCountruler(String countruler) {
        this.countruler = countruler;
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
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parentid", nullable = true, length = 2000)
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
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
    @Column(name = "projectid", nullable = true, length = 255)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "projectname", nullable = true, length = 255)
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "relatematerialamount", nullable = true, length = 255)
    public String getRelatematerialamount() {
        return relatematerialamount;
    }

    public void setRelatematerialamount(String relatematerialamount) {
        this.relatematerialamount = relatematerialamount;
    }

    @Basic
    @Column(name = "relatematerialamount2", nullable = true, length = 255)
    public String getRelatematerialamount2() {
        return relatematerialamount2;
    }

    public void setRelatematerialamount2(String relatematerialamount2) {
        this.relatematerialamount2 = relatematerialamount2;
    }

    @Basic
    @Column(name = "relatematerialid", nullable = true, length = 255)
    public String getRelatematerialid() {
        return relatematerialid;
    }

    public void setRelatematerialid(String relatematerialid) {
        this.relatematerialid = relatematerialid;
    }

    @Basic
    @Column(name = "relatematerialid2", nullable = true, length = 255)
    public String getRelatematerialid2() {
        return relatematerialid2;
    }

    public void setRelatematerialid2(String relatematerialid2) {
        this.relatematerialid2 = relatematerialid2;
    }

    @Basic
    @Column(name = "relatematerialname", nullable = true, length = 255)
    public String getRelatematerialname() {
        return relatematerialname;
    }

    public void setRelatematerialname(String relatematerialname) {
        this.relatematerialname = relatematerialname;
    }

    @Basic
    @Column(name = "relatematerialname2", nullable = true, length = 255)
    public String getRelatematerialname2() {
        return relatematerialname2;
    }

    public void setRelatematerialname2(String relatematerialname2) {
        this.relatematerialname2 = relatematerialname2;
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
    @Column(name = "sysid", nullable = true)
    public Long getSysid() {
        return sysid;
    }

    public void setSysid(Long sysid) {
        this.sysid = sysid;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 255)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "workcontent", nullable = true, length = 255)
    public String getWorkcontent() {
        return workcontent;
    }

    public void setWorkcontent(String workcontent) {
        this.workcontent = workcontent;
    }

    @Basic
    @Column(name = "inputdate", nullable = true)
    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    @Basic
    @Column(name = "collectdate", nullable = true)
    public Date getCollectdate() {
        return collectdate;
    }

    public void setCollectdate(Date collectdate) {
        this.collectdate = collectdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagClearDatabase that = (BagClearDatabase) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(countruler, that.countruler) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(price, that.price) &&
                Objects.equals(pricefrom, that.pricefrom) &&
                Objects.equals(projectamount, that.projectamount) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(projectname, that.projectname) &&
                Objects.equals(relatematerialamount, that.relatematerialamount) &&
                Objects.equals(relatematerialamount2, that.relatematerialamount2) &&
                Objects.equals(relatematerialid, that.relatematerialid) &&
                Objects.equals(relatematerialid2, that.relatematerialid2) &&
                Objects.equals(relatematerialname, that.relatematerialname) &&
                Objects.equals(relatematerialname2, that.relatematerialname2) &&
                Objects.equals(state, that.state) &&
                Objects.equals(sysid, that.sysid) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(workcontent, that.workcontent) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(collectdate, that.collectdate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, countruler, flag, name, parentid, price, pricefrom, projectamount, projectid, projectname, relatematerialamount, relatematerialamount2, relatematerialid, relatematerialid2, relatematerialname, relatematerialname2, state, sysid, unit, workcontent, inputdate, collectdate);
    }

    @Basic
    @Column(name = "packtype", nullable = true, length = 255)
    public String getPacktype() {
        return packtype;
    }

    public void setPacktype(String packtype) {
        this.packtype = packtype;
    }
}
