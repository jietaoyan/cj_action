package com.bccm.projectservices.sqlEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ENGINEERINGGUIDANCE_COMPANY", schema = "dbo", catalog = "INTEGRATEDMANAGE")
public class EngineeringGuidanceCompany {
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
    private String formNo;
    private String projectName;
    private String laborSubcontractor;
    private String subcontractortype;
    private String businesscontent;
    private String companyaddress;
    private String contact;
    private String datatime;
    private String projectsite;
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
    @Column(name = "FORMNO", nullable = true, length = 50)
    public String getFormNo() {
        return formNo;
    }

    public void setFormNo(String formNo) {
        this.formNo = formNo;
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
    @Column(name = "LABORSUBCONTRACTOR", nullable = true, length = 500)
    public String getLaborSubcontractor() {
        return laborSubcontractor;
    }

    public void setLaborSubcontractor(String laborSubcontractor) {
        this.laborSubcontractor = laborSubcontractor;
    }

    @Basic
    @Column(name = "SUBCONTRACTORTYPE", nullable = true, length = 50)
    public String getSubcontractortype() {
        return subcontractortype;
    }

    public void setSubcontractortype(String subcontractortype) {
        this.subcontractortype = subcontractortype;
    }

    @Basic
    @Column(name = "BUSINESSCONTENT", nullable = true, length = 500)
    public String getBusinesscontent() {
        return businesscontent;
    }

    public void setBusinesscontent(String businesscontent) {
        this.businesscontent = businesscontent;
    }

    @Basic
    @Column(name = "COMPANYADDRESS", nullable = true, length = 500)
    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    @Basic
    @Column(name = "CONTACT", nullable = true, length = 500)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
        EngineeringGuidanceCompany that = (EngineeringGuidanceCompany) o;
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
                Objects.equals(formNo, that.formNo) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(laborSubcontractor, that.laborSubcontractor) &&
                Objects.equals(subcontractortype, that.subcontractortype) &&
                Objects.equals(businesscontent, that.businesscontent) &&
                Objects.equals(companyaddress, that.companyaddress) &&
                Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, sysid, subject, inputerDeptId, inputerDeptName, inputerDeptPathId, phoneNum, inputerName, inputerFullName, inputDate, updateDate, notifyCheck, notifyMethod, description, flag, state, formNo, projectName, laborSubcontractor, subcontractortype, businesscontent, companyaddress, contact);
    }

    @Basic
    @Column(name = "DATATIME", nullable = true, length = 50)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy")
    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
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
    @Column(name = "PROJECTCITY", nullable = true, length = 50)
    public String getProjectCity() {
        return projectCity;
    }

    public void setProjectCity(String projectCity) {
        this.projectCity = projectCity;
    }
}
