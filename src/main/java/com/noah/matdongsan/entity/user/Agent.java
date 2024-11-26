package com.noah.matdongsan.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_id")
    private Long id;

    @Column(name = "owner_name",nullable = false)
    private String ownerName;

    @Column(name = "agent_name", nullable = false)
    private String agentName;

    @Column(name = "agent_license_number", nullable = false)
    private Long agentLicenseNumber;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name = "common_user_id", nullable = false)
    private CommonUser commonUser;

    @Column(name = "agent_photo_url", nullable = false)
    private String agentPhotoUrl;

    @Column(name = "agent_license_photo_url", nullable = false)
    private String agentLicensePhotoUrl;

    @Builder
    public Agent(String ownerName, String agentName, Long agentLicenseNumber, String phone, String agentPhotoUrl, String agentLicensePhotoUrl, Address address, CommonUser commonUser) {
        this.ownerName = ownerName;
        this.agentName = agentName;
        this.agentLicenseNumber = agentLicenseNumber;
        this.phone = phone;
        this.agentPhotoUrl = agentPhotoUrl;
        this.agentLicensePhotoUrl = agentLicensePhotoUrl;
        this.address = address;
        this.commonUser = commonUser;
    }
}
