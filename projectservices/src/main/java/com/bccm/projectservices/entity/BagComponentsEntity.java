package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "bag_components", schema = "bccm")
public class BagComponentsEntity{
    private String guid;
    private String flag;
    private String name;
    private Date inputdate;
    private String type;
    private String relatepackid;
    private String relatepackname;
    private Double amount;
    private BigDecimal price;

    @Id
    @Column(name = "guid", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "inputdate", nullable = true)
    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
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
    @Column(name = "relatepackid", nullable = true, length = 225)
    public String getRelatepackid() {
        return relatepackid;
    }

    public void setRelatepackid(String relatepackid) {
        this.relatepackid = relatepackid;
    }

    @Basic
    @Column(name = "relatepackname", nullable = true, length = 225)
    public String getRelatepackname() {
        return relatepackname;
    }

    public void setRelatepackname(String relatepackname) {
        this.relatepackname = relatepackname;
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagComponentsEntity that = (BagComponentsEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(name, that.name) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(type, that.type) &&
                Objects.equals(relatepackid, that.relatepackid) &&
                Objects.equals(relatepackname, that.relatepackname) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, flag, name, inputdate, type, relatepackid, relatepackname, amount, price);
    }
}
