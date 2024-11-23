package com.noah.matdongsan.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String role;

    @Column(name = "is_removed")
    boolean isRemoved;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    String name;

    String profileURL;

    String phone;
}
