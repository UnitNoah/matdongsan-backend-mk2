package com.noah.matdongsan.security;

import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.repository.user.CommonUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private CommonUserRepository commonUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CommonUser commonUser = commonUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        // 권한 추가
        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(commonUser.getRole().name())
        );

        return new AppUserDetails(commonUser, authorities);
    }
}
