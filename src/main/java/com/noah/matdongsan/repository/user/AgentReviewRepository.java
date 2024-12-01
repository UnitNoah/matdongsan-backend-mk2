package com.noah.matdongsan.repository.user;

import com.noah.matdongsan.entity.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentReviewRepository extends JpaRepository<AgentReviewRepository,Long> {
}
