package com.noah.matdongsan.entity.user;

import com.noah.matdongsan.entity.property.Property;
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
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public AgentReview(String content, int rate, Agent agent, Member member) {
        this.content = content;
        this.rate = rate;
        this.agent = agent;
        this.member = member;
    }
}
