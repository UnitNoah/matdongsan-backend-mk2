package com.noah.matdongsan.controller;

import com.noah.matdongsan.dto.member.CommonUserCreateDto;
import com.noah.matdongsan.dto.member.CommonUserReadDto;
import com.noah.matdongsan.dto.member.LoginRequestDto;
import com.noah.matdongsan.dto.member.LoginResponseDto;
import com.noah.matdongsan.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> createMember(@ModelAttribute @Valid CommonUserCreateDto dto) throws IOException {
        memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        String token = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
        LoginResponseDto loginResponseDto = memberService.buildLoginResponse(loginRequest.getEmail());

        loginResponseDto.updateToken(token);
        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/login/find-email")
    public ResponseEntity<String> recoverEmail(@RequestParam String name, @RequestParam String phone){
        String recoveredEmail = memberService.getRecoverEmail(name, phone);
        return ResponseEntity.ok(recoveredEmail);
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExistence(@RequestParam(name = "email" ,required = true) String email){
        return ResponseEntity.ok( memberService.isEmailRegistered(email));
    }

    //비밀번호 찾기



    @GetMapping("/tickets/remain")
    public ResponseEntity<Optional<Integer>> checkTickets(@RequestParam(name = "email" ,required = true) String email){
        return ResponseEntity.ok( memberService.hasAvailableTickets(email));
    }

    @GetMapping("/myPage")
    public ResponseEntity<CommonUserReadDto> getUserDataForMyPage(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CommonUserReadDto commonUserReadDto = memberService.getUserDataByEmail(email);
        return ResponseEntity.ok(commonUserReadDto);
    }

}
