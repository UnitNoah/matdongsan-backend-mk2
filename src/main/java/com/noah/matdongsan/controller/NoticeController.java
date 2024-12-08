package com.noah.matdongsan.controller;

import com.noah.matdongsan.dto.notice.NoticeReadDto;
import com.noah.matdongsan.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<NoticeReadDto>> getNoticesList(
            @RequestParam(name = "pageNo",required = false, defaultValue = "1") String pageNo
    ) {

        // 파라미터를 서비스에 전달
        List<NoticeReadDto> notices = noticeService.getNoticeList();
        return ResponseEntity.ok(notices);
    }
}
