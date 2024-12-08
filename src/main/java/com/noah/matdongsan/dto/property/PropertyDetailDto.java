package com.noah.matdongsan.dto.property;

import lombok.Data;

@Data
public class PropertyDetailDto {
    private String pdcontent; // 상세 설명
    private String pdmoveindate; // 입주 가능 날짜
    private String pdbath; // 화장실 유무
    private String pdlift; // 엘리베이터 유무
    private boolean pdbed; // 침대 유무
    private String pdlot; // 주차 공간
    private String pdheating; // 난방
    private String pdcooling; // 냉방
    private boolean pdmicrowave; // 전자레인지 유무
    private boolean pdburner; // 가스레인지 유무
    private boolean pdfridge; // 냉장고 유무
    private boolean pdshoecloset; // 신발장 유무
    private boolean pdtv; // TV 유무
    private boolean pdcloset; // 옷장 유무
    private boolean pddinningtable; // 식탁 유무
    private boolean pdtable; // 책상 유무
    private boolean pdwasher; // 세탁기 유무
    private boolean pdinduction; // 인덕션 유무
    private String moveIn; // 입주 여부
}
