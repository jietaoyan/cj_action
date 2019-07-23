package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "price_detail_list_history", schema = "bccm", catalog = "")
public class PriceDetailListHistoryEntity {
    private String guid;
    private BigDecimal bagclearprice;
    private String buildingprojectid;
    private String content;
    private String countruler;
    private String feature;
    private String flag;
    private Integer flagaddition;
    private Integer inputerdeptid;
    private String inputerdeptname;
    private Integer inputerdeptpathid;
    private String inputerfullname;
    private String inputername;
    private BigDecimal machinecost;
    private BigDecimal materialcost;
    private BigDecimal materialprice;
    private String name;
    private String originflag;
    private String parentid;
    private BigDecimal personcost;
    private BigDecimal price;
    private String pricefrom;
    private Double projectamount;
    private String projectid;
    private String projectname;
    private String projectnameapart;
    private String projectnameapartid;
    private String relatematerialamount;
    private String relatematerialdetailamount;
    private String relatematerialdetailid;
    private String relatematerialdetailname;
    private String relatematerialid;
    private String relatematerialname;
    private String remark;
    private String rootprojectid;
    private Integer sortid;
    private Integer sortids;
    private String state;
    private Long sysid;
    private String taxrate;
    private BigDecimal total;
    private String type;
    private String unit;
    private String updatebyuserfullname;
    private String updatebyusername;
    private Timestamp updatedt;
    private String workcontent;

    @Id
    @Column(name = "guid")
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "bagclearprice")
    public BigDecimal getBagclearprice() {
        return bagclearprice;
    }

    public void setBagclearprice(BigDecimal bagclearprice) {
        this.bagclearprice = bagclearprice;
    }

    @Basic
    @Column(name = "buildingprojectid")
    public String getBuildingprojectid() {
        return buildingprojectid;
    }

    public void setBuildingprojectid(String buildingprojectid) {
        this.buildingprojectid = buildingprojectid;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "countruler")
    public String getCountruler() {
        return countruler;
    }

    public void setCountruler(String countruler) {
        this.countruler = countruler;
    }

    @Basic
    @Column(name = "feature")
    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    @Basic
    @Column(name = "flag")
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Basic
    @Column(name = "flagaddition")
    public Integer getFlagaddition() {
        return flagaddition;
    }

    public void setFlagaddition(Integer flagaddition) {
        this.flagaddition = flagaddition;
    }

    @Basic
    @Column(name = "inputerdeptid")
    public Integer getInputerdeptid() {
        return inputerdeptid;
    }

    public void setInputerdeptid(Integer inputerdeptid) {
        this.inputerdeptid = inputerdeptid;
    }

    @Basic
    @Column(name = "inputerdeptname")
    public String getInputerdeptname() {
        return inputerdeptname;
    }

    public void setInputerdeptname(String inputerdeptname) {
        this.inputerdeptname = inputerdeptname;
    }

    @Basic
    @Column(name = "inputerdeptpathid")
    public Integer getInputerdeptpathid() {
        return inputerdeptpathid;
    }

    public void setInputerdeptpathid(Integer inputerdeptpathid) {
        this.inputerdeptpathid = inputerdeptpathid;
    }

    @Basic
    @Column(name = "inputerfullname")
    public String getInputerfullname() {
        return inputerfullname;
    }

    public void setInputerfullname(String inputerfullname) {
        this.inputerfullname = inputerfullname;
    }

    @Basic
    @Column(name = "inputername")
    public String getInputername() {
        return inputername;
    }

    public void setInputername(String inputername) {
        this.inputername = inputername;
    }

    @Basic
    @Column(name = "machinecost")
    public BigDecimal getMachinecost() {
        return machinecost;
    }

    public void setMachinecost(BigDecimal machinecost) {
        this.machinecost = machinecost;
    }

    @Basic
    @Column(name = "materialcost")
    public BigDecimal getMaterialcost() {
        return materialcost;
    }

    public void setMaterialcost(BigDecimal materialcost) {
        this.materialcost = materialcost;
    }

    @Basic
    @Column(name = "materialprice")
    public BigDecimal getMaterialprice() {
        return materialprice;
    }

    public void setMaterialprice(BigDecimal materialprice) {
        this.materialprice = materialprice;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "originflag")
    public String getOriginflag() {
        return originflag;
    }

    public void setOriginflag(String originflag) {
        this.originflag = originflag;
    }

    @Basic
    @Column(name = "parentid")
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    @Basic
    @Column(name = "personcost")
    public BigDecimal getPersoncost() {
        return personcost;
    }

    public void setPersoncost(BigDecimal personcost) {
        this.personcost = personcost;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "pricefrom")
    public String getPricefrom() {
        return pricefrom;
    }

    public void setPricefrom(String pricefrom) {
        this.pricefrom = pricefrom;
    }

    @Basic
    @Column(name = "projectamount")
    public Double getProjectamount() {
        return projectamount;
    }

    public void setProjectamount(Double projectamount) {
        this.projectamount = projectamount;
    }

    @Basic
    @Column(name = "projectid")
    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "projectname")
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Basic
    @Column(name = "projectnameapart")
    public String getProjectnameapart() {
        return projectnameapart;
    }

    public void setProjectnameapart(String projectnameapart) {
        this.projectnameapart = projectnameapart;
    }

    @Basic
    @Column(name = "projectnameapartid")
    public String getProjectnameapartid() {
        return projectnameapartid;
    }

    public void setProjectnameapartid(String projectnameapartid) {
        this.projectnameapartid = projectnameapartid;
    }

    @Basic
    @Column(name = "relatematerialamount")
    public String getRelatematerialamount() {
        return relatematerialamount;
    }

    public void setRelatematerialamount(String relatematerialamount) {
        this.relatematerialamount = relatematerialamount;
    }

    @Basic
    @Column(name = "relatematerialdetailamount")
    public String getRelatematerialdetailamount() {
        return relatematerialdetailamount;
    }

    public void setRelatematerialdetailamount(String relatematerialdetailamount) {
        this.relatematerialdetailamount = relatematerialdetailamount;
    }

    @Basic
    @Column(name = "relatematerialdetailid")
    public String getRelatematerialdetailid() {
        return relatematerialdetailid;
    }

    public void setRelatematerialdetailid(String relatematerialdetailid) {
        this.relatematerialdetailid = relatematerialdetailid;
    }

    @Basic
    @Column(name = "relatematerialdetailname")
    public String getRelatematerialdetailname() {
        return relatematerialdetailname;
    }

    public void setRelatematerialdetailname(String relatematerialdetailname) {
        this.relatematerialdetailname = relatematerialdetailname;
    }

    @Basic
    @Column(name = "relatematerialid")
    public String getRelatematerialid() {
        return relatematerialid;
    }

    public void setRelatematerialid(String relatematerialid) {
        this.relatematerialid = relatematerialid;
    }

    @Basic
    @Column(name = "relatematerialname")
    public String getRelatematerialname() {
        return relatematerialname;
    }

    public void setRelatematerialname(String relatematerialname) {
        this.relatematerialname = relatematerialname;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "rootprojectid")
    public String getRootprojectid() {
        return rootprojectid;
    }

    public void setRootprojectid(String rootprojectid) {
        this.rootprojectid = rootprojectid;
    }

    @Basic
    @Column(name = "sortid")
    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    @Basic
    @Column(name = "sortids")
    public Integer getSortids() {
        return sortids;
    }

    public void setSortids(Integer sortids) {
        this.sortids = sortids;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "sysid")
    public Long getSysid() {
        return sysid;
    }

    public void setSysid(Long sysid) {
        this.sysid = sysid;
    }

    @Basic
    @Column(name = "taxrate")
    public String getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(String taxrate) {
        this.taxrate = taxrate;
    }

    @Basic
    @Column(name = "total")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "updatebyuserfullname")
    public String getUpdatebyuserfullname() {
        return updatebyuserfullname;
    }

    public void setUpdatebyuserfullname(String updatebyuserfullname) {
        this.updatebyuserfullname = updatebyuserfullname;
    }

    @Basic
    @Column(name = "updatebyusername")
    public String getUpdatebyusername() {
        return updatebyusername;
    }

    public void setUpdatebyusername(String updatebyusername) {
        this.updatebyusername = updatebyusername;
    }

    @Basic
    @Column(name = "updatedt")
    public Timestamp getUpdatedt() {
        return updatedt;
    }

    public void setUpdatedt(Timestamp updatedt) {
        this.updatedt = updatedt;
    }

    @Basic
    @Column(name = "workcontent")
    public String getWorkcontent() {
        return workcontent;
    }

    public void setWorkcontent(String workcontent) {
        this.workcontent = workcontent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceDetailListHistoryEntity that = (PriceDetailListHistoryEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(bagclearprice, that.bagclearprice) &&
                Objects.equals(buildingprojectid, that.buildingprojectid) &&
                Objects.equals(content, that.content) &&
                Objects.equals(countruler, that.countruler) &&
                Objects.equals(feature, that.feature) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(flagaddition, that.flagaddition) &&
                Objects.equals(inputerdeptid, that.inputerdeptid) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(inputerdeptpathid, that.inputerdeptpathid) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(machinecost, that.machinecost) &&
                Objects.equals(materialcost, that.materialcost) &&
                Objects.equals(materialprice, that.materialprice) &&
                Objects.equals(name, that.name) &&
                Objects.equals(originflag, that.originflag) &&
                Objects.equals(parentid, that.parentid) &&
                Objects.equals(personcost, that.personcost) &&
                Objects.equals(price, that.price) &&
                Objects.equals(pricefrom, that.pricefrom) &&
                Objects.equals(projectamount, that.projectamount) &&
                Objects.equals(projectid, that.projectid) &&
                Objects.equals(projectname, that.projectname) &&
                Objects.equals(projectnameapart, that.projectnameapart) &&
                Objects.equals(projectnameapartid, that.projectnameapartid) &&
                Objects.equals(relatematerialamount, that.relatematerialamount) &&
                Objects.equals(relatematerialdetailamount, that.relatematerialdetailamount) &&
                Objects.equals(relatematerialdetailid, that.relatematerialdetailid) &&
                Objects.equals(relatematerialdetailname, that.relatematerialdetailname) &&
                Objects.equals(relatematerialid, that.relatematerialid) &&
                Objects.equals(relatematerialname, that.relatematerialname) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(rootprojectid, that.rootprojectid) &&
                Objects.equals(sortid, that.sortid) &&
                Objects.equals(sortids, that.sortids) &&
                Objects.equals(state, that.state) &&
                Objects.equals(sysid, that.sysid) &&
                Objects.equals(taxrate, that.taxrate) &&
                Objects.equals(total, that.total) &&
                Objects.equals(type, that.type) &&
                Objects.equals(unit, that.unit) &&
                Objects.equals(updatebyuserfullname, that.updatebyuserfullname) &&
                Objects.equals(updatebyusername, that.updatebyusername) &&
                Objects.equals(updatedt, that.updatedt) &&
                Objects.equals(workcontent, that.workcontent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, bagclearprice, buildingprojectid, content, countruler, feature, flag, flagaddition, inputerdeptid, inputerdeptname, inputerdeptpathid, inputerfullname, inputername, machinecost, materialcost, materialprice, name, originflag, parentid, personcost, price, pricefrom, projectamount, projectid, projectname, projectnameapart, projectnameapartid, relatematerialamount, relatematerialdetailamount, relatematerialdetailid, relatematerialdetailname, relatematerialid, relatematerialname, remark, rootprojectid, sortid, sortids, state, sysid, taxrate, total, type, unit, updatebyuserfullname, updatebyusername, updatedt, workcontent);
    }

    public PriceDetailListHistoryEntity(String guid, BigDecimal bagclearprice, String buildingprojectid, String content, String countruler, String feature, String flag, Integer inputerdeptid, String inputerdeptname, Integer inputerdeptpathid, String inputerfullname, String inputername, BigDecimal materialprice, String name, String parentid, BigDecimal price, String pricefrom, Double projectamount, String projectid, String projectname, String projectnameapart, String projectnameapartid, String relatematerialamount, String relatematerialdetailamount, String relatematerialdetailid, String relatematerialdetailname, String relatematerialid, String relatematerialname, String remark, String state, Long sysid, BigDecimal total, String type, String unit, String updatebyuserfullname, String updatebyusername, Timestamp updatedt, String workcontent, String taxrate, Integer sortid, Integer flagaddition, String originflag, Integer sortids, String rootprojectid, BigDecimal personcost, BigDecimal materialcost, BigDecimal machinecost) {
        this.guid = guid;
        this.bagclearprice = bagclearprice;
        this.buildingprojectid = buildingprojectid;
        this.content = content;
        this.countruler = countruler;
        this.feature = feature;
        this.flag = flag;
        this.inputerdeptid = inputerdeptid;
        this.inputerdeptname = inputerdeptname;
        this.inputerdeptpathid = inputerdeptpathid;
        this.inputerfullname = inputerfullname;
        this.inputername = inputername;
        this.materialprice = materialprice;
        this.name = name;
        this.parentid = parentid;
        this.price = price;
        this.pricefrom = pricefrom;
        this.projectamount = projectamount;
        this.projectid = projectid;
        this.projectname = projectname;
        this.projectnameapart = projectnameapart;
        this.projectnameapartid = projectnameapartid;
        this.relatematerialamount = relatematerialamount;
        this.relatematerialdetailamount = relatematerialdetailamount;
        this.relatematerialdetailid = relatematerialdetailid;
        this.relatematerialdetailname = relatematerialdetailname;
        this.relatematerialid = relatematerialid;
        this.relatematerialname = relatematerialname;
        this.remark = remark;
        this.state = state;
        this.sysid = sysid;
        this.total = total;
        this.type = type;
        this.unit = unit;
        this.updatebyuserfullname = updatebyuserfullname;
        this.updatebyusername = updatebyusername;
        this.updatedt = updatedt;
        this.workcontent = workcontent;
        this.taxrate = taxrate;
        this.sortid = sortid;
        this.flagaddition = flagaddition;
        this.originflag = originflag;
        this.sortids = sortids;
        this.rootprojectid = rootprojectid;
        this.personcost = personcost;
        this.materialcost = materialcost;
        this.machinecost = machinecost;
    }

    public PriceDetailListHistoryEntity() {
    }
}
