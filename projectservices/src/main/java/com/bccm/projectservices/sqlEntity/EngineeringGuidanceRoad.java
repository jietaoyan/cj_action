package com.bccm.projectservices.sqlEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ENGINEERINGGUIDANCE_ROAD", schema = "dbo", catalog = "INTEGRATEDMANAGE")
public class EngineeringGuidanceRoad {
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
    private BigDecimal totalIndicator;
    private BigDecimal subgrade;
    private BigDecimal road;
    private BigDecimal trafficFacilities;
    private String usingnature;
    private String designlevel;
    private String roadsurfacetype;
    private String roadbedwidth;
    private String lengthroad;
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
    @Column(name = "DESCRIPTION", nullable = true, length = 500)
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
    @Column(name = "DESIGNPHASE", nullable = true, length = 50)
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
    @Column(name = "TOTALINDICATOR", nullable = true, precision = 4)
    public BigDecimal getTotalIndicator() {
        return totalIndicator;
    }

    public void setTotalIndicator(BigDecimal totalIndicator) {
        this.totalIndicator = totalIndicator;
    }

    @Basic
    @Column(name = "SUBGRADE", nullable = true, precision = 4)
    public BigDecimal getSubgrade() {
        return subgrade;
    }

    public void setSubgrade(BigDecimal subgrade) {
        this.subgrade = subgrade;
    }

    @Basic
    @Column(name = "ROAD", nullable = true, precision = 4)
    public BigDecimal getRoad() {
        return road;
    }

    public void setRoad(BigDecimal road) {
        this.road = road;
    }

    @Basic
    @Column(name = "TRAFFICFACILITIES", nullable = true, precision = 4)
    public BigDecimal getTrafficFacilities() {
        return trafficFacilities;
    }

    public void setTrafficFacilities(BigDecimal trafficFacilities) {
        this.trafficFacilities = trafficFacilities;
    }

    @Basic
    @Column(name = "USINGNATURE", nullable = true, length = 500)
    public String getUsingnature() {
        return usingnature;
    }

    public void setUsingnature(String usingnature) {
        this.usingnature = usingnature;
    }

    @Basic
    @Column(name = "DESIGNLEVEL", nullable = true, length = 50)
    public String getDesignlevel() {
        return designlevel;
    }

    public void setDesignlevel(String designlevel) {
        this.designlevel = designlevel;
    }

    @Basic
    @Column(name = "ROADSURFACETYPE", nullable = true, length = 50)
    public String getRoadsurfacetype() {
        return roadsurfacetype;
    }

    public void setRoadsurfacetype(String roadsurfacetype) {
        this.roadsurfacetype = roadsurfacetype;
    }

    @Basic
    @Column(name = "ROADBEDWIDTH", nullable = true, length = 50)
    public String getRoadbedwidth() {
        return roadbedwidth;
    }

    public void setRoadbedwidth(String roadbedwidth) {
        this.roadbedwidth = roadbedwidth;
    }

    @Basic
    @Column(name = "LENGTHROAD", nullable = true, length = 50)
    public String getLengthroad() {
        return lengthroad;
    }

    public void setLengthroad(String lengthroad) {
        this.lengthroad = lengthroad;
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
        EngineeringGuidanceRoad that = (EngineeringGuidanceRoad) o;
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
                Objects.equals(totalIndicator, that.totalIndicator) &&
                Objects.equals(subgrade, that.subgrade) &&
                Objects.equals(road, that.road) &&
                Objects.equals(trafficFacilities, that.trafficFacilities) &&
                Objects.equals(usingnature, that.usingnature) &&
                Objects.equals(designlevel, that.designlevel) &&
                Objects.equals(roadsurfacetype, that.roadsurfacetype) &&
                Objects.equals(roadbedwidth, that.roadbedwidth) &&
                Objects.equals(lengthroad, that.lengthroad);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, sysid, subject, inputerDeptId, inputerDeptName, inputerDeptPathId, phoneNum, inputerName, inputerFullName, inputDate, updateDate, notifyCheck, notifyMethod, description, flag, state, projectName, subprojectName, constructionSite, designPhase, pricelevelyear, totalIndicator, subgrade, road, trafficFacilities, usingnature, designlevel, roadsurfacetype, roadbedwidth, lengthroad);
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
