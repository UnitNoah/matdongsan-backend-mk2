package com.noah.matdongsan.entity.property;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "property_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PropertyDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_detail_id")
    private Long id;

    @Lob
    private String content;

    @Column(name = "moving_day", nullable = false)
    private LocalDate movingDay;

    @ElementCollection
    @CollectionTable(name = "property_amenities", joinColumns = @JoinColumn(name = "property_detail_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private final Set<Amenity>amenities = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false) // 외래 키 설정
    private Property property;

    public PropertyDetail(String content, LocalDate movingDay, Set<Amenity> amenities, Property property) {
        this.content = content;
        this.movingDay = movingDay;
        this.amenities.addAll(amenities);
        this.property = property;
    }

}
