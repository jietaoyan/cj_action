package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name="list_Database")
public class ListDatabase {
        private String guid;
        private Long sysid;
        private String state;
        private String parentId;
        private String standardName;
        private String standardId;
        private String name;
        //国标编号
        private String flag;
        private BigDecimal price;
        private Double projectAmount;
        private String unit;
        private String pricefrom;
        private String feature;
        private String countruler;
        private String workContent;

    @Basic
    @Column(name = "FEATURE", nullable = true)
    public String getFeature() {
        return feature;
    }


    public void setFeature(String feature) {
        this.feature = feature;
    }
    @Basic
    @Column(name = "STANDARDID", nullable = true)
    public String getStandardId() {
        return standardId;
    }

    public void setStandardId(String standardId) {
        this.standardId = standardId;
    }

    @Basic
    @Column(name = "COUNTRULER", nullable = true)
    public String getCountruler() {
        return countruler;
    }

    public void setCountruler(String countruler) {
        this.countruler = countruler;
    }

    @Basic
    @Column(name = "WORKCONTENT", nullable = true)
    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    @Basic
    @Column(name = "STANDARDNAME", nullable = true)
    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    @Basic
    @Column(name = "NAME", nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PRICE", nullable = true)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "PROJECTAMOUNT", nullable = true)
    public Double getProjectAmount() {
        return projectAmount;
    }

    public void setProjectAmount(Double projectAmount) {
        this.projectAmount = projectAmount;
    }

    @Basic
    @Column(name = "UNIT", nullable = true)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "PRICEFROM", nullable = true)
    public String getPricefrom() {
        return pricefrom;
    }

    public void setPricefrom(String pricefrom) {
        this.pricefrom = pricefrom;
    }

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
        @Column(name = "PARENTID", nullable = true, length = 2000)
        public String getParentid() {
            return parentId;
        }

        public void setParentid(String parentid) {
            this.parentId = parentid;
        }


        @Basic
        @Column(name = "FLAG", nullable = true, length = 255)
        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

    }
