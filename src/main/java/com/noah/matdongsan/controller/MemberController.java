package com.noah.matdongsan.controller;

import com.noah.matdongsan.dto.member.CommonUserCreateDto;
import com.noah.matdongsan.dto.member.LoginRequestDto;
import com.noah.matdongsan.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody @Valid CommonUserCreateDto dto) throws IOException {
        memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest) {
        String token = memberService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }


}
