package com.noah.matdongsan.service.member;

import com.noah.matdongsan.dto.member.CommonUserCreateDto;
import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.entity.user.UserRole;
import com.noah.matdongsan.repository.user.CommonUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final CommonUserRepository commonUserRepository;

    public void createMember(CommonUserCreateDto dto) {
        CommonUser commonUser = CommonUser.builder()
                .email(dto.getEmail())
                .role(UserRole.USER)
                .phone(dto.getPhone())
                .build();

        commonUserRepository.save(commonUser);
    }

    public void softDeleteUser(Long id) {
        CommonUser user = commonUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found."));
        user.markAsRemoved();
    }

}
