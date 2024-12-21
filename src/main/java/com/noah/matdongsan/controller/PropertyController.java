package com.noah.matdongsan.controller;

import com.noah.matdongsan.dto.property.PropertyCreateDto;
import com.noah.matdongsan.dto.property.PropertyReadDto;
import com.noah.matdongsan.service.property.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/payment/ticket/{product}")
    public void purchaseTicket(@PathVariable(name="product") int product){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        propertyService.updateTicketAmount(email,product);
    }

    @PostMapping("/registerForm")
    public void createProperty(@ModelAttribute PropertyCreateDto propertyCreateDto,@AuthenticationPrincipal String userEmail) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        propertyService.createProperty(propertyCreateDto,email);
    }

    //페이지# 받아서 인피니티 스크롤 구현 해야함
    @GetMapping
    public ResponseEntity<List<PropertyReadDto>> getProperties(){
        
        return null;
    }
}
