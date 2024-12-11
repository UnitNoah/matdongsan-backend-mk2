package com.noah.matdongsan.service.notice;

import com.noah.matdongsan.dto.notice.NoticeReadDto;
import com.noah.matdongsan.dto.notice.PagedResponseDto;
import com.noah.matdongsan.entity.notice.Notice;
import com.noah.matdongsan.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public PagedResponseDto<NoticeReadDto> getNoticeList(int pageNo, String criteria, String keyword) {
        Sort.Direction direction = getSortDirection(criteria);
       final int PAGE_SIZE = 9;
        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE, Sort.by(direction, "createdDate"));

        Page<Notice> page = getNoticesByKeyword(keyword, pageable);

        List<NoticeReadDto> content = page.map(NoticeReadDto::from).getContent();

        return new PagedResponseDto<>(
                content,
                page.getNumber() + 1,
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }

    private Sort.Direction getSortDirection(String criteria) {
        if ("asc".equalsIgnoreCase(criteria)) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.DESC;
    }

    private Page<Notice> getNoticesByKeyword(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return noticeRepository.findByTitleContaining(keyword, pageable);
        } else {
            return noticeRepository.findAll(pageable);
        }
    }

}
