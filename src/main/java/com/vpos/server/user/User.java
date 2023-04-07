package com.vpos.server.user;
import com.vpos.server.business.Business;
import com.vpos.server.role.Role;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "`users`")
public class User {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname", nullable = false, columnDefinition = "Text")
    private String firstname;

    @Column(name = "lastname", nullable = false, columnDefinition = "Text")
    private String lastname;

    @Column(name = "email", nullable = false, columnDefinition = "Text", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_admin")
    private Boolean is_admin;

    @OneToMany
    @JoinColumn(name = "business_id", referencedColumnName = "id")
    private List<Business> business = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private Boolean status;

    @OneToMany
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private List<Role> roles = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updated_at;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }


    public User(String firstname, String lastname, String email, String password, Boolean is_admin, List<Business> business, Boolean status, List<Role> roles, Date created_at, Date updated_at) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
        this.business = business;
        this.status = status;
        this.roles = roles;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Business> getBusiness() {
        return business;
    }

    public void setBusiness(List<Business> business) {
        this.business = business;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", is_admin=" + is_admin +
                ", business=" + business +
                ", status=" + status +
                ", roles=" + roles +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
