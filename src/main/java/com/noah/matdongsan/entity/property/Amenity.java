package com.noah.matdongsan.entity.property;

public enum Amenity {
    BATH("pdbath"),
    LIFT("pdlift"),
    BED("pdbed"),
    LOT("pdlot"),
    HEATING("pdheating"),
    COOLING("pdcooling"),
    MICROWAVE("pdmicrowave"),
    BURNER("pdburner"),
    FRIDGE("pdfridge"),
    SHOE_CLOSET("pdshoecloset"),
    TV("pdtv"),
    CLOSET("pdcloset"),
    DINNING_TABLE("pddinningtable"),
    TABLE("pdtable"),
    WASHER("pdwasher"),
    INDUCTION("pdinduction");

    private final String fieldName;

    Amenity(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
