package com.noah.matdongsan.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "property_photos")
public class PropertyPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_photo_id")
    private Long id;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    public PropertyPhoto(String photoUrl,Property property) {
        this.photoUrl=photoUrl;
        this.property = property;
    }
}
