package com.noah.matdongsan.dto.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.noah.matdongsan.entity.notice.Notice;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NoticeDetailReadDto {
    private Long id;

    private String title;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    public NoticeDetailReadDto(Long id, String title, LocalDateTime createdAt, String content) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.content = content;
    }

    public static NoticeDetailReadDto from(Notice notice) {
        return new NoticeDetailReadDto(notice.getId(), notice.getTitle(), notice.getCreatedDate(),notice.getContent());
    }
}
