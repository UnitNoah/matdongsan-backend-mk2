package com.noah.matdongsan.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Comment {
    @Id
    private Long id;

    @Lob
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(name="property_id")
    private Long propertyId;
}
