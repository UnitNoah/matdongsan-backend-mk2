package com.noah.matdongsan.controller;

import com.noah.matdongsan.dto.property.PropertyCreateDto;
import com.noah.matdongsan.dto.property.PropertyReadDto;
import com.noah.matdongsan.service.property.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProperties(
            @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "rentType", required = false) String rentType,
            @RequestParam(name = "floorType", required = false) String floorType,
            @RequestParam(name = "price", required = false) String price,
            @RequestParam(name = "lat", required = false) String lat,
            @RequestParam(name = "lng", required = false) String lng
    ) {
        Pageable pageable = PageRequest.of(pageNo - 1, size);

        Page<PropertyReadDto> propertyPage = propertyService.getProperties(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("content", propertyPage.getContent());
        response.put("currentPage", propertyPage.getNumber() + 1); // 1-based indexing
        response.put("totalItems", propertyPage.getTotalElements());
        response.put("totalPages", propertyPage.getTotalPages());
        response.put("pageSize", propertyPage.getSize());

        return ResponseEntity.ok(response);
    }

}
