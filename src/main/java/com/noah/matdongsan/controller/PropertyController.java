package com.noah.matdongsan.controller;

import com.noah.matdongsan.dto.property.PropertyCreateDto;
import com.noah.matdongsan.service.property.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/payment/ticket/{product}")
    public void purchaseTicket(@PathVariable(name="product") int product){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("상품 " +product);
        propertyService.updateTicketAmount(email,product);
    }

    @PostMapping("/registerForm")
    public void createProperty(@ModelAttribute PropertyCreateDto propertyCreateDto){
        log.info(propertyCreateDto.toString());
    }
}
