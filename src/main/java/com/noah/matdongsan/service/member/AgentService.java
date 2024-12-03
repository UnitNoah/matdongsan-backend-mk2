package com.noah.matdongsan.service.member;

import com.noah.matdongsan.dto.member.AgentCreateDto;
import com.noah.matdongsan.entity.user.Agent;
import com.noah.matdongsan.entity.user.CommonUser;
import com.noah.matdongsan.entity.user.UserRole;
import com.noah.matdongsan.repository.user.AgentRepository;
import com.noah.matdongsan.repository.user.CommonUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgentService {
    private final CommonUserRepository commonUserRepository;
    private final AgentRepository agentRepository;

    public void createAgent(AgentCreateDto dto) {
        CommonUser commonUser = CommonUser.builder()
                .email(dto.getEmail())
                .role(UserRole.AGENT)
                .phone(dto.getPhone())
                .build();

        commonUser = commonUserRepository.save(commonUser);

        Agent agent = Agent.builder()
                .commonUser(commonUser)
                .agentName(dto.getAgentName())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .address(dto.getAddress())
                .build();

        agentRepository.save(agent);
    }
}

