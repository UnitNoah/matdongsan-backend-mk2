package com.noah.matdongsan.dto.property;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PropertyCreateDto {

    // property 관련 필드
    private String pdeposite; // 보증금
    private String prentalfee; // 월세
    private String pfloortype; // 층 유형
    private String pfloor; // 층
    private String psize; // 크기
    private String pmaintenance; // 관리비
    private String ptitle; // 제목
    private String pcategory; // 카테고리
    private String paddress; // 주소
    private String paddressdetail; // 상세 주소
    private String ppostcode; // 우편번호
    private String platitude; // 위도
    private String plongitude; // 경도

    // 이미지 관련 필드
    private MultipartFile pthumbnail; // 썸네일 이미지 파일
    private List<MultipartFile> ppattach; // 디테일 이미지 파일 리스트

    // propertyDetail 관련 필드
    private PropertyDetailDto propertyDetail; // 중첩 객체로 처리
}
