package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "projectmanage_information_permission", schema = "bccm")
public class ProjectmanageInformationPermissionEntity {
    private String guid;
    private String fkguid;
    private Long systemid;
    private String state;
    private String prjname;
    private String type;
    private String remark;
    private String content;
    private Timestamp updatedte;
    private Timestamp inputedte;
    private String updatebyusername;
    private String updatebyuserfullname;
    private Integer inputerdeptid;
    private Integer inputerdeptpathid;
    private String inputerdeptname;
    private String inputername;
    private String sharename;
    private String inputerfullname;
    private String sharefullname;
    private String name;
    private Integer prjno;
    private Integer sysprjid;
    private Timestamp inputdate;
    private String tel;
    private Integer deleteflag;
    private Integer typeid;
    private Integer parentid;
    private Integer layerid;
    private Integer childernid;
    private BigInteger money;
    private Long id;
    private String sharedname;
    @Id
    @Column(name = "GUID", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "FKGUID", nullable = true, length = 50)
    public String getFkguid() {
        return fkguid;
    }

    public void setFkguid(String fkguid) {
        this.fkguid = fkguid;
    }

    @Basic
    @Column(name = "Systemid", nullable = true)
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
    public Timestamp getUpdatedte() {
        return updatedte;
    }

    public void setUpdatedte(Timestamp updatedte) {
        this.updatedte = updatedte;
    }

    @Basic
    @Column(name = "INPUTEDTE", nullable = true)
    public Timestamp getInputedte() {
        return inputedte;
    }

    public void setInputedte(Timestamp inputedte) {
        this.inputedte = inputedte;
    }

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
    @Column(name = "SHARENAME", nullable = true, length = 255)
    public String getSharename() {
        return sharename;
    }

    public void setSharename(String sharename) {
        this.sharename = sharename;
    }


    @Basic
    @Column(name = "sharedname", nullable = true, length = 255)
    public String getSharedname() {
        return sharedname;
    }

    public void setSharedname(String sharedname) {
        this.sharedname = sharedname;
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
    @Column(name = "SHAREFULLNAME", nullable = true, length = 255)
    public String getSharefullname() {
        return sharefullname;
    }

    public void setSharefullname(String sharefullname) {
        this.sharefullname = sharefullname;
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

    public void setPrjno(Integer prjno) {
        this.prjno = prjno;
    }

    @Basic
    @Column(name = "Sysprjid", nullable = true)
    public Integer getSysprjid() {
        return sysprjid;
    }

    public void setSysprjid(Integer sysprjid) {
        this.sysprjid = sysprjid;
    }

    @Basic
    @Column(name = "INPUTDATE", nullable = true)
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
    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
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
    public Integer getChildernid() {
        return childernid;
    }

    public void setChildernid(Integer childernid) {
        this.childernid = childernid;
    }

    @Basic
    @Column(name = "money", nullable = true, precision = 0)
    public BigInteger getMoney() {
        return money;
    }

    public void setMoney(BigInteger money) {
        this.money = money;
    }

    @Basic
    @Column(name = "id", nullable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectmanageInformationPermissionEntity that = (ProjectmanageInformationPermissionEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(fkguid, that.fkguid) &&
                Objects.equals(systemid, that.systemid) &&
                Objects.equals(state, that.state) &&
                Objects.equals(prjname, that.prjname) &&
                Objects.equals(type, that.type) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(content, that.content) &&
                Objects.equals(updatedte, that.updatedte) &&
                Objects.equals(inputedte, that.inputedte) &&
                Objects.equals(updatebyusername, that.updatebyusername) &&
                Objects.equals(updatebyuserfullname, that.updatebyuserfullname) &&
                Objects.equals(inputerdeptid, that.inputerdeptid) &&
                Objects.equals(inputerdeptpathid, that.inputerdeptpathid) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(sharename, that.sharename) &&
                Objects.equals(sharedname, that.sharedname) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(sharefullname, that.sharefullname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(prjno, that.prjno) &&
                Objects.equals(sysprjid, that.sysprjid) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(deleteflag, that.deleteflag) &&
                Objects.equals(typeid, that.typeid) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(layerid, that.layerid) &&
                Objects.equals(childernid, that.childernid) &&
                Objects.equals(money, that.money) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, fkguid, systemid, state, prjname, type, remark, content, updatedte, inputedte, updatebyusername, updatebyuserfullname, inputerdeptid, inputerdeptpathid, inputerdeptname, inputername, sharename, inputerfullname, sharefullname, name, prjno, sysprjid, inputdate, tel, deleteflag, typeid, parentid, layerid, childernid, money, id,sharedname);
    }
}
