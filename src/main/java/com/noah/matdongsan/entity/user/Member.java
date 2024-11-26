package com.noah.matdongsan.entity.user;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(name = "is_removed")
    private boolean isRemoved;

    private String name;

    private String profileURL;

    private String phone;

    @OneToOne
    @JoinColumn(name="common_user_id")
    private CommonUser commonUser;
}
