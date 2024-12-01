package com.noah.matdongsan.repository.user;

import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.user.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
}
