package com.noah.matdongsan.service.member;

import com.noah.matdongsan.dto.member.CommonUserCreateDto;
import com.noah.matdongsan.dto.member.CommonUserReadDto;
import com.noah.matdongsan.dto.member.LoginResponseDto;
import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.entity.user.UserRole;
import com.noah.matdongsan.repository.user.CommonUserRepository;
import com.noah.matdongsan.repository.user.TicketRepository;
import com.noah.matdongsan.security.JwtProvider;
import com.noah.matdongsan.service.common.FileUploadService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final CommonUserRepository commonUserRepository;
    private final TicketRepository ticketRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final FileUploadService fileUploadService;
    private final JwtProvider jwtProvider;

    public void createMember(CommonUserCreateDto dto) throws IOException {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        if (dto.getProfileImage() != null) {
            String uploadedUrl = fileUploadService.uploadFile(dto.getProfileImage());
            dto.updateProfileUrl(uploadedUrl);
        }

        CommonUser commonUser = CommonUser.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .role(UserRole.USER)
                .password(encodedPassword)
                .phone(dto.getPhone())
                .profileUrl(dto.getProfileUrl())
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

    public LoginResponseDto buildLoginResponse(String email) {
        Optional<CommonUser> userData = commonUserRepository.findByEmail(email);

        LoginResponseDto response;

        response = userData.filter(user -> !user.isRemoved())
                .map(user -> new LoginResponseDto(
                        "success",
                        user.getEmail(),
                        user.getRole().name()
                ))
                .orElseGet(() -> new LoginResponseDto(
                        "removed",
                        null,
                        null
                ));

        return response;
    }

    public boolean isEmailRegistered(String email) {
        return commonUserRepository.findByEmail(email).isPresent();
    }

    public String getRecoverEmail(String name, String phone) {
        Optional<CommonUser> user = commonUserRepository.findByNameAndPhone(name, phone);

        if (user.isPresent()) {
            String email = user.get().getEmail();
            return getMaskedEmail(email);
        } else {
            throw new EntityNotFoundException("사용자를 찾을 수 없습니다.");
        }

    }

    public String getMaskedEmail(String email) {
        int atIndex = email.indexOf('@');

        if (atIndex > 0) {
            String localPart = email.substring(0, atIndex);
            String domainPart = email.substring(atIndex);

            if (localPart.length() > 3) {
                localPart = localPart.substring(0, 3) + "****";
            } else {
                localPart = "****";
            }

            return localPart + domainPart;
        } else {
            return "Invalid email format";
        }
    }

    public Boolean checkTickets(String email) {
        Optional<Long> userId = commonUserRepository.findUserIdByEmail(email);
        return userId.isPresent();
    }

    public Optional<Integer> hasAvailableTickets(String email) {
        Optional<Long> userId = commonUserRepository.findUserIdByEmail(email);

        if (userId.isPresent()) {
            Integer ticketAmount = ticketRepository.getTicketAmountByUserId(userId.get());
            return Optional.ofNullable(ticketAmount);
        }

        return Optional.empty();
    }

    public CommonUserReadDto getUserDataByEmail(String email) {
        CommonUser commonUser = commonUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

        CommonUserReadDto commonUserReadDto = new CommonUserReadDto(
                commonUser.getName(),
                commonUser.getProfileUrl(),
                commonUser.getPhone(),
                commonUser.getRole()
        );

        Integer amount = ticketRepository.getTicketAmountByUserId(commonUser.getId());
        commonUserReadDto.updateAmount(amount);

        return commonUserReadDto;
    }

    public CommonUser findUserByEmail(String email) {
        return commonUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }
}
