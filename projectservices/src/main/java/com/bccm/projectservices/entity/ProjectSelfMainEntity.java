package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "project_self_main", schema = "bccm")
public class ProjectSelfMainEntity {
    private String guid;
    private String flag;
    private LocalDateTime inputdate;
    private String inputerdeptid;
    private String inputerdeptname;
    private String inputerfullname;
    private String inputername;
    private Integer isdelete;
    private String projectid;
    private String projectname;
    private BigDecimal selftotalcost;

    @Id
    @Column(name = "guid", nullable = false, length = 45)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "flag", nullable = true, length = 45)
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
    @Column(name = "inputerfullname", nullable = true, length = 255)
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
    @Column(name = "projectid", nullable = true, length = 45)
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
    @Column(name = "selftotalcost", nullable = true, precision = 2)
    public BigDecimal getSelftotalcost() {
        return selftotalcost;
    }

    public void setSelftotalcost(BigDecimal selftotalcost) {
        this.selftotalcost = selftotalcost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectSelfMainEntity that = (ProjectSelfMainEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(inputerdeptid, that.inputerdeptid) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(isdelete, that.isdelete) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(projectname, that.projectname) &&
                Objects.equals(selftotalcost, that.selftotalcost);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, flag, inputdate, inputerdeptid, inputerdeptname, inputerfullname, inputername
                , isdelete, projectid, projectname, selftotalcost);
    }
}
