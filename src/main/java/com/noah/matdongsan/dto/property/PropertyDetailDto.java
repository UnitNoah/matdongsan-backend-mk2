package com.noah.matdongsan.dto.property;

import com.noah.matdongsan.entity.property.Amenity;
import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.property.PropertyDetail;
import lombok.Data;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

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

    public PropertyDetailDto(PropertyDetail propertyDetail) {
        this.pdcontent = propertyDetail.getContent();
        this.pdmoveindate = propertyDetail.getMovingDay().toString();

        Set<Amenity> amenities = propertyDetail.getAmenities();
        this.pdbath = amenities.contains(Amenity.BATH) ? "yes" : "no";
        this.pdlift = amenities.contains(Amenity.LIFT) ? "yes" : "no";
        this.pdbed = amenities.contains(Amenity.BED);
        this.pdmicrowave = amenities.contains(Amenity.MICROWAVE);
        this.pdburner = amenities.contains(Amenity.BURNER);
        this.pdfridge = amenities.contains(Amenity.FRIDGE);
        this.pdshoecloset = amenities.contains(Amenity.SHOE_CLOSET);
        this.pdtv = amenities.contains(Amenity.TV);
        this.pdcloset = amenities.contains(Amenity.CLOSET);
        this.pddinningtable = amenities.contains(Amenity.DINNING_TABLE);
        this.pdtable = amenities.contains(Amenity.TABLE);
        this.pdwasher = amenities.contains(Amenity.WASHER);
        this.pdinduction = amenities.contains(Amenity.INDUCTION);
    }

    public PropertyDetail toEntity(Property property) {
        Set<Amenity> amenities = EnumSet.noneOf(Amenity.class);

        for (Amenity amenity : Amenity.values()) {
            if (isAmenityPresent(amenity)) {
                amenities.add(amenity);
            }
        }

        LocalDate movingDay = pdmoveindate != null ? LocalDate.parse(pdmoveindate) : null;

        return new PropertyDetail(pdcontent, movingDay, amenities, property);
    }


    private boolean isAmenityPresent(Amenity amenity) {
        return switch (amenity) {
            case BATH -> "yes".equalsIgnoreCase(pdbath);
            case LIFT -> "yes".equalsIgnoreCase(pdlift);
            case BED -> pdbed;
            case LOT -> "yes".equalsIgnoreCase(pdlot);
            case HEATING -> "yes".equalsIgnoreCase(pdheating);
            case COOLING -> "yes".equalsIgnoreCase(pdcooling);
            case MICROWAVE -> pdmicrowave;
            case BURNER -> pdburner;
            case FRIDGE -> pdfridge;
            case SHOE_CLOSET -> pdshoecloset;
            case TV -> pdtv;
            case CLOSET -> pdcloset;
            case DINNING_TABLE -> pddinningtable;
            case TABLE -> pdtable;
            case WASHER -> pdwasher;
            case INDUCTION -> pdinduction;
        };
    }

}
