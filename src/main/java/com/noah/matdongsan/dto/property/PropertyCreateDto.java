package com.noah.matdongsan.dto.property;

import com.noah.matdongsan.entity.user.Address;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PropertyCreateDto {

    // property 관련 필드
    // 엔티티별로 프론트에서 분리해서 가져오는 게 좋은지..
    // 백엔드에서 분리된 필드를 받아 백엔드에서 나눠야하는지 고민이 됩니다.
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

    public Address createAddress(){
        if (this.paddress == null || this.ppostcode == null || this.platitude == null || this.plongitude == null) {
            throw new IllegalArgumentException("Address 관련 필드는 모두 필수입니다.");
        }
        return new Address(this.paddress, this.paddressdetail, this.ppostcode, this.platitude, this.plongitude);
    }
}
