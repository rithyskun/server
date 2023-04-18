package com.vpos.server.auth;

/**
 * @created 12/04/2023 - 5:04 AM
 * @project server
 * @author Rithy SKUN
 **/

import com.vpos.server.business.Business;
import com.vpos.server.role.Role;

import java.util.Collection;

public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private Boolean is_admin;
    private Collection<Business> business;
    private Boolean status;
    private Collection<Role> roles;

    public RegisterRequest() {
    }

    public RegisterRequest(String firstname, String lastname, String email, String password, Boolean is_admin, Collection<Business> business, Boolean status, Collection<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
        this.business = business;
        this.status = status;
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Collection<Business> getBusiness() {
        return business;
    }

    public void setBusiness(Collection<Business> business) {
        this.business = business;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", is_admin=" + is_admin +
                ", business=" + business +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}
