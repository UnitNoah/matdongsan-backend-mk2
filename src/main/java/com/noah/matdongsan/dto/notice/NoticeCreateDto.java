    package com.noah.matdongsan.dto.notice;

    import lombok.Data;
    import org.springframework.web.multipart.MultipartFile;

    @Data
    public class NoticeCreateDto {
        private String title;
        private String content;

        private MultipartFile image;
    }
