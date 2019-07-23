package com.bccm.projectservices.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "projectmanage_information_main", schema = "bccm")
public class ProjectmanageInformationMainEntity {
    private String guid;
    private Long systemid;
    private String state;
    private String prjname;
    private String type;
    private String remark;
    private String content;
    private Timestamp updatedte;
    //private Timestamp inputedte;
    private String updatebyusername;
    private String updatebyuserfullname;
    private Integer inputerdeptid;
    private Integer inputerdeptpathid;
    private String inputerdeptname;
    private String inputername;
    private String inputerfullname;
    private String name;
    private Integer prjno;
    private Integer sysprjid;
    private Timestamp inputdate;
    private String tel;
    private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private Integer deleteflag;
    private Integer typeid;
    private String parentid;
    private Integer layerid;
    private Integer childernId;
    private String money;
    private String id;
    private String area;

    @Basic
    @Column(name = "id", nullable = true)
    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    @Basic
    @Column(name = "money", nullable = true, precision = 0)
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Id
    @Column(name = "GUID", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "systemid", nullable = true)
    public Long getSystemid() {
        return systemid;
    }

    public void setSystemid(Long systemid) {
        this.systemid = systemid;
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
    @Column(name = "Prjname", nullable = true, length = 255)
    public String getPrjname() {
        return prjname;
    }

    public void setPrjname(String prjname) {
        this.prjname = prjname;
    }

    @Basic
    @Column(name = "area", nullable = true, length = 255)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
    @Column(name = "REMARK", nullable = true, length = 2000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "CONTENT", nullable = true, length = 2000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "UPDATEDTE", nullable = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Timestamp getUpdatedte() {
        return updatedte;
    }

    public void setUpdatedte(Timestamp updatedte) {
        this.updatedte = updatedte;
    }

//    @Basic
//    @Column(name = "INPUTEDTE", nullable = true)
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
//    public Timestamp getInputedte() {
//        return inputedte;
//    }
//
//    public void setInputedte(Timestamp inputedte) {
//        this.inputedte = inputedte;
//    }

    @Basic
    @Column(name = "UPDATEBYUSERNAME", nullable = true, length = 255)
    public String getUpdatebyusername() {
        return updatebyusername;
    }

    public void setUpdatebyusername(String updatebyusername) {
        this.updatebyusername = updatebyusername;
    }

    @Basic
    @Column(name = "UPDATEBYUSERFULLNAME", nullable = true, length = 255)
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
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Prjno", nullable = true)
    public Integer getPrjno() {
        return prjno;
    }

    public void setPrjno(Integer prjNo) {
        this.prjno = prjno;
    }

    @Basic
    @Column(name = "Sysprjid", nullable = true)
    public Integer getSysprjid() {
        return sysprjid;
    }

    public void setSysprjid(Integer sysPrjId) {
        this.sysprjid = sysprjid;
    }

    @Basic
    @Column(name = "INPUTDATE", nullable = true)
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
    @Column(name = "column5", nullable = true, length = 255)
    public String getColumn5() {
        return column5;
    }

    public void setColumn5(String column5) {
        this.column5 = column5;
    }

    @Basic
    @Column(name = "deleteflag", nullable = true)
    public Integer getDeleteflag() {
        return deleteflag;
    }

    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    @Basic
    @Column(name = "typeid", nullable = true)
    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Basic
    @Column(name = "parentid", nullable = true)
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "layerid", nullable = true)
    public Integer getLayerid() {
        return layerid;
    }

    public void setLayerid(Integer layerid) {
        this.layerid = layerid;
    }

    @Basic
    @Column(name = "childernid", nullable = true)
    public Integer getChildernId() {
        return childernId;
    }

    public void setChildernId(Integer childernId) {
        this.childernId = childernId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectmanageInformationMainEntity that = (ProjectmanageInformationMainEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(systemid, that.systemid) &&
                Objects.equals(id, that.id) &&
                Objects.equals(state, that.state) &&
                Objects.equals(prjname, that.prjname) &&
                Objects.equals(type, that.type) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(content, that.content) &&
                Objects.equals(updatedte, that.updatedte) &&
                //Objects.equals(inputedte, that.inputedte) &&
                Objects.equals(updatebyusername, that.updatebyusername) &&
                Objects.equals(updatebyuserfullname, that.updatebyuserfullname) &&
                Objects.equals(inputerdeptid, that.inputerdeptid) &&
                Objects.equals(inputerdeptpathid, that.inputerdeptpathid) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(prjno, that.prjno) &&
                Objects.equals(sysprjid, that.sysprjid) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(area, that.area) &&
                Objects.equals(column1, that.column1) &&
                Objects.equals(column2, that.column2) &&
                Objects.equals(column3, that.column3) &&
                Objects.equals(column4, that.column4) &&
                Objects.equals(column5, that.column5) &&
                Objects.equals(deleteflag, that.deleteflag) &&
                Objects.equals(typeid, that.typeid) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(layerid, that.layerid) &&
                Objects.equals(childernId, that.childernId)  &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, systemid, state, prjname, type, remark, content, updatedte, updatebyusername, updatebyuserfullname, inputerdeptid, inputerdeptpathid, inputerdeptname, inputername, inputerfullname, name, prjno, sysprjid, inputdate, tel, area, column1, column2, column3, column4, column5, deleteflag, typeid, parentid, layerid, childernId, money, id);
    }
}
