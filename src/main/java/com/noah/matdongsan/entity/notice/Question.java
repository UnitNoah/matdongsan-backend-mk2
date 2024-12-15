package com.noah.matdongsan.entity.notice;

import com.noah.matdongsan.entity.BaseTimeEntity;
import com.noah.matdongsan.entity.user.CommonUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "common_user_id", nullable = false)
    private CommonUser commonUser;

    @Column(name = "question_photo_url", nullable = true)
    private String questionPhotoUrl;

    @Builder
    public Question(String category, String title, String content, CommonUser commonUser, String questionPhotoUrl) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.commonUser = commonUser;
        this.questionPhotoUrl = questionPhotoUrl;
    }
    @Builder
    public Question(String category, String title, String content, CommonUser commonUser) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.commonUser = commonUser;
    }
}
