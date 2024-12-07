package com.noah.matdongsan.dto.member;

import com.noah.matdongsan.entity.user.UserRole;
import lombok.Data;

@Data
public class CommonUserReadDto {

    private String name;

    private String profileUrl;

    private String phone;

    private Integer amount;

    private UserRole userRole;

    public CommonUserReadDto(String name, String profileUrl, String phone,UserRole userRole) {
        this.name = name;
        this.profileUrl = profileUrl;
        this.phone = phone;
        this.userRole = userRole;
    }

    public void updateAmount(Integer amount) {
        this.amount = amount;
    }

}
