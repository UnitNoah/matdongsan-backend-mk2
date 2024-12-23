package com.noah.matdongsan.dto.property;

import com.noah.matdongsan.entity.user.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
public class PropertyUserDto {

    private Long uid;

    private String uemail;

    private String uname;

    private UserRole urole;

    private boolean isRemoved;

    private String profileUrl;

    private String uphone;

    @Builder
    public PropertyUserDto(String uphone, String profileUrl, boolean isRemoved, UserRole urole, String uname, String uemail, Long uid) {
        this.uphone = uphone;
        this.profileUrl = profileUrl;
        this.isRemoved = isRemoved;
        this.urole = urole;
        this.uname = uname;
        this.uemail = uemail;
        this.uid = uid;
    }
}
