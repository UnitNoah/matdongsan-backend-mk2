package com.noah.matdongsan.dto.member;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
