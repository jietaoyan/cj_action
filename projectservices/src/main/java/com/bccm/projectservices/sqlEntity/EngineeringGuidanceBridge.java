package com.bccm.projectservices.sqlEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ENGINEERINGGUIDANCE_BRIDGE", schema = "dbo", catalog = "INTEGRATEDMANAGE")
public class EngineeringGuidanceBridge {
    private String guid;
    private long sysid;
    private String subject;
    private String inputerDeptId;
    private String inputerDeptName;
    private String inputerDeptPathId;
    private String phoneNum;
    private String inputerName;
    private String inputerFullName;
    private Timestamp inputDate;
    private Timestamp updateDate;
    private String notifyCheck;
    private String notifyMethod;
    private String description;
    private String flag;
    private String state;
    private String projectName;
    private String subprojectName;
    private String constructionSite;
    private String designPhase;
    private String pricelevelyear;
    private String longbridge;
    private String wide;
    private String costindicators;
    private String riverriverseawidth;
    private String bridge;
    private String basiclocation;
    private String pierheight;
    private String span;
    private String lengthroad;
    private String bridge2;
    private String basiclocation2;
    private String highpier;
    private String span2;
    private String datatime;
    private String projectCity;
    private String AuditUserName;
    private String AuditUserFullName;
    private String AuditUserName2;
    private String AuditUserFullName2;

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
        return inputerDeptId;
    }

    public void setInputerDeptId(String inputerDeptId) {
        this.inputerDeptId = inputerDeptId;
    }

    @Basic
    @Column(name = "INPUTERDEPTNAME", nullable = true, length = 100)
    public String getInputerDeptName() {
        return inputerDeptName;
    }

    public void setInputerDeptName(String inputerDeptName) {
        this.inputerDeptName = inputerDeptName;
    }

    @Basic
    @Column(name = "INPUTERDEPTPATHID", nullable = true, length = 50)
    public String getInputerDeptPathId() {
        return inputerDeptPathId;
    }

    public void setInputerDeptPathId(String inputerDeptPathId) {
        this.inputerDeptPathId = inputerDeptPathId;
    }

    @Basic
    @Column(name = "PHONENUM", nullable = true, length = 50)
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Basic
    @Column(name = "INPUTERNAME", nullable = true, length = 50)
    public String getInputerName() {
        return inputerName;
    }

    public void setInputerName(String inputerName) {
        this.inputerName = inputerName;
    }

    @Basic
    @Column(name = "INPUTERFULLNAME", nullable = true, length = 50)
    public String getInputerFullName() {
        return inputerFullName;
    }

    public void setInputerFullName(String inputerFullName) {
        this.inputerFullName = inputerFullName;
    }

    @Basic
    @Column(name = "INPUTDATE", nullable = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    public Timestamp getInputDate() {
        return inputDate;
    }

    public void setInputDate(Timestamp inputDate) {
        this.inputDate = inputDate;
    }

    @Basic
    @Column(name = "UPDATEDATE", nullable = true)
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "NOTIFYCHECK", nullable = true, length = 50)
    public String getNotifyCheck() {
        return notifyCheck;
    }

    public void setNotifyCheck(String notifyCheck) {
        this.notifyCheck = notifyCheck;
    }

    @Basic
    @Column(name = "NOTIFYMETHOD", nullable = true, length = 50)
    public String getNotifyMethod() {
        return notifyMethod;
    }

    public void setNotifyMethod(String notifyMethod) {
        this.notifyMethod = notifyMethod;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    @Column(name = "PROJECTNAME", nullable = true, length = 500)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "SUBPROJECTNAME", nullable = true, length = 500)
    public String getSubprojectName() {
        return subprojectName;
    }

    public void setSubprojectName(String subprojectName) {
        this.subprojectName = subprojectName;
    }

    @Basic
    @Column(name = "CONSTRUCTIONSITE", nullable = true, length = 500)
    public String getConstructionSite() {
        return constructionSite;
    }

    public void setConstructionSite(String constructionSite) {
        this.constructionSite = constructionSite;
    }

    @Basic
    @Column(name = "DESIGNPHASE", nullable = true, length = 500)
    public String getDesignPhase() {
        return designPhase;
    }

    public void setDesignPhase(String designPhase) {
        this.designPhase = designPhase;
    }

    @Basic
    @Column(name = "PRICELEVELYEAR", nullable = true)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy")
    public String getPricelevelyear() {
        return pricelevelyear;
    }

    public void setPricelevelyear(String pricelevelyear) {
        this.pricelevelyear = pricelevelyear;
    }

    @Basic
    @Column(name = "LONGBRIDGE", nullable = true, length = 500)
    public String getLongbridge() {
        return longbridge;
    }

    public void setLongbridge(String longbridge) {
        this.longbridge = longbridge;
    }

    @Basic
    @Column(name = "WIDE", nullable = true, length = 500)
    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    @Basic
    @Column(name = "COSTINDICATORS", nullable = true, length = 500)
    public String getCostindicators() {
        return costindicators;
    }

    public void setCostindicators(String costindicators) {
        this.costindicators = costindicators;
    }

    @Basic
    @Column(name = "RIVERRIVERSEAWIDTH", nullable = true, length = 500)
    public String getRiverriverseawidth() {
        return riverriverseawidth;
    }

    public void setRiverriverseawidth(String riverriverseawidth) {
        this.riverriverseawidth = riverriverseawidth;
    }

    @Basic
    @Column(name = "BRIDGE", nullable = true, length = 500)
    public String getBridge() {
        return bridge;
    }

    public void setBridge(String bridge) {
        this.bridge = bridge;
    }

    @Basic
    @Column(name = "BASICLOCATION", nullable = true, length = 500)
    public String getBasiclocation() {
        return basiclocation;
    }

    public void setBasiclocation(String basiclocation) {
        this.basiclocation = basiclocation;
    }

    @Basic
    @Column(name = "PIERHEIGHT", nullable = true, length = 500)
    public String getPierheight() {
        return pierheight;
    }

    public void setPierheight(String pierheight) {
        this.pierheight = pierheight;
    }

    @Basic
    @Column(name = "SPAN", nullable = true, length = 500)
    public String getSpan() {
        return span;
    }

    public void setSpan(String span) {
        this.span = span;
    }

    @Basic
    @Column(name = "LENGTHROAD", nullable = true, length = 500)
    public String getLengthroad() {
        return lengthroad;
    }

    public void setLengthroad(String lengthroad) {
        this.lengthroad = lengthroad;
    }

    @Basic
    @Column(name = "BRIDGE2", nullable = true, length = 50)
    public String getBridge2() {
        return bridge2;
    }

    public void setBridge2(String bridge2) {
        this.bridge2 = bridge2;
    }

    @Basic
    @Column(name = "BASICLOCATION2", nullable = true, length = 50)
    public String getBasiclocation2() {
        return basiclocation2;
    }

    public void setBasiclocation2(String basiclocation2) {
        this.basiclocation2 = basiclocation2;
    }

    @Basic
    @Column(name = "HIGHPIER", nullable = true, length = 50)
    public String getHighpier() {
        return highpier;
    }

    public void setHighpier(String highpier) {
        this.highpier = highpier;
    }

    @Basic
    @Column(name = "SPAN2", nullable = true, length = 50)
    public String getSpan2() {
        return span2;
    }

    public void setSpan2(String span2) {
        this.span2 = span2;
    }
    @Basic
    @Column(name = "AUDITUSERNAME", nullable = true, length = 50)
    public String getAuditusername() {
        return AuditUserName;
    }

    public void setAuditusername(String AuditUserName) {
        this.AuditUserName = AuditUserName;
    }
    @Basic
    @Column(name = "AUDITUSERFULLNAME", nullable = true, length = 50)
    public String getAudituserfullname() {
        return AuditUserFullName;
    }

    public void setAudituserfullname(String AuditUserFullName) {
        this.AuditUserFullName = AuditUserFullName;
    }
    @Basic
    @Column(name = "AUDITUSERNAME2", nullable = true, length = 50)
    public String getAuditusername2() {
        return AuditUserName2;
    }

    public void setAuditusername2(String AuditUserName2) {
        this.AuditUserName2 = AuditUserName2;
    }
    @Basic
    @Column(name = "AUDITUSERFULLNAME2", nullable = true, length = 50)
    public String getAudituserfullname2() {
        return AuditUserFullName2;
    }

    public void setAudituserfullname2(String AuditUserFullName2) {
        this.AuditUserFullName2 = AuditUserFullName2;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EngineeringGuidanceBridge that = (EngineeringGuidanceBridge) o;
        return sysid == that.sysid &&
                Objects.equals(guid, that.guid) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(inputerDeptId, that.inputerDeptId) &&
                Objects.equals(inputerDeptName, that.inputerDeptName) &&
                Objects.equals(inputerDeptPathId, that.inputerDeptPathId) &&
                Objects.equals(phoneNum, that.phoneNum) &&
                Objects.equals(inputerName, that.inputerName) &&
                Objects.equals(inputerFullName, that.inputerFullName) &&
                Objects.equals(inputDate, that.inputDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(notifyCheck, that.notifyCheck) &&
                Objects.equals(notifyMethod, that.notifyMethod) &&
                Objects.equals(description, that.description) &&
                Objects.equals(flag, that.flag) &&
                Objects.equals(state, that.state) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(subprojectName, that.subprojectName) &&
                Objects.equals(constructionSite, that.constructionSite) &&
                Objects.equals(designPhase, that.designPhase) &&
                Objects.equals(pricelevelyear, that.pricelevelyear) &&
                Objects.equals(longbridge, that.longbridge) &&
                Objects.equals(wide, that.wide) &&
                Objects.equals(costindicators, that.costindicators) &&
                Objects.equals(riverriverseawidth, that.riverriverseawidth) &&
                Objects.equals(bridge, that.bridge) &&
                Objects.equals(basiclocation, that.basiclocation) &&
                Objects.equals(pierheight, that.pierheight) &&
                Objects.equals(span, that.span) &&
                Objects.equals(lengthroad, that.lengthroad) &&
                Objects.equals(bridge2, that.bridge2) &&
                Objects.equals(basiclocation2, that.basiclocation2) &&
                Objects.equals(highpier, that.highpier) &&
                Objects.equals(span2, that.span2);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, sysid, subject, inputerDeptId, inputerDeptName, inputerDeptPathId, phoneNum, inputerName, inputerFullName, inputDate, updateDate, notifyCheck, notifyMethod, description, flag, state, projectName, subprojectName, constructionSite, designPhase, pricelevelyear, longbridge, wide, costindicators, riverriverseawidth, bridge, basiclocation, pierheight, span, lengthroad, bridge2, basiclocation2, highpier, span2);
    }

    @Basic
    @Column(name = "DATATIME", nullable = true, length = 50)
    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    @Basic
    @Column(name = "PROJECTCITY", nullable = true, length = 50)
    public String getProjectCity() {
        return projectCity;
    }

    public void setProjectCity(String projectCity) {
        this.projectCity = projectCity;
    }
}
