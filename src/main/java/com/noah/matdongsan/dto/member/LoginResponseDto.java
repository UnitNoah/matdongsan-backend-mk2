package com.noah.matdongsan.dto.member;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponseDto {
    private String result;       // 로그인 결과 ("success", "fail", "removed")
    private String email;        // 사용자 이메일
    private String token;        // JWT 토큰
    private String userRole;     // 사용자 역할 (예: "USER", "ADMIN")

    public LoginResponseDto(String result,String email, String userRole){
        this.result = result;
        this.email=email;
        this.userRole=userRole;
    }

    public void updateToken(String token){
        this.token = token;
    }
}
