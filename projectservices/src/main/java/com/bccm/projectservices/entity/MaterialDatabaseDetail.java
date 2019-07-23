package com.bccm.projectservices.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "material_database_detail", schema = "bccm")
public class MaterialDatabaseDetail {

    private String guid;
    private String flag;
    private BigDecimal price;
    private BigDecimal pretaxprice;
    private String plocation;
    private String msupplier;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp mdate;
    private String pricefrom;
    private String taxrate;
    private String mlocation;
    private String msuppliertel;
    private String mcomponent;
    private String remark;

    @Id
    @Column(name = "GUID", nullable = false, length = 45)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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
    @Column(name = "PRICE", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "PRETAXPRICE", nullable = true, precision = 2)
    public BigDecimal getPretaxprice() {
        return pretaxprice;
    }

    public void setPretaxprice(BigDecimal pretaxprice) {
        this.pretaxprice = pretaxprice;
    }

    @Basic
    @Column(name = "PLOCATION", nullable = true, length = 255)
    public String getPlocation() {
        return plocation;
    }

    public void setPlocation(String plocation) {
        this.plocation = plocation;
    }

    @Basic
    @Column(name = "MSUPPLIER", nullable = true, length = 255)
    public String getMsupplier() {
        return msupplier;
    }

    public void setMsupplier(String msupplier) {
        this.msupplier = msupplier;
    }

    @Basic
    @Column(name = "MDATE", nullable = true)
    public Timestamp getMdate() {
        return mdate;
    }

    public void setMdate(Timestamp mdate) {
        this.mdate = mdate;
    }

    @Basic
    @Column(name = "PRICEFROM", nullable = true, length = 255)
    public String getPricefrom() {
        return pricefrom;
    }

    public void setPricefrom(String pricefrom) {
        this.pricefrom = pricefrom;
    }

    @Basic
    @Column(name = "TAXRATE", nullable = true, length = 45)
    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    @Basic
    @Column(name = "MLOCATION", nullable = true, length = 255)
    public String getMlocation() {
        return mlocation;
    }

    public void setMlocation(String mlocation) {
        this.mlocation = mlocation;
    }

    @Basic
    @Column(name = "MSUPPLIERTEL", nullable = true, length = 45)
    public String getMsuppliertel() {
        return msuppliertel;
    }

    public void setMsuppliertel(String msuppliertel) {
        this.msuppliertel = msuppliertel;
    }

    @Basic
    @Column(name = "MCOMPONENT", nullable = true, length = 500)
    public String getMcomponent() {
        return mcomponent;
    }

    public void setMcomponent(String mcomponent) {
        this.mcomponent = mcomponent;
    }

    @Basic
    @Column(name = "REMARK", nullable = true, length = 500)
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
        MaterialDatabaseDetail that = (MaterialDatabaseDetail) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(price, that.price) &&
                Objects.equals(pretaxprice, that.pretaxprice) &&
                Objects.equals(plocation, that.plocation) &&
                Objects.equals(msupplier, that.msupplier) &&
                Objects.equals(mdate, that.mdate) &&
                Objects.equals(pricefrom, that.pricefrom) &&
                Objects.equals(taxrate, that.taxrate) &&
                Objects.equals(mlocation, that.mlocation) &&
                Objects.equals(msuppliertel, that.msuppliertel) &&
                Objects.equals(mcomponent, that.mcomponent) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, flag, price, pretaxprice, plocation, msupplier, mdate, pricefrom, taxrate, mlocation, msuppliertel, mcomponent, remark);
    }

    @Override
    public String toString() {
        return flag +"-----"+price+"-"+mdate +"\n";
    }
}
