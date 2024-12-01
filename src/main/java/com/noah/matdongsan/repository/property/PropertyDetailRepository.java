package com.noah.matdongsan.repository.property;

import com.noah.matdongsan.entity.property.PropertyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDetailRepository extends JpaRepository<PropertyDetail,Long> {
}
