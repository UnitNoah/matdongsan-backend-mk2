package com.noah.matdongsan.repository.notice;

import com.noah.matdongsan.dto.notice.NoticeReadDto;
import com.noah.matdongsan.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    @Query("SELECT new com.noah.matdongsan.dto.notice.NoticeReadDto(n.id, n.title) FROM Notice n")
    List<NoticeReadDto> getNotices();
}
