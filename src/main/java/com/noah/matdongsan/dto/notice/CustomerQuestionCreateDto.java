package com.noah.matdongsan.dto.notice;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerQuestionCreateDto {
    private String title;
    private String content;
    private String category;
    private String imageUrl;
    private String email;

    private MultipartFile image;

    public void updateImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
