package com.noah.matdongsan.repository.property;

import com.noah.matdongsan.entity.property.PropertyPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto,Long> {
    List<PropertyPhoto> findByProperty_id(Long propertyId);
}
