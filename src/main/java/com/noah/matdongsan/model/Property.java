package com.noah.matdongsan.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Property {
    @Id
    @GeneratedValue
    @Column(name = "property_id")
    private Long id;

    private int deposite;

    private int monthlyFee;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(length = 2)
    private String floor;

    @Column(name = "floor_type", length = 10)
    private String floorType;

    @Column(name = "room_size")
    private String roomSize;

    private int maintenance;

    @Column(name="hit_count")
    private int hitCount;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    private String title;

    private String category;

    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    private String postcode;

    private String latitude;

    private String longitude;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
