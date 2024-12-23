package com.noah.matdongsan.entity.property;

import com.noah.matdongsan.entity.BaseTimeEntity;
import com.noah.matdongsan.entity.user.Address;
import com.noah.matdongsan.entity.user.CommonUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Property extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long id;

    @Column(nullable = false)
    private String deposit;

    @Column(name = "monthly_fee", nullable = false)
    private String monthlyFee;

    @Column(nullable = false)
    private String maintenance;

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
    @JoinColumn(name = "common_user_id", nullable = false)
    private CommonUser commonUser;

    @Builder
    public Property(String deposit, String monthlyFee, String maintenance, String floorType,String floor, String roomSize, String title, String category, Address address,CommonUser commonUser) {
        this.deposit = deposit;
        this.monthlyFee = monthlyFee;
        this.maintenance = maintenance;
        this.floorType = floorType;
        this.floor = floor;
        this.roomSize = roomSize;
        this.title = title;
        this.category = category;
        this.address = address;
        this.commonUser = commonUser;
        this.status = PropertyStatus.AVAILABLE;
    }
    //나중에 중복 안되도록 막아야함
    public void incrementHitCount() {
        this.hitCount++;
    }

    public void updateThumbnailUrl(String thumbnailUrl){
        // 매개변수 확인해야함
        this.thumbnailUrl = thumbnailUrl;
    }
}
