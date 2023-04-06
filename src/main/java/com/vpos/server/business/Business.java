package com.vpos.server.business;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "`business`")
public class Business {

    @Id
    @SequenceGenerator(name = "business_sequence", allocationSize = 1, sequenceName = "business_sequence")
    @Column(name = "id", nullable = false)

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "business_sequence"
    )
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "address1")
    private String address1;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active = false;

    @Column(name = "userId")
    private Integer userId;

    public Business() {
    }

    public Business(Long id) {
        this.id = id;
    }

    public Business(String name, String address, String address1, Boolean is_active, Integer userId) {
        this.name = name;
        this.address = address;
        this.address1 = address1;
        this.is_active = is_active;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", address1='" + address1 + '\'' +
                ", is_active=" + is_active +
                ", userId=" + userId +
                '}';
    }
}
