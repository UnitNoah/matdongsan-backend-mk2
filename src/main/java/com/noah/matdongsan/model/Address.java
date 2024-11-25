package com.noah.matdongsan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String address;
    @Column(name = "address_detail")
    private String addressDetail;
    private String postcode;
    private String latitude;
    private String longitude;
}
