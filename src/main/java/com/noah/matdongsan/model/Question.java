package com.noah.matdongsan.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Enumerated(EnumType.STRING) // Enum을 문자열로 저장
    @Column(nullable = false)
    private QuestionCategory category;
    private String title;
    private String content;

    @OneToOne
    @JoinColumn(name = "common_user_id", nullable = false)
    private CommonUser commonUser;

    @OneToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;

    @Column(name = "question_photo_url", nullable = false)
    private String questionPhotoUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public Question(QuestionCategory category, String title, String content, CommonUser commonUser, Answer answer, String questionPhotoUrl) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.commonUser = commonUser;
        this.answer = answer;
        this.questionPhotoUrl = questionPhotoUrl;
    }
}
