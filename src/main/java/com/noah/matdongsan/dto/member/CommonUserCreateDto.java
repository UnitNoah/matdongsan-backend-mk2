package com.noah.matdongsan.dto.member;

import com.noah.matdongsan.entity.user.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CommonUserCreateDto {
    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "올바르지 않은 이메일 형식입니다."
    )
    private String email;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotBlank(message = "전화번호는 빈 값이 될 수 없습니다.")
    private String phone;

    @Column(name = "is_removed")
    private boolean isRemoved;

}
