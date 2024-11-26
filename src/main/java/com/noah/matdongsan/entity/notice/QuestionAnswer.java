package com.noah.matdongsan.entity.notice;

import com.noah.matdongsan.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "question_answer")
public class QuestionAnswer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Builder
    public QuestionAnswer(String content, Question question) {
        this.content = content;
        this.question = question;
    }
}
