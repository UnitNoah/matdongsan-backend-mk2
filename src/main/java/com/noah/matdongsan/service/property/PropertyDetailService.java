package com.noah.matdongsan.service.property;

import com.noah.matdongsan.dto.property.PropertyDetailDto;
import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.property.PropertyDetail;
import com.noah.matdongsan.repository.property.PropertyDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyDetailService {
    private final PropertyDetailRepository propertyDetailRepository;

    @Transactional
    public void create(PropertyDetailDto dto, Property property) {

        if (dto.getPdcontent() == null || dto.getPdcontent().isEmpty()) {
            throw new IllegalArgumentException("Content is required.");
        }

        PropertyDetail propertyDetail = dto.toEntity(property);
        propertyDetailRepository.save(propertyDetail);
    }

    public Optional<PropertyDetail> getDetailById(Long propertyId) {
        return propertyDetailRepository.findByProperty_Id(propertyId);
    }
}
