package com.noah.matdongsan.controller;

import com.amazonaws.Response;
import com.noah.matdongsan.dto.notice.*;
import com.noah.matdongsan.service.notice.NoticeService;
import com.noah.matdongsan.service.notice.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<PagedResponseDto<NoticeReadDto>> getNoticesList(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "criteria", required = false, defaultValue = "createdDate") String criteria,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {

        PagedResponseDto<NoticeReadDto> notices = noticeService.getNoticeList(pageNo, criteria, keyword);
        return ResponseEntity.ok(notices);
    }

    //admin만 작성하도록 해야함
    //공지사항 생성
    @Secured("ROLE_MANAGER")
    @PostMapping
    public void createNotice(NoticeCreateDto noticeCreateDto) {
        log.info(noticeCreateDto.toString());
    }

    @GetMapping("/detailNotice/{id}")
    public ResponseEntity<NoticeDetailReadDto> getNoticeDetailData(@PathVariable(name = "id") Long id) {
        NoticeDetailReadDto detail = noticeService.getDetailPageById(id);
        return ResponseEntity.ok(detail);
    }

    //공지사항 삭제
    //공지사항 수정

    //1:1 문의 생성
    @PostMapping("/customerInquiry")
    public void createCustomerInquiry(@ModelAttribute CustomerQuestionCreateDto dto) throws IOException {
        questionService.createCustomerInquiry(dto);
    }

    //1:1 문의 삭제
    //1:1 문의 수정
    //1:1 문의 읽기
    //1:1 문의리스트 읽기


}
