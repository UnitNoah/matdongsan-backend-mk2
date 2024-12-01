package com.noah.matdongsan.repository.property;

import com.noah.matdongsan.entity.property.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
}
