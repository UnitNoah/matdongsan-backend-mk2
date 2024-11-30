package com.noah.matdongsan.repository.property;

import com.noah.matdongsan.entity.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {
}
