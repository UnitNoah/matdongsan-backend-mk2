package com.noah.matdongsan.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {
    @Column(nullable = false)
    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    public Address(String address, String addressDetail, String postcode, String latitude, String longitude) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
