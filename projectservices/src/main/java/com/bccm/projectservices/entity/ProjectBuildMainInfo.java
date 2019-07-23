package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="Project_Build_Main_Info")
public class ProjectBuildMainInfo implements Serializable {
    private String guid;
    private Long sysid;
    private String state;
    private String remark;
    private String content;
    private Timestamp updatedt;
    private String updatebyusername;
    private String updatebyuserfullname;
    private Integer inputerdeptid;
    private Integer inputerdeptpathid;
    private String inputerdeptname;
    private String inputername;
    private String inputerfullname;
    private String projectname;
    private String projectnameapart;
    private String flag;
    private String projectid;
    private String projectnameapartid;

    @Id
    @Column(name = "GUID", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "SYSID", nullable = true)
    public Long getSysid() {
        return sysid;
    }

    public void setSysid(Long sysid) {
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
    @Column(name = "UPDATEDT", nullable = true)
    public Timestamp getUpdatedt() {
        return updatedt;
    }

    public void setUpdatedt(Timestamp updatedt) {
        this.updatedt = updatedt;
    }

    @Basic
    @Column(name = "UPDATEBYUSERNAME", nullable = true, length = 200)
    public String getUpdatebyusername() {
        return updatebyusername;
    }

    public void setUpdatebyusername(String updatebyusername) {
        this.updatebyusername = updatebyusername;
    }

    @Basic
    @Column(name = "UPDATEBYUSERFULLNAME", nullable = true, length = 200)
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
    @Column(name = "PROJECTNAME", nullable = true, length = 255)
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "PROJECTNAMEAPART", nullable = true, length = 255)
    public String getProjectnameapart() {
        return projectnameapart;
    }

    public void setProjectnameapart(String projectnameapart) {
        this.projectnameapart = projectnameapart;
    }

    @Basic
    @Column(name = "FLAG", nullable = true, length = 255)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "PROJECTID", nullable = true, length = 225)
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "PROJECTNAMEAPARTID", nullable = true, length = 255)
    public String getProjectnameapartid() {
        return projectnameapartid;
    }

    public void setProjectnameapartid(String projectnameapartid) {
        this.projectnameapartid = projectnameapartid;
    }

}
