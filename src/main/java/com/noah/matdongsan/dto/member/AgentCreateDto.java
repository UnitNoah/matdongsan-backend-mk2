package com.noah.matdongsan.dto.member;

import com.noah.matdongsan.entity.user.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AgentCreateDto extends CommonUserCreateDto {

    @NotBlank(message = "중개업소 이름은 필수입니다.")
    private String agentName;

    @NotBlank(message = "대표 사진은 필수입니다.")
    private String agentPhotoUrl;

    @Valid
    private Address address;
}
