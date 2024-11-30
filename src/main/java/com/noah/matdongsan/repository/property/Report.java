package com.noah.matdongsan.repository.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Report extends JpaRepository<Report,Long> {
}
