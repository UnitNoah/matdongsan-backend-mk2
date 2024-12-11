package com.noah.matdongsan.dto.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.noah.matdongsan.entity.notice.Notice;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeReadDto {

    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    public NoticeReadDto(long id, String title,LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
    }

    public static NoticeReadDto from(Notice notice) {
        return new NoticeReadDto(notice.getId(), notice.getTitle(),notice.getCreatedDate());
    }
}
