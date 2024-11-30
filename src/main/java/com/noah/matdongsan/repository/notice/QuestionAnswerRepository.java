package com.noah.matdongsan.repository.notice;

import com.noah.matdongsan.entity.notice.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {
}
