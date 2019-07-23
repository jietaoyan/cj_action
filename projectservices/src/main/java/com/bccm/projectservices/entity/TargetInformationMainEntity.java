package com.bccm.projectservices.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "target_information_main", schema = "bccm")
public class TargetInformationMainEntity {
    private String guid;
    private String sysid;
    private String state;
    private String remark;
    private String content;
    private String companypath;
    private Timestamp updatedt;
    private String updatebyusername;
    private String updatebyuserfullname;
    private Integer inputerdeptid;
    private Integer inputerdeptpathid;
    private String inputerdeptname;
    private String companyname;
    private String inputername;
    private String inputerfullname;
    private String type;
    private String name;
    private String prjno;
    private String prjname;
    private Integer sysprjid;
    private Timestamp inputdate;
    private String tel;
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String target;
    private Integer deleteflag;

    @Id
    @Column(name = "GUID", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "sysid", nullable = true)
    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 2000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "COMPANYPATH", nullable = true, length = 2000)
    public String getCompanypath() {
        return companypath;
    }

    public void setCompanypath(String companypath) {
        this.companypath = companypath;
    }

    @Basic
    @Column(name = "UPDATEDT", nullable = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Timestamp getUpdatedt() {
        return updatedt;
    }

    public void setUpdatedt(Timestamp updatedt) {
        this.updatedt = updatedt;
    }

    @Basic
    @Column(name = "UPDATEBYUSERNAME", nullable = true, length = 0)
    public String getUpdatebyusername() {
        return updatebyusername;
    }

    public void setUpdatebyusername(String updatebyusername) {
        this.updatebyusername = updatebyusername;
    }

    @Basic
    @Column(name = "UPDATEBYUSERFULLNAME", nullable = true, length = 0)
    public String getUpdatebyuserfullname() {
        return updatebyuserfullname;
    }

    public void setUpdatebyuserfullname(String updatebyuserfullname) {
        this.updatebyuserfullname = updatebyuserfullname;
    }

    @Basic
    @Column(name = "INPUTERDEPTID", nullable = true)
    public Integer getInputerdeptid() {
        return inputerdeptid;
    }

    public void setInputerdeptid(Integer inputerdeptid) {
        this.inputerdeptid = inputerdeptid;
    }

    @Basic
    @Column(name = "INPUTERDEPTPATHID", nullable = true)
    public Integer getInputerdeptpathid() {
        return inputerdeptpathid;
    }

    public void setInputerdeptpathid(Integer inputerdeptpathid) {
        this.inputerdeptpathid = inputerdeptpathid;
    }

    @Basic
    @Column(name = "INPUTERDEPTNAME", nullable = true, length = 255)
    public String getInputerdeptname() {
        return inputerdeptname;
    }

    public void setInputerdeptname(String inputerdeptname) {
        this.inputerdeptname = inputerdeptname;
    }

    @Basic
    @Column(name = "COMPANYNAME", nullable = true, length = 255)
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @Basic
    @Column(name = "INPUTERNAME", nullable = true, length = 255)
    public String getInputername() {
        return inputername;
    }

    public void setInputername(String inputername) {
        this.inputername = inputername;
    }

    @Basic
    @Column(name = "INPUTERFULLNAME", nullable = true, length = 255)
    public String getInputerfullname() {
        return inputerfullname;
    }

    public void setInputerfullname(String inputerfullname) {
        this.inputerfullname = inputerfullname;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "prjno", nullable = true, length = 255)
    public String getPrjno() {
        return prjno;
    }

    public void setPrjno(String prjno) {
        this.prjno = prjno;
    }

    @Basic
    @Column(name = "prjname", nullable = true, length = 255)
    public String getPrjname() {
        return prjname;
    }

    public void setPrjname(String prjname) {
        this.prjname = prjname;
    }

    @Basic
    @Column(name = "sysprjid", nullable = true)
    public Integer getSysprjid() {
        return sysprjid;
    }

    public void setSysprjid(Integer sysprjid) {
        this.sysprjid = sysprjid;
    }

    @Basic
    @Column(name = "inputdate", nullable = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Timestamp getInputdate() {
        return inputdate;
    }

    public void setInputdate(Timestamp inputdate) {
        this.inputdate = inputdate;
    }

    @Basic
    @Column(name = "TEL", nullable = true, length = 255)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "column1", nullable = true, length = 255)
    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    @Basic
    @Column(name = "column2", nullable = true, length = 255)
    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    @Basic
    @Column(name = "column3", nullable = true, length = 255)
    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    @Basic
    @Column(name = "column4", nullable = true, length = 255)
    public String getColumn4() {
        return column4;
    }

    public void setColumn4(String column4) {
        this.column4 = column4;
    }

    @Basic
    @Column(name = "target", nullable = true, length = 255)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Basic
    @Column(name = "deleteflag", nullable = true)
    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetInformationMainEntity that = (TargetInformationMainEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(sysid, that.sysid) &&
                Objects.equals(state, that.state) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(content, that.content) &&
                Objects.equals(companypath, that.companypath) &&
                Objects.equals(updatedt, that.updatedt) &&
                Objects.equals(updatebyusername, that.updatebyusername) &&
                Objects.equals(updatebyuserfullname, that.updatebyuserfullname) &&
                Objects.equals(inputerdeptid, that.inputerdeptid) &&
                Objects.equals(inputerdeptpathid, that.inputerdeptpathid) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(companyname, that.companyname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(prjno, that.prjno) &&
                Objects.equals(prjname, that.prjname) &&
                Objects.equals(sysprjid, that.sysprjid) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(column1, that.column1) &&
                Objects.equals(column2, that.column2) &&
                Objects.equals(column3, that.column3) &&
                Objects.equals(column4, that.column4) &&
                Objects.equals(target, that.target) &&
                Objects.equals(deleteflag, that.deleteflag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, sysid, state, remark, content, companypath, updatedt, updatebyusername, updatebyuserfullname, inputerdeptid, inputerdeptpathid, inputerdeptname, companyname, inputername, inputerfullname, type, name, prjno, prjname, sysprjid, inputdate, tel, column1, column2, column3, column4, target, deleteflag);
    }
}
