package com.noah.matdongsan.service.notice;

import com.noah.matdongsan.dto.notice.NoticeReadDto;
import com.noah.matdongsan.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<NoticeReadDto> getNoticeList() {
        return noticeRepository.getNotices();
    }
}
