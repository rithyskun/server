package com.vpos.server.user;
import jakarta.persistence.*;

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

    @Column(name = "businessId")
    private List<Integer> businessId;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "roles")
    private List<String> roles;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String firstname, String lastname, String email, String password, Boolean is_admin, List<Integer> businessId, Boolean status, List<String> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
        this.businessId = businessId;
        this.status = status;
        this.roles = roles;
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

    public List<Integer> getBusinessId() {
        return businessId;
    }

    public void setBusinessId(List<Integer> businessId) {
        this.businessId = businessId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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
                ", businessId=" + businessId +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}
