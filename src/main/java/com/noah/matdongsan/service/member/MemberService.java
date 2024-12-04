package com.noah.matdongsan.service.member;

import com.noah.matdongsan.dto.member.CommonUserCreateDto;
import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.entity.user.UserRole;
import com.noah.matdongsan.repository.user.CommonUserRepository;
import com.noah.matdongsan.security.JwtProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final CommonUserRepository commonUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public void createMember(CommonUserCreateDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        CommonUser commonUser = CommonUser.builder()
                .email(dto.getEmail())
                .role(UserRole.USER)
                .password(encodedPassword)
                .phone(dto.getPhone())
                .build();

        commonUserRepository.save(commonUser);
    }

    public void softDeleteUser(Long id) {
        CommonUser user = commonUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found."));
        user.markAsRemoved();
    }

    public String login(String email, String rawPassword) {
        // 이메일로 사용자 조회
        CommonUser user = commonUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email %s not found.", email)));

        // 비밀번호 검증
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }

        // 인증 성공: JWT 생성 및 반환
        return jwtProvider.createToken(user.getEmail(), user.getRole().name());
    }

}
