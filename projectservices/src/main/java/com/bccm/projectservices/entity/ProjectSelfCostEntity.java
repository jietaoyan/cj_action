package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 自营成本数据库表单
 */
@Entity
@Table(name = "project_self_cost", schema = "bccm")
public class ProjectSelfCostEntity {

    private String guid;
    private String fkguid;
    private String flag;
    private String remark;
    private Integer rowid;
    private BigDecimal selfbonus;
    private BigDecimal selfcost;
    private Double selfnum;
    private BigDecimal selfprocost;
    private BigDecimal selfsalary;
    private BigDecimal selfsalarytotel;
    private Long sysid;
    private BigDecimal totelcost;
    private String indexno;
    private String title;
    private String unit;

    @Id
    @Column(name = "guid", nullable = false, length = 255)
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
    @Column(name = "flag", nullable = true, length = 255)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
    @Column(name = "rowid", nullable = true)
    public Integer getRowid() {
        return rowid;
    }

    public void setRowid(Integer rowid) {
        this.rowid = rowid;
    }

    @Basic
    @Column(name = "selfbonus", nullable = true, precision = 2)
    public BigDecimal getSelfbonus() {
        return selfbonus;
    }

    public void setSelfbonus(BigDecimal selfbonus) {
        this.selfbonus = selfbonus;
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
    @Column(name = "selfnum", nullable = true, precision = 0)
    public Double getSelfnum() {
        return selfnum;
    }

    public void setSelfnum(Double selfnum) {
        this.selfnum = selfnum;
    }

    @Basic
    @Column(name = "selfprocost", nullable = true, precision = 2)
    public BigDecimal getSelfprocost() {
        return selfprocost;
    }

    public void setSelfprocost(BigDecimal selfprocost) {
        this.selfprocost = selfprocost;
    }

    @Basic
    @Column(name = "selfsalary", nullable = true, precision = 2)
    public BigDecimal getSelfsalary() {
        return selfsalary;
    }

    public void setSelfsalary(BigDecimal selfsalary) {
        this.selfsalary = selfsalary;
    }

    @Basic
    @Column(name = "selfsalarytotel", nullable = true, precision = 2)
    public BigDecimal getSelfsalarytotel() {
        return selfsalarytotel;
    }

    public void setSelfsalarytotel(BigDecimal selfsalarytotel) {
        this.selfsalarytotel = selfsalarytotel;
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
    @Column(name = "totelcost", nullable = true, precision = 2)
    public BigDecimal getTotelcost() {
        return totelcost;
    }

    public void setTotelcost(BigDecimal totelcost) {
        this.totelcost = totelcost;
    }

    @Basic
    @Column(name = "indexno", nullable = true, length = 45)
    public String getIndexno() {
        return indexno;
    }

    public void setIndexno(String indexno) {
        this.indexno = indexno;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectSelfCostEntity that = (ProjectSelfCostEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(rowid, that.rowid) &&
                Objects.equals(selfbonus, that.selfbonus) &&
                Objects.equals(selfcost, that.selfcost) &&
                Objects.equals(selfnum, that.selfnum) &&
                Objects.equals(selfprocost, that.selfprocost) &&
                Objects.equals(selfsalary, that.selfsalary) &&
                Objects.equals(selfsalarytotel, that.selfsalarytotel) &&
                Objects.equals(sysid, that.sysid) &&
                Objects.equals(totelcost, that.totelcost) &&
                Objects.equals(indexno, that.indexno) &&
                Objects.equals(title, that.title) &&
                Objects.equals(unit, that.unit)&&
                Objects.equals(fkguid, that.fkguid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, flag,  remark, rowid, selfbonus, selfcost, selfnum, selfprocost
                , selfsalary, selfsalarytotel, sysid, totelcost, indexno, title, unit, fkguid);
    }
}
