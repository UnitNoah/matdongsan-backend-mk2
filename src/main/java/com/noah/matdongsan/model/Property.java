package com.noah.matdongsan.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "properties")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    public void incrementHitCount() {
        this.hitCount++;
    }
}
