package com.bccm.projectservices.entity;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="list_project_edit")
public class ListProjectEdit {
    private String guid;
    private Long sysid;
    private String state;
    private String parentId;
    private BigDecimal amount;
    private String remark;
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
    @Column(name = "PARENTID", nullable = true, length = 2000)
    public String getParentid() {
        return parentId;
    }

    public void setParentid(String parentid) {
        this.parentId = parentid;
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


    @Basic
    @Column(name = "AMOUNT", nullable = true)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setProjectnameapartid(String projectnameapartid) {
        this.projectnameapartid = projectnameapartid;
    }


    public ListProjectEdit(String guid, Long sysid, String state, String parentId, BigDecimal amount, String remark, String inputerdeptname, String inputername, String inputerfullname, String projectname, String projectnameapart, String flag, String projectid, String projectnameapartid) {
        this.guid = guid;
        this.sysid = sysid;
        this.state = state;
        this.parentId = parentId;
        this.amount = amount;
        this.remark = remark;
        this.inputerdeptname = inputerdeptname;
        this.inputername = inputername;
        this.inputerfullname = inputerfullname;
        this.projectname = projectname;
        this.projectnameapart = projectnameapart;
        this.flag = flag;
        this.projectid = projectid;
        this.projectnameapartid = projectnameapartid;
    }

    public  ListProjectEdit(){}
}

