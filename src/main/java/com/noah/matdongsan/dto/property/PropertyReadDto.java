package com.noah.matdongsan.dto.property;

import com.noah.matdongsan.entity.property.PropertyStatus;
import com.noah.matdongsan.entity.user.Address;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PropertyReadDto {
    private String pdeposite; // 보증금
    private String prentalfee; // 월세
    private String pfloortype; // 층 유형
    private String pfloor; // 층
    private String psize; // 크기
    private String pmaintenance; // 관리비
    private String ptitle; // 제목
    private String pcategory; // 카테고리
    private Address paddress; // 주소
    private String pthumbnail;
    private PropertyStatus status;

    @Builder
    public PropertyReadDto(String pdeposite, String prentalfee, String pfloortype, String pfloor, String psize, String pmaintenance, String ptitle, String pcategory, Address paddress, String thumbnailUrl,PropertyStatus status) {
        this.pdeposite = pdeposite;
        this.prentalfee = prentalfee;
        this.pfloortype = pfloortype;
        this.pfloor = pfloor;
        this.psize = psize;
        this.pmaintenance = pmaintenance;
        this.ptitle = ptitle;
        this.pcategory = pcategory;
        this.pthumbnail = thumbnailUrl;
        this.paddress = paddress;
        this.status = status;
    }
}
