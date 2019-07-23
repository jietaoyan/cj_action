package com.bccm.projectservices.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user_permission_data", schema = "bccm", catalog = "")
public class UserPermissionDataEntity {
    private String guid;
    private String inputerfullname;
    private String inputerusername;
    private Timestamp inputdatetime;
    private String permissionfullname;
    private String permissionusername;
    private String permissionlevel;
    private Timestamp permissionstarttime;
    private Timestamp permissionendtime;
    private String permissionstate;
    private String remark;

    @Id
    @Column(name = "guid", nullable = false, length = 50)
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Basic
    @Column(name = "inputerfullname", nullable = true, length = 255)
    public String getInputerfullname() {
        return inputerfullname;
    }

    public void setInputerfullname(String inputerfullname) {
        this.inputerfullname = inputerfullname;
    }

    @Basic
    @Column(name = "inputerusername", nullable = true, length = 255)
    public String getInputerusername() {
        return inputerusername;
    }

    public void setInputerusername(String inputerusername) {
        this.inputerusername = inputerusername;
    }

    @Basic
    @Column(name = "inputdatetime", nullable = true)
    public Timestamp getInputdatetime() {
        return inputdatetime;
    }

    public void setInputdatetime(Timestamp inputdatetime) {
        this.inputdatetime = inputdatetime;
    }

    @Basic
    @Column(name = "permissionfullname", nullable = true, length = 255)
    public String getPermissionfullname() {
        return permissionfullname;
    }

    public void setPermissionfullname(String permissionfullname) {
        this.permissionfullname = permissionfullname;
    }

    @Basic
    @Column(name = "permissionusername", nullable = true, length = 255)
    public String getPermissionusername() {
        return permissionusername;
    }

    public void setPermissionusername(String permissionusername) {
        this.permissionusername = permissionusername;
    }

    @Basic
    @Column(name = "permissionlevel", nullable = true, length = 255)
    public String getPermissionlevel() {
        return permissionlevel;
    }

    public void setPermissionlevel(String permissionlevel) {
        this.permissionlevel = permissionlevel;
    }

    @Basic
    @Column(name = "permissionstarttime", nullable = true)
    public Timestamp getPermissionstarttime() {
        return permissionstarttime;
    }

    public void setPermissionstarttime(Timestamp permissionstarttime) {
        this.permissionstarttime = permissionstarttime;
    }

    @Basic
    @Column(name = "permissionendtime", nullable = true)
    public Timestamp getPermissionendtime() {
        return permissionendtime;
    }

    public void setPermissionendtime(Timestamp permissionendtime) {
        this.permissionendtime = permissionendtime;
    }

    @Basic
    @Column(name = "permissionstate", nullable = true, length = 255)
    public String getPermissionstate() {
        return permissionstate;
    }

    public void setPermissionstate(String permissionstate) {
        this.permissionstate = permissionstate;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 500)
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
        UserPermissionDataEntity that = (UserPermissionDataEntity) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(inputerfullname, that.inputerfullname) &&
                Objects.equals(inputerusername, that.inputerusername) &&
                Objects.equals(inputdatetime, that.inputdatetime) &&
                Objects.equals(permissionfullname, that.permissionfullname) &&
                Objects.equals(permissionusername, that.permissionusername) &&
                Objects.equals(permissionlevel, that.permissionlevel) &&
                Objects.equals(permissionstarttime, that.permissionstarttime) &&
                Objects.equals(permissionendtime, that.permissionendtime) &&
                Objects.equals(permissionstate, that.permissionstate) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guid, inputerfullname, inputerusername, inputdatetime, permissionfullname, permissionusername, permissionlevel, permissionstarttime, permissionendtime, permissionstate, remark);
    }
}
