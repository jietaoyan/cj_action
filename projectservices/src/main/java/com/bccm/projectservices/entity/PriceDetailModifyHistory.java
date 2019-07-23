package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "price_detail_modify_history", schema = "bccm")
public class PriceDetailModifyHistory {
    private String guid;
    private String fkguid;
    private String flag;
    private String name;
    private String type;
    private BigDecimal price;
    private Timestamp updatedt;

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
    @Column(name = "FLAG", nullable = true, length = 50)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 233)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "UPDATEDT", nullable = true)
    public Timestamp getUpdatedt() {
        return updatedt;
    }

    public void setUpdatedt(Timestamp updatedt) {
        this.updatedt = updatedt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDetailModifyHistory that = (PriceDetailModifyHistory) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(fkguid, that.fkguid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(price, that.price) &&
                Objects.equals(updatedt, that.updatedt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, fkguid, flag, name, type, price, updatedt);
    }
}
