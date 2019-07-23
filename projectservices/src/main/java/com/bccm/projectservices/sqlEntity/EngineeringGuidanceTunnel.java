package com.bccm.projectservices.sqlEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ENGINEERINGGUIDANCE_TUNNEL", schema = "dbo", catalog = "INTEGRATEDMANAGE")
public class EngineeringGuidanceTunnel {
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
    private String length;
    private String holedoor;
    private String clearance;
    private String costindexcivilengineering;
    private String minglonghole;
    private String concealedexcavationsection;
    private String proportionsurroundingrockgrade1;
    private String proportionsurroundingrockgrade2;
    private String proportionsurroundingrockgrade3;
    private String proportionsurroundingrockgrade4;
    private String proportionsurroundingrockgrade5;
    private String proportionsurroundingrockgrade6;
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
    @Column(name = "LENGTH", nullable = true, length = 50)
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    @Basic
    @Column(name = "HOLEDOOR", nullable = true, length = 50)
    public String getHoledoor() {
        return holedoor;
    }

    public void setHoledoor(String holedoor) {
        this.holedoor = holedoor;
    }

    @Basic
    @Column(name = "CLEARANCE", nullable = true, length = 50)
    public String getClearance() {
        return clearance;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }

    @Basic
    @Column(name = "COSTINDEXCIVILENGINEERING", nullable = true, length = 50)
    public String getCostindexcivilengineering() {
        return costindexcivilengineering;
    }

    public void setCostindexcivilengineering(String costindexcivilengineering) {
        this.costindexcivilengineering = costindexcivilengineering;
    }

    @Basic
    @Column(name = "MINGLONGHOLE", nullable = true, length = 50)
    public String getMinglonghole() {
        return minglonghole;
    }

    public void setMinglonghole(String minglonghole) {
        this.minglonghole = minglonghole;
    }

    @Basic
    @Column(name = "CONCEALEDEXCAVATIONSECTION", nullable = true, length = 50)
    public String getConcealedexcavationsection() {
        return concealedexcavationsection;
    }

    public void setConcealedexcavationsection(String concealedexcavationsection) {
        this.concealedexcavationsection = concealedexcavationsection;
    }

    @Basic
    @Column(name = "PROPORTIONSURROUNDINGROCKGRADE1", nullable = true, length = 50)
    public String getProportionsurroundingrockgrade1() {
        return proportionsurroundingrockgrade1;
    }

    public void setProportionsurroundingrockgrade1(String proportionsurroundingrockgrade1) {
        this.proportionsurroundingrockgrade1 = proportionsurroundingrockgrade1;
    }

    @Basic
    @Column(name = "PROPORTIONSURROUNDINGROCKGRADE2", nullable = true, length = 50)
    public String getProportionsurroundingrockgrade2() {
        return proportionsurroundingrockgrade2;
    }

    public void setProportionsurroundingrockgrade2(String proportionsurroundingrockgrade2) {
        this.proportionsurroundingrockgrade2 = proportionsurroundingrockgrade2;
    }

    @Basic
    @Column(name = "PROPORTIONSURROUNDINGROCKGRADE3", nullable = true, length = 50)
    public String getProportionsurroundingrockgrade3() {
        return proportionsurroundingrockgrade3;
    }

    public void setProportionsurroundingrockgrade3(String proportionsurroundingrockgrade3) {
        this.proportionsurroundingrockgrade3 = proportionsurroundingrockgrade3;
    }

    @Basic
    @Column(name = "PROPORTIONSURROUNDINGROCKGRADE4", nullable = true, length = 50)
    public String getProportionsurroundingrockgrade4() {
        return proportionsurroundingrockgrade4;
    }

    public void setProportionsurroundingrockgrade4(String proportionsurroundingrockgrade4) {
        this.proportionsurroundingrockgrade4 = proportionsurroundingrockgrade4;
    }

    @Basic
    @Column(name = "PROPORTIONSURROUNDINGROCKGRADE5", nullable = true, length = 50)
    public String getProportionsurroundingrockgrade5() {
        return proportionsurroundingrockgrade5;
    }

    public void setProportionsurroundingrockgrade5(String proportionsurroundingrockgrade5) {
        this.proportionsurroundingrockgrade5 = proportionsurroundingrockgrade5;
    }

    @Basic
    @Column(name = "PROPORTIONSURROUNDINGROCKGRADE6", nullable = true, length = 50)
    public String getProportionsurroundingrockgrade6() {
        return proportionsurroundingrockgrade6;
    }

    public void setProportionsurroundingrockgrade6(String proportionsurroundingrockgrade6) {
        this.proportionsurroundingrockgrade6 = proportionsurroundingrockgrade6;
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
        EngineeringGuidanceTunnel that = (EngineeringGuidanceTunnel) o;
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
                Objects.equals(length, that.length) &&
                Objects.equals(holedoor, that.holedoor) &&
                Objects.equals(clearance, that.clearance) &&
                Objects.equals(costindexcivilengineering, that.costindexcivilengineering) &&
                Objects.equals(minglonghole, that.minglonghole) &&
                Objects.equals(concealedexcavationsection, that.concealedexcavationsection) &&
                Objects.equals(proportionsurroundingrockgrade1, that.proportionsurroundingrockgrade1) &&
                Objects.equals(proportionsurroundingrockgrade2, that.proportionsurroundingrockgrade2) &&
                Objects.equals(proportionsurroundingrockgrade3, that.proportionsurroundingrockgrade3) &&
                Objects.equals(proportionsurroundingrockgrade4, that.proportionsurroundingrockgrade4) &&
                Objects.equals(proportionsurroundingrockgrade5, that.proportionsurroundingrockgrade5) &&
                Objects.equals(proportionsurroundingrockgrade6, that.proportionsurroundingrockgrade6);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, sysid, subject, inputerDeptId, inputerDeptName, inputerDeptPathId, phoneNum, inputerName, inputerFullName, inputDate, updateDate, notifyCheck, notifyMethod, description, flag, state, projectName, subprojectName, constructionSite, designPhase, pricelevelyear, length, holedoor, clearance, costindexcivilengineering, minglonghole, concealedexcavationsection, proportionsurroundingrockgrade1, proportionsurroundingrockgrade2, proportionsurroundingrockgrade3, proportionsurroundingrockgrade4, proportionsurroundingrockgrade5, proportionsurroundingrockgrade6);
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
