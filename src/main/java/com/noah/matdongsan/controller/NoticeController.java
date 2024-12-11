package com.noah.matdongsan.controller;

import com.noah.matdongsan.dto.notice.NoticeReadDto;
import com.noah.matdongsan.dto.notice.PagedResponseDto;
import com.noah.matdongsan.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<PagedResponseDto<NoticeReadDto>> getNoticesList(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam( name = "criteria" , required = false, defaultValue = "createdDate") String criteria,
            @RequestParam( name = "keyword" , required = false, defaultValue = "") String keyword)
    {

        PagedResponseDto<NoticeReadDto> notices = noticeService.getNoticeList(pageNo, criteria, keyword);
        return ResponseEntity.ok(notices);
    }

    //admin만 작성하도록 해야함
    //공지사항 생성
    //공지사항 디테일 읽기
    //공지사항 삭제
    //공지사항 수정

    //이메일 받아야함
    //1:1 문의 생성
    //1:1 문의 삭제
    //1:1 문의 수정
    //1:1 문의 읽기
    //1:1 문의리스트 읽기


}
