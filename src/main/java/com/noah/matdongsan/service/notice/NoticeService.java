package com.noah.matdongsan.service.notice;

import com.noah.matdongsan.dto.notice.NoticeDetailReadDto;
import com.noah.matdongsan.dto.notice.NoticeReadDto;
import com.noah.matdongsan.dto.notice.NoticeUpdateDto;
import com.noah.matdongsan.dto.notice.PagedResponseDto;
import com.noah.matdongsan.entity.notice.Notice;
import com.noah.matdongsan.repository.notice.NoticeRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public NoticeDetailReadDto getDetailPageById(Long id) {
        return noticeRepository.findById(id)
                .map(NoticeDetailReadDto::from)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public void updateNoticeDetailPage(NoticeUpdateDto noticeUpdateDto) {
        log.info(noticeUpdateDto.getId() + "");
        Notice notice = noticeRepository.findById(noticeUpdateDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("공지사항을 찾을 수 없습니다. ID: " + noticeUpdateDto.getId()));

        notice.update(noticeUpdateDto.getTitle(), noticeUpdateDto.getContent());
        log.info(notice.getContent());
        // noticeRepository.save(notice);
    }

    @Transactional
    public void deleteNoticePageById(Long id) {
        if (!noticeRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 공지사항이 존재하지 않습니다. ID: " + id);
        }
        noticeRepository.deleteById(id);
    }
}
