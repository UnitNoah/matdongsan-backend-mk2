package com.noah.matdongsan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {
    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    private String postcode;
    private String latitude;
    private String longitude;

    public Address(String address, String addressDetail, String postcode, String latitude, String longitude) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
