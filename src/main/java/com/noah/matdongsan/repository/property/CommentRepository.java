package com.noah.matdongsan.repository.property;

import com.noah.matdongsan.entity.property.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
