package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "case_database", schema = "bccm")
public class CaseDatabaseEntity {
    private String guid;
    private String flag;
    private String name;
    private Date inputdate;
    private String relateproject;
    private String projectid;
    private String projectname;
    private String inputername;
    private String inputerusername;
    private String inpudept;
    private String purpose;
    private String result;
    private String remark;
    private String subject;
    private String process;
    private String attachment;
    private String deleteflag;
    private String relatepackid;
    private String parentid;
    private String prcess;
    private String pricefrom;
    private Double projectamount;
    private String relatepackname;

    @Id
    @Column(name = "guid", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
    @Column(name = "inputdate", nullable = true)
    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    @Basic
    @Column(name = "relateproject", nullable = true, length = 255)
    public String getRelateproject() {
        return relateproject;
    }

    public void setRelateproject(String relateproject) {
        this.relateproject = relateproject;
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
    @Column(name = "inputername", nullable = true, length = 255)
    public String getInputername() {
        return inputername;
    }

    public void setInputername(String inputername) {
        this.inputername = inputername;
    }

    @Basic
    @Column(name = "inputerusername", nullable = true, length = 255)
    public String getInputerusername() {
        return inputerusername;
    }

    public void setInputerusername(String inputerusername) {
        this.inputerusername = inputerusername;
    }

    @Basic
    @Column(name = "inpudept", nullable = true, length = 255)
    public String getInpudept() {
        return inpudept;
    }

    public void setInpudept(String inpudept) {
        this.inpudept = inpudept;
    }

    @Basic
    @Column(name = "purpose", nullable = true, length = 255)
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Basic
    @Column(name = "result", nullable = true, length = 255)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "subject", nullable = true, length = 255)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "process", nullable = true, length = 255)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "attachment", nullable = true, length = 255)
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Basic
    @Column(name = "deleteflag", nullable = true, length = 255)
    public String getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    @Basic
    @Column(name = "relatepackid", nullable = true, length = 225)
    public String getRelatepackid() {
        return relatepackid;
    }

    public void setRelatepackid(String relatepackid) {
        this.relatepackid = relatepackid;
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
    @Column(name = "prcess", nullable = true, length = 255)
    public String getPrcess() {
        return prcess;
    }

    public void setPrcess(String prcess) {
        this.prcess = prcess;
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
    @Column(name = "relatepackname", nullable = true, length = 225)
    public String getRelatepackname() {
        return relatepackname;
    }

    public void setRelatepackname(String relatepackname) {
        this.relatepackname = relatepackname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseDatabaseEntity that = (CaseDatabaseEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(name, that.name) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(relateproject, that.relateproject) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(projectname, that.projectname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(inputerusername, that.inputerusername) &&
                Objects.equals(inpudept, that.inpudept) &&
                Objects.equals(purpose, that.purpose) &&
                Objects.equals(result, that.result) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(process, that.process) &&
                Objects.equals(attachment, that.attachment) &&
                Objects.equals(deleteflag, that.deleteflag) &&
                Objects.equals(relatepackid, that.relatepackid) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(prcess, that.prcess) &&
                Objects.equals(pricefrom, that.pricefrom) &&
                Objects.equals(projectamount, that.projectamount) &&
                Objects.equals(relatepackname, that.relatepackname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, flag, name, inputdate, relateproject, projectid, projectname, inputername, inputerusername, inpudept, purpose, result, remark, subject, process, attachment, deleteflag, relatepackid, parentid, prcess, pricefrom, projectamount, relatepackname);
    }
}
