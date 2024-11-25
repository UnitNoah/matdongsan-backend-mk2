package com.noah.matdongsan.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Column(nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "agent_photo_url", nullable = false)
    private String agentPhotoUrl;
}
