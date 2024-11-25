package com.noah.matdongsan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long id;

    @Column(nullable = false)
    private long deposit;

    @Column(name = "monthly_fee", nullable = false)
    private long monthlyFee;

    @Column(nullable = false)
    private long maintenance;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;

    @Column(length = 2, nullable = false)
    private String floor;

    @Column(name = "floor_type", length = 10, nullable = false)
    private String floorType;

    @Column(name = "room_size", nullable = false)
    private String roomSize;

    @Column(name = "hit_count", nullable = false)
    private int hitCount = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String category;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public Property(long deposit, long monthlyfee, long maintenance, String floorType, String roomSize, String title, String category) {
        this.deposit = deposit;
        this.monthlyFee = monthlyfee;
        this.maintenance = maintenance;
        this.floorType = floorType;
        this.roomSize = roomSize;
        this.title = title;
        this.category = category;
    }

    public void incrementHitCount() {
        this.hitCount++;
    }
}
