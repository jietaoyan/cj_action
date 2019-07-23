package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "project_measure_detail", schema = "bccm")
public class ProjectMeasureDetail {
    private String guid;
    private String fkguid;
    private String name;
    private BigDecimal cost;
    private Float rate;
    private String remark;
    private String flag;//备用字段
    private Integer sortid;

    @Id
    @Column(name = "GUID", nullable = false, length = 45)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "FKGUID", nullable = true, length = 45)
    public String getFkguid() {
        return fkguid;
    }

    public void setFkguid(String fkguid) {
        this.fkguid = fkguid;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "COST", nullable = true, precision = 2)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "RATE", nullable = true)
    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "FLAG", nullable = true, length = 45)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "SORTID", nullable = true)
    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectMeasureDetail that = (ProjectMeasureDetail) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(fkguid, that.fkguid) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(sortid, that.sortid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, fkguid, name, cost, rate, remark, flag, sortid);
    }
}
