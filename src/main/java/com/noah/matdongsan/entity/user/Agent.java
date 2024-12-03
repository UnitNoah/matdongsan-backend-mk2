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

    @OneToOne
    @JoinColumn(name = "common_user_id", nullable = false)
    private CommonUser commonUser;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "agent_name", nullable = false)
    private String agentName;

    @Embedded
    private Address address;

    @Column(name = "license_file_url", nullable = false)
    private String licenseFileUrl;

    @Builder
    public Agent(CommonUser commonUser, String ownerName, String agentName, Address address, double latitude, double longitude) {
        this.commonUser = commonUser;
        this.ownerName = ownerName;
        this.agentName = agentName;
        this.address = address;
    }
}


