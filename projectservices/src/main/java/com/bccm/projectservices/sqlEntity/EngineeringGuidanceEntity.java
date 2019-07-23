package com.bccm.projectservices.sqlEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "engineeringguidance", schema = "dbo")
public class EngineeringGuidanceEntity {
    private String guid;
    private long sysid;
    private String subject;
    private String inputerdeptId;
    private String inputerdeptname;
    private String inputerdeptpathId;
    private String phonenum;
    private String inputername;
    private String inputerfullname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp inputdate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedate;
    private String notifycheck;
    private String notifymethod;
    private String description1;
    private String flag;
    private String state;
    private String professional;
    private String datasource;
    private String projectsite;
    private String subcontractingmode;
    private String namesubcontract;
    private String nameproject;
    private String namesubcontractproject;
    private String measuringunit;
    private String subcontractquantity;
    private String priceinRmb;
    private String thesubcontractprice;
    private String rate;
    private String thesubcontractprice1;
    private String subcontractconstructioncontent;
    private String thepriceincludescostcontent;
    private String basicprice;
    private String theamountresourcesinvestedproject;
    private String descriptionconstructionenvironment;
    private String mterialname;
    private String materialconsumption;
    private String measurementrules;
    private String formno;
    private String datatime;
    private String ProjectCity;
    private String type;
    private String auditUserName;
    private String auditUserFullName;
    private String auditUserName2;
    private String auditUserFullName2;

    @Basic
    @Column(name = "PROJECTCITY", nullable = false)
    public String getProjectCity() {
        return this.ProjectCity;
    }

    public void setProjectCity(String projectCity) {
        this.ProjectCity = projectCity;
    }

    @Basic
    @Column(name = "TYPE", nullable = false)
    public String getTYPE() {
        return type;
    }

    public void setTYPE(String type) {
        this.type = type;
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
    @Column(name = "SYSID", nullable = false)
    public long getSysid() {
        return sysid;
    }

    public void setSysid(long sysid) {
        this.sysid = sysid;
    }

    @Basic
    @Column(name = "SUBJECT", nullable = true, length = 100)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "INPUTERDEPTID", nullable = true, length = 50)
    public String getInputerDeptId() {
        return inputerdeptId;
    }

    public void setInputerDeptId(String inputerdeptId) {
        this.inputerdeptId = inputerdeptId;
    }

    @Basic
    @Column(name = "INPUTERDEPTNAME", nullable = true, length = 100)
    public String getInputerDeptName() {
        return inputerdeptname;
    }

    public void setInputerDeptName(String inputerdeptname) {
        this.inputerdeptname = inputerdeptname;
    }

    @Basic
    @Column(name = "INPUTERDEPTPATHID", nullable = true, length = 50)
    public String getInputerDeptPathId() {
        return inputerdeptpathId;
    }

    public void setInputerDeptPathId(String inputerdeptpathId) {
        this.inputerdeptpathId = inputerdeptpathId;
    }

    @Basic
    @Column(name = "PHONENUM", nullable = true, length = 50)
    public String getPhoneNum() {
        return phonenum;
    }

    public void setPhoneNum(String phonenum) {
        this.phonenum = phonenum;
    }

    @Basic
    @Column(name = "INPUTERNAME", nullable = true, length = 50)
    public String getInputerName() {
        return inputername;
    }

    public void setInputerName(String inputername) {
        this.inputername = inputername;
    }

    @Basic
    @Column(name = "INPUTERFULLNAME", nullable = true, length = 50)
    public String getInputerFullName() {
        return inputerfullname;
    }

    public void setInputerFullName(String inputerfullname) {
        this.inputerfullname = inputerfullname;
    }

    @Basic
    @Column(name = "INPUTDATE", nullable = true)
    public Timestamp getInputDate() {
        return inputdate;
    }

    public void setInputDate(Timestamp inputdate) {
        this.inputdate = inputdate;
    }

    @Basic
    @Column(name = "UPDATEDATE", nullable = true)
    public Timestamp getUpdateDate() {
        return updatedate;
    }

    public void setUpdateDate(Timestamp updatedate) {
        this.updatedate = updatedate;
    }

    @Basic
    @Column(name = "NOTIFYCHECK", nullable = true, length = 50)
    public String getNotifyCheck() {
        return notifycheck;
    }

    public void setNotifyCheck(String notifycheck) {
        this.notifycheck = notifycheck;
    }

    @Basic
    @Column(name = "NOTIFYMETHOD", nullable = true, length = 50)
    public String getNotifyMethod() {
        return notifymethod;
    }

    public void setNotifyMethod(String notifymethod) {
        this.notifymethod = notifymethod;
    }

    @Basic
    @Column(name = "DESCRIPTION1", nullable = true, length = 200)
    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
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
    @Column(name = "STATE", nullable = true, length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PROFESSIONAL", nullable = true, length = 100)
    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    @Basic
    @Column(name = "DATASOURCE", nullable = true, length = 100)
    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    @Basic
    @Column(name = "PROJECTSITE", nullable = true, length = 50)
    public String getProjectsite() {
        return projectsite;
    }

    public void setProjectsite(String projectsite) {
        this.projectsite = projectsite;
    }

    @Basic
    @Column(name = "SUBCONTRACTINGMODE", nullable = true, length = 50)
    public String getSubcontractingmode() {
        return subcontractingmode;
    }

    public void setSubcontractingmode(String subcontractingmode) {
        this.subcontractingmode = subcontractingmode;
    }

    @Basic
    @Column(name = "NAMESUBCONTRACT", nullable = true, length = 50)
    public String getNamesubcontract() {
        return namesubcontract;
    }

    public void setNamesubcontract(String namesubcontract) {
        this.namesubcontract = namesubcontract;
    }

    @Basic
    @Column(name = "NAMEPROJECT", nullable = true, length = 50)
    public String getNameproject() {
        return nameproject;
    }

    public void setNameproject(String nameproject) {
        this.nameproject = nameproject;
    }

    @Basic
    @Column(name = "NAMESUBCONTRACTPROJECT", nullable = true, length = 50)
    public String getNamesubcontractproject() {
        return namesubcontractproject;
    }

    public void setNamesubcontractproject(String namesubcontractproject) {
        this.namesubcontractproject = namesubcontractproject;
    }

    @Basic
    @Column(name = "MEASURINGUNIT", nullable = true, length = 50)
    public String getMeasuringunit() {
        return measuringunit;
    }

    public void setMeasuringunit(String measuringunit) {
        this.measuringunit = measuringunit;
    }

    @Basic
    @Column(name = "SUBCONTRACTQUANTITY", nullable = true, length = 50)
    public String getSubcontractquantity() {
        return subcontractquantity;
    }

    public void setSubcontractquantity(String subcontractquantity) {
        this.subcontractquantity = subcontractquantity;
    }

    @Basic
    @Column(name = "PRICEINRMB", nullable = true, length = 50)
    public String getPriceinRmb() {
        return priceinRmb;
    }

    public void setPriceinRmb(String priceinRmb) {
        this.priceinRmb = priceinRmb;
    }

    @Basic
    @Column(name = "THESUBCONTRACTPRICE", nullable = true, length = 50)
    public String getThesubcontractprice() {
        return thesubcontractprice;
    }

    public void setThesubcontractprice(String thesubcontractprice) {
        this.thesubcontractprice = thesubcontractprice;
    }

    @Basic
    @Column(name = "RATE", nullable = true, length = 50)
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "THESUBCONTRACTPRICE1", nullable = true, length = 50)
    public String getThesubcontractprice1() {
        return thesubcontractprice1;
    }

    public void setThesubcontractprice1(String thesubcontractprice1) {
        this.thesubcontractprice1 = thesubcontractprice1;
    }

    @Basic
    @Column(name = "SUBCONTRACTCONSTRUCTIONCONTENT", nullable = true, length = 500)
    public String getSubcontractconstructioncontent() {
        return subcontractconstructioncontent;
    }

    public void setSubcontractconstructioncontent(String subcontractconstructioncontent) {
        this.subcontractconstructioncontent = subcontractconstructioncontent;
    }

    @Basic
    @Column(name = "THEPRICEINCLUDESCOSTCONTENT", nullable = true, length = 500)
    public String getThepriceincludescostcontent() {
        return thepriceincludescostcontent;
    }

    public void setThepriceincludescostcontent(String thepriceincludescostcontent) {
        this.thepriceincludescostcontent = thepriceincludescostcontent;
    }

    @Basic
    @Column(name = "BASICPRICE", nullable = true, length = 500)
    public String getBasicprice() {
        return basicprice;
    }

    public void setBasicprice(String basicprice) {
        this.basicprice = basicprice;
    }

    @Basic
    @Column(name = "THEAMOUNTRESOURCESINVESTEDPROJECT", nullable = true, length = 500)
    public String getTheamountresourcesinvestedproject() {
        return theamountresourcesinvestedproject;
    }

    public void setTheamountresourcesinvestedproject(String theamountresourcesinvestedproject) {
        this.theamountresourcesinvestedproject = theamountresourcesinvestedproject;
    }

    @Basic
    @Column(name = "DESCRIPTIONCONSTRUCTIONENVIRONMENT", nullable = true, length = 500)
    public String getDescriptionconstructionenvironment() {
        return descriptionconstructionenvironment;
    }

    public void setDescriptionconstructionenvironment(String descriptionconstructionenvironment) {
        this.descriptionconstructionenvironment = descriptionconstructionenvironment;
    }

    @Basic
    @Column(name = "MTERIALNAME", nullable = true, length = 500)
    public String getMterialname() {
        return mterialname;
    }

    public void setMterialname(String mterialname) {
        this.mterialname = mterialname;
    }

    @Basic
    @Column(name = "MATERIALCONSUMPTION", nullable = true, length = 500)
    public String getMaterialconsumption() {
        return materialconsumption;
    }

    public void setMaterialconsumption(String materialconsumption) {
        this.materialconsumption = materialconsumption;
    }

    @Basic
    @Column(name = "MEASUREMENTRULES", nullable = true, length = 500)
    public String getMeasurementrules() {
        return measurementrules;
    }

    public void setMeasurementrules(String measurementrules) {
        this.measurementrules = measurementrules;
    }

    @Basic
    @Column(name = "FORMNO", nullable = true, length = 50)
    public String getFormNo() {
        return formno;
    }

    public void setFormNo(String formno) {
        this.formno = formno;
    }

    @Basic
    @Column(name = "DATATIME", nullable = true)
    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    @Basic
    @Column(name = "AUDITUSERNAME", nullable = true, length = 50)
    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    @Basic
    @Column(name = "AUDITUSERFULLNAME", nullable = true, length = 50)
    public String getAuditUserFullName() {
        return auditUserFullName;
    }

    public void setAuditUserFullName(String auditUserFullName) {
        this.auditUserFullName = auditUserFullName;
    }

    @Basic
    @Column(name = "AUDITUSERNAME2", nullable = true, length = 50)
    public String getAuditUserName2() {
        return auditUserName2;
    }

    public void setAuditUserName2(String auditUserName2) {
        this.auditUserName2 = auditUserName2;
    }

    @Basic
    @Column(name = "AUDITUSERFULLNAME2", nullable = true, length = 50)
    public String getAuditUserFullName2() {
        return auditUserFullName2;
    }

    public void setAuditUserFullName2(String auditUserFullName2) {
        this.auditUserFullName2 = auditUserFullName2;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineeringGuidanceEntity that = (EngineeringGuidanceEntity) o;
        return sysid == that.sysid &&
                Objects.equals(guid, that.guid) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(inputerdeptId, that.inputerdeptId) &&
                Objects.equals(inputerdeptname, that.inputerdeptname) &&
                Objects.equals(inputerdeptpathId, that.inputerdeptpathId) &&
                Objects.equals(phonenum, that.phonenum) &&
                Objects.equals(inputername, that.inputername) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(inputdate, that.inputdate) &&
                Objects.equals(updatedate, that.updatedate) &&
                Objects.equals(notifycheck, that.notifycheck) &&
                Objects.equals(notifymethod, that.notifymethod) &&
                Objects.equals(description1, that.description1) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(state, that.state) &&
                Objects.equals(professional, that.professional) &&
                Objects.equals(datasource, that.datasource) &&
                Objects.equals(projectsite, that.projectsite) &&
                Objects.equals(subcontractingmode, that.subcontractingmode) &&
                Objects.equals(namesubcontract, that.namesubcontract) &&
                Objects.equals(nameproject, that.nameproject) &&
                Objects.equals(namesubcontractproject, that.namesubcontractproject) &&
                Objects.equals(measuringunit, that.measuringunit) &&
                Objects.equals(subcontractquantity, that.subcontractquantity) &&
                Objects.equals(priceinRmb, that.priceinRmb) &&
                Objects.equals(thesubcontractprice, that.thesubcontractprice) &&
                Objects.equals(rate, that.rate) &&
                Objects.equals(thesubcontractprice1, that.thesubcontractprice1) &&
                Objects.equals(subcontractconstructioncontent, that.subcontractconstructioncontent) &&
                Objects.equals(thepriceincludescostcontent, that.thepriceincludescostcontent) &&
                Objects.equals(basicprice, that.basicprice) &&
                Objects.equals(theamountresourcesinvestedproject, that.theamountresourcesinvestedproject) &&
                Objects.equals(descriptionconstructionenvironment, that.descriptionconstructionenvironment) &&
                Objects.equals(mterialname, that.mterialname) &&
                Objects.equals(materialconsumption, that.materialconsumption) &&
                Objects.equals(measurementrules, that.measurementrules) &&
                Objects.equals(formno, that.formno) &&
                Objects.equals(datatime, that.datatime)&&
                Objects.equals(ProjectCity, that.ProjectCity)&&
                Objects.equals(type, that.type)&&
                Objects.equals(auditUserName, that.auditUserName) &&
                Objects.equals(auditUserFullName, that.auditUserFullName) &&
                Objects.equals(auditUserName2, that.auditUserName2) &&
                Objects.equals(auditUserFullName2, that.auditUserFullName2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, sysid, subject, inputerdeptId, inputerdeptname, inputerdeptpathId
                , phonenum, inputername, inputerfullname, inputdate, updatedate, notifycheck, notifymethod
                , description1, flag, state, professional, datasource, projectsite, subcontractingmode
                , namesubcontract, nameproject, namesubcontractproject, measuringunit, subcontractquantity
                , priceinRmb, thesubcontractprice, rate, thesubcontractprice1, subcontractconstructioncontent
                , thepriceincludescostcontent, basicprice, theamountresourcesinvestedproject
                , descriptionconstructionenvironment, mterialname, materialconsumption, measurementrules, formno
                , datatime, ProjectCity, type, auditUserName, auditUserFullName, auditUserName2, auditUserFullName2);
    }
}
