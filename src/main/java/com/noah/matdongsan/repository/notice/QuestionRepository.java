package com.noah.matdongsan.repository.notice;

import com.noah.matdongsan.entity.notice.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
