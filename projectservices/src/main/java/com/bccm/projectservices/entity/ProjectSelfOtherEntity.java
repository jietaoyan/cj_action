package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "project_self_other", schema = "bccm")
public class ProjectSelfOtherEntity {
    private String guid;
    private String fkguid;
    private Long sysid;
    private String flag;
    private Integer rowid;
    private String title;
    private String unit;
    private Double selfnum;
    private BigDecimal selfcost;
    private BigDecimal totelcost;
    private String remark;

    @Id
    @Column(name = "guid", nullable = false, length = 45)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "fkguid", nullable = true, length = 45)
    public String getFkguid() {
        return fkguid;
    }

    public void setFkguid(String fkguid) {
        this.fkguid = fkguid;
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
    @Column(name = "flag", nullable = true, length = 45)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "rowid", nullable = true)
    public Integer getRowid() {
        return rowid;
    }

    public void setRowid(Integer rowid) {
        this.rowid = rowid;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 45)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "selfnum", nullable = true, precision = 0)
    public Double getSelfnum() {
        return selfnum;
    }

    public void setSelfnum(Double selfnum) {
        this.selfnum = selfnum;
    }

    @Basic
    @Column(name = "selfcost", nullable = true, precision = 2)
    public BigDecimal getSelfcost() {
        return selfcost;
    }

    public void setSelfcost(BigDecimal selfcost) {
        this.selfcost = selfcost;
    }

    @Basic
    @Column(name = "totelcost", nullable = true, precision = 2)
    public BigDecimal getTotelcost() {
        return totelcost;
    }

    public void setTotelcost(BigDecimal totelcost) {
        this.totelcost = totelcost;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectSelfOtherEntity that = (ProjectSelfOtherEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(fkguid, that.fkguid) &&
                Objects.equals(sysid, that.sysid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(rowid, that.rowid) &&
                Objects.equals(title, that.title) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(selfnum, that.selfnum) &&
                Objects.equals(selfcost, that.selfcost) &&
                Objects.equals(totelcost, that.totelcost) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, fkguid, sysid, flag, rowid, title, unit, selfnum, selfcost, totelcost, remark);
    }
}
