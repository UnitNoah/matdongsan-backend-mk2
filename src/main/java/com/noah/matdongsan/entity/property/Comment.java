package com.noah.matdongsan.entity.property;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Builder
    public Comment(String comment, Property property) {
        this.comment = comment;
        this.property = property;
    }

    @PrePersist
    public void initializeCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }


}
