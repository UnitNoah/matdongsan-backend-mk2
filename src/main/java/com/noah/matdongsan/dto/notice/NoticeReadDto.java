package com.noah.matdongsan.dto.notice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeReadDto {

    private Long id;

    private String title;

    public NoticeReadDto(long id, String title) {
        this.id = id;
        this.title = title;
    }

}
