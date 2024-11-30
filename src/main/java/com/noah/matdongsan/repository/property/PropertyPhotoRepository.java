package com.noah.matdongsan.repository.property;

import com.noah.matdongsan.entity.property.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto,Long> {
}
