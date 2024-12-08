package com.noah.matdongsan.dto.member;

import com.noah.matdongsan.entity.user.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 50, message = "비밀번호는 최소 8자 이상, 최대 50자 이하여야 합니다.")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotBlank(message = "전화번호는 빈 값이 될 수 없습니다.")
    private String phone;

    private boolean isRemoved = false;

    private String profileUrl;

    private MultipartFile profileImage;

    public void updateProfileUrl(String url) {
        this.profileUrl = url;
    }
}
