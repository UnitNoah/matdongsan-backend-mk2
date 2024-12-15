package com.noah.matdongsan.repository.notice;

import com.noah.matdongsan.dto.notice.NoticeReadDto;
import com.noah.matdongsan.entity.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    Page<Notice> findByTitleContaining(String keyword, Pageable pageable);
}
