package com.noah.matdongsan.notice;

import com.noah.matdongsan.entity.notice.Notice;
import com.noah.matdongsan.repository.notice.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // JPA 관련 테스트를 위한 어노테이션
class NoticeRepositoryTest {

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    @DisplayName("Notice 저장 테스트")
    void saveNoticeTest() {
        // Given
        Notice notice = Notice.builder()
                .title("Test Title")
                .content("Test Content")
                .build();

        // When
        Notice savedNotice = noticeRepository.save(notice);

        // Then
        assertThat(savedNotice).isNotNull();
        assertThat(savedNotice.getId()).isNotNull();
        assertThat(savedNotice.getTitle()).isEqualTo("Test Title");
        assertThat(savedNotice.getContent()).isEqualTo("Test Content");
    }

    @Test
    @DisplayName("Notice 조회 테스트")
    void findNoticeTest() {
        // Given
        Notice notice = Notice.builder()
                .title("Test Title")
                .content("Test Content")
                .build();
        Notice savedNotice = noticeRepository.save(notice);

        // When
        Optional<Notice> foundNotice = noticeRepository.findById(savedNotice.getId());

        // Then
        assertThat(foundNotice).isPresent();
        assertThat(foundNotice.get().getTitle()).isEqualTo("Test Title");
        assertThat(foundNotice.get().getContent()).isEqualTo("Test Content");
    }

    @Test
    @DisplayName("Notice 수정 테스트")
    void updateNoticeTest() {
        // Given
        Notice notice = Notice.builder()
                .title("Old Title")
                .content("Old Content")
                .build();
        Notice savedNotice = noticeRepository.save(notice);

        // When
        savedNotice = Notice.builder()
                .title("Updated Title")
                .content("Updated Content")
                .build();
        Notice updatedNotice = noticeRepository.save(savedNotice);

        // Then
        assertThat(updatedNotice.getTitle()).isEqualTo("Updated Title");
        assertThat(updatedNotice.getContent()).isEqualTo("Updated Content");
    }

    @Test
    @DisplayName("Notice 삭제 테스트")
    void deleteNoticeTest() {
        // Given
        Notice notice = Notice.builder()
                .title("Test Title")
                .content("Test Content")
                .build();
        Notice savedNotice = noticeRepository.save(notice);

        // When
        noticeRepository.deleteById(savedNotice.getId());
        Optional<Notice> deletedNotice = noticeRepository.findById(savedNotice.getId());

        // Then
        assertThat(deletedNotice).isNotPresent();
    }
}
