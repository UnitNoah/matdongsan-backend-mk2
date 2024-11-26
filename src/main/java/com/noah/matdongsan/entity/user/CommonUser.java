package com.noah.matdongsan.entity.user;

import com.noah.matdongsan.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonUser extends BaseTimeEntity {
    @Id
    @Column(name="common_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name="is_removed")
    private boolean isRemoved;

    public CommonUser(String email, UserRole role) {
        this.email = email;
        this.role = role;
    }

}
