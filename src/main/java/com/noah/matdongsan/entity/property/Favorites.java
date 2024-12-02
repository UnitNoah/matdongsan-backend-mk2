package com.noah.matdongsan.entity.property;

import com.noah.matdongsan.entity.user.CommonUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "common_user_id")
    private CommonUser commonUser;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
