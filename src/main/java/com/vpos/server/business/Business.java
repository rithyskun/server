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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "address1")
    private String address1;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    @Column(name = "userId")
    private Integer userId;
    @Column(name = "created_at", updatable = false)
    private Date created_at;
}
