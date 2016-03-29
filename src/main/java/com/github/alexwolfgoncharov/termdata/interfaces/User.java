package com.github.alexwolfgoncharov.termdata.interfaces;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by alexwolf on 10.01.16.
 */
@Entity
@Table(name = "auth_demo", schema = "Termometr")
public class User {
    private int id;
    private String login;
    private String pass;
    private Timestamp time;
    private String role;
    private Byte access;
    private String company;
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "login_baseid", joinColumns = { @JoinColumn(name = "login_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "base_id", referencedColumnName = "ID") })
    Set<BaseID> baseIdSet;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "login_baseid_demo",

            joinColumns = @JoinColumn(name = "login_id", referencedColumnName = "ID"),

            inverseJoinColumns = @JoinColumn(name = "base_id", referencedColumnName = "ID"))
    public Set<BaseID> getBaseIdSet() {
        return baseIdSet;
    }

    public void setBaseIdSet(Set<BaseID> baseIdSet) {
        this.baseIdSet = baseIdSet;
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "PASS")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "role")
//    @Enumerated(EnumType.STRING)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "access")
    public Byte getAccess() {
        return access;
    }

    public void setAccess(Byte access) {
        this.access = access;
    }

    @Basic
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (access != null ? !access.equals(that.access) : that.access != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (access != null ? access.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", time=" + time +
                ", role='" + role + '\'' +
                ", access=" + access +
                ", company='" + company + '\'' +
                ", baseIdSet=" + baseIdSet +
                '}';
    }
}
