package com.noah.matdongsan.entity.user;

import com.noah.matdongsan.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseTimeEntity {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Integer price;

    @OneToOne
    @JoinColumn(name="common_user_id")
    private CommonUser commonUser;

    public Ticket(Integer amount, Integer price, CommonUser commonUser) {
        this.amount = amount;
        this.price = price;
        this.commonUser = commonUser;
    }
}
