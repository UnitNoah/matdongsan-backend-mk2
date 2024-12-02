package com.noah.matdongsan.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "agent_review")
public class AgentReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rate;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @OneToOne
    @JoinColumn(name = "common_user_id")
    private CommonUser commonUser;

    @Builder
    public AgentReview(String content, int rate, Agent agent, CommonUser commonUser) {
        this.content = content;
        this.rate = rate;
        this.agent = agent;
        this.commonUser = commonUser;
    }
}
