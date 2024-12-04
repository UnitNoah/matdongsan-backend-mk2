package com.noah.matdongsan.security;

import com.noah.matdongsan.entity.user.CommonUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class AppUserDetails extends User {
    private final CommonUser commonUser;

    public AppUserDetails(CommonUser commonUser, List<GrantedAuthority> authorities) {
        super(commonUser.getEmail(), commonUser.getPassword(),
                !commonUser.isRemoved(), true, true, true, authorities);
        this.commonUser = commonUser;
    }

}
