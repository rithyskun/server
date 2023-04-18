package com.vpos.server.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpos.server.business.Business;
import com.vpos.server.role.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "firstname", nullable = false, columnDefinition = "Text")
    private String firstname;

    @NotBlank(message = "Last name is required")
    @Column(name = "lastname", nullable = false, columnDefinition = "Text")
    private String lastname;

    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false, columnDefinition = "Text", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_admin")
    private Boolean is_admin;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Business> business = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date created_at;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updated_at;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }


    public User(String firstname, String lastname, String email, String password, Boolean is_admin, List<Business> business, Boolean status, Collection<Role> roles, Date created_at, Date updated_at) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
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
