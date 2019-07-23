package com.bccm.projectservices.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "projectmanage_userinfor_logininfo", schema = "bccm", catalog = "")
public class ProjectmanageUserinforLoginEntity {
    private String guid;
    private String fkguid;
    private Long systemid;
    private String state;
    private String type;
    private String remark;
    private String content;
    private Timestamp updatedte;
    private Timestamp inputedte;
    private Integer userdeptid;
    private Integer userdeptpathid;
    private String userdeptname;
    private String username;
    private String userfullname;
    private String name;
    private String sex;
    private Integer prjno;
    private Timestamp inputdate;
    private String tel;
    private Integer deleteflag;
    private String id;
    private Timestamp logintime;
    private Timestamp logouttime;

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
    @Column(name = "USERDEPTID", nullable = true)
    public Integer getUserdeptid() {
        return userdeptid;
    }

    public void setUserdeptid(Integer userdeptid) {
        this.userdeptid = userdeptid;
    }

    @Basic
    @Column(name = "USERDEPTPATHID", nullable = true)
    public Integer getUserdeptpathid() {
        return userdeptpathid;
    }

    public void setUserdeptpathid(Integer userdeptpathid) {
        this.userdeptpathid = userdeptpathid;
    }

    @Basic
    @Column(name = "logintime", nullable = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp getLogintime() {
        return logintime;
    }

    public void setLogintime(Timestamp logintime) {
        this.logintime = logintime;
    }

    @Basic
    @Column(name = "logouttime", nullable = true)
    public Timestamp getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(Timestamp logouttime) {
        this.logouttime = logouttime;
    }

    @Basic
    @Column(name = "USERDEPTNAME", nullable = true, length = 255)
    public String getUserdeptname() {
        return userdeptname;
    }

    public void setUserdeptname(String userdeptname) {
        this.userdeptname = userdeptname;
    }

    @Basic
    @Column(name = "USERNAME", nullable = true, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "USERFULLNAME", nullable = true, length = 255)
    public String getUserfullname() {
        return userfullname;
    }

    public void setUserfullname(String userfullname) {
        this.userfullname = userfullname;
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
    @Column(name = "sex", nullable = true, length = 255)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
    @Column(name = "id", nullable = true, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectmanageUserinforLoginEntity that = (ProjectmanageUserinforLoginEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(fkguid, that.fkguid) &&
                Objects.equals(systemid, that.systemid) &&
                Objects.equals(state, that.state) &&
                Objects.equals(type, that.type) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(content, that.content) &&
                Objects.equals(updatedte, that.updatedte) &&
                Objects.equals(inputedte, that.inputedte) &&
                Objects.equals(userdeptid, that.userdeptid) &&
                Objects.equals(userdeptpathid, that.userdeptpathid) &&
                Objects.equals(userdeptname, that.userdeptname) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userfullname, that.userfullname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(prjno, that.prjno) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(tel, that.tel) &&
                Objects.equals(logintime, that.logintime) &&
                Objects.equals(logouttime, that.logouttime) &&
                Objects.equals(deleteflag, that.deleteflag) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, fkguid, systemid, state, type, remark, content, updatedte, inputedte, userdeptid, userdeptpathid, userdeptname, username, userfullname, name, sex, prjno, inputdate, tel, deleteflag, id , logouttime, logintime);
    }
}
