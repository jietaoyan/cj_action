package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 税金数据库表单
 */
@Entity
@Table(name = "project_tax_cost", schema = "bccm")
public class ProjectTaxCostEntity {
    private Long sysid;
    private String guid;
    private String inputerdeptid;
    private String inputerdeptpathid;
    private String inputerdeptname;
    private String inputername;
    private String inputerfullname;
    private String phonenum;
    private Integer isdelete;
    private String flag;
    private String projectid;
    private String projectname;
    private Double taxrate;
    private BigDecimal deductioncost;
    private BigDecimal taxtotalcost;
    private LocalDateTime inputdate;

    @Column(name = "SYSID")
    public Long getSysid() {
        return sysid;
    }

    public void setSysid(Long sysid) {
        this.sysid = sysid;
    }

    @Id
    @Column(name = "GUID")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "INPUTERDEPTID")
    public String getInputerdeptid() {
        return inputerdeptid;
    }

    public void setInputerdeptid(String inputerdeptid) {
        this.inputerdeptid = inputerdeptid;
    }

    @Basic
    @Column(name = "INPUTERDEPTPATHID")
    public String getInputerdeptpathid() {
        return inputerdeptpathid;
    }

    public void setInputerdeptpathid(String inputerdeptpathid) {
        this.inputerdeptpathid = inputerdeptpathid;
    }

    @Basic
    @Column(name = "INPUTERDEPTNAME")
    public String getInputerdeptname() {
        return inputerdeptname;
    }

    public void setInputerdeptname(String inputerdeptname) {
        this.inputerdeptname = inputerdeptname;
    }

    @Basic
    @Column(name = "INPUTERNAME")
    public String getInputername() {
        return inputername;
    }

    public void setInputername(String inputername) {
        this.inputername = inputername;
    }

    @Basic
    @Column(name = "INPUTERFULLNAME")
    public String getInputerfullname() {
        return inputerfullname;
    }

    public void setInputerfullname(String inputerfullname) {
        this.inputerfullname = inputerfullname;
    }

    @Basic
    @Column(name = "PHONENUM")
    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    @Basic
    @Column(name = "ISDELETE")
    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    @Basic
    @Column(name = "FLAG")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "PROJECTID")
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "PROJECTNAME")
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "TAXRATE")
    public Double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Double taxrate) {
        this.taxrate = taxrate;
    }

    @Basic
    @Column(name = "DEDUCTIONCOST")
    public BigDecimal getDeductioncost() {
        return deductioncost;
    }

    public void setDeductioncost(BigDecimal deductioncost) {
        this.deductioncost = deductioncost;
    }

    @Basic
    @Column(name = "TAXTOTALCOST")
    public BigDecimal getTaxtotalcost() {
        return taxtotalcost;
    }

    public void setTaxtotalcost(BigDecimal taxtotalcost) {
        this.taxtotalcost = taxtotalcost;
    }

    @Basic
    @Column(name = "INPUTDATE")
    public LocalDateTime getInputdate() {
        return inputdate;
    }

    public void setInputdate(LocalDateTime inputdate) {
        this.inputdate = inputdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTaxCostEntity that = (ProjectTaxCostEntity) o;
        return sysid == that.sysid &&
                Objects.equals(guid, that.guid) &&
                Objects.equals(inputerdeptid, that.inputerdeptid) &&
                Objects.equals(inputerdeptpathid, that.inputerdeptpathid) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(phonenum, that.phonenum) &&
                Objects.equals(isdelete, that.isdelete) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(projectname, that.projectname) &&
                Objects.equals(taxrate, that.taxrate) &&
                Objects.equals(deductioncost, that.deductioncost) &&
                Objects.equals(taxtotalcost, that.taxtotalcost)&&
                Objects.equals(inputdate, that.inputdate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sysid, guid, inputerdeptid, inputerdeptpathid, inputerdeptname, inputername, inputerfullname, phonenum, isdelete, flag, projectid, projectname, taxrate, deductioncost, taxtotalcost, inputdate);
    }
}
