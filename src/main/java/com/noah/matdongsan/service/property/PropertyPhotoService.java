package com.noah.matdongsan.service.property;

import com.noah.matdongsan.entity.property.Property;
import com.noah.matdongsan.entity.property.PropertyPhoto;
import com.noah.matdongsan.repository.property.PropertyPhotoRepository;
import com.noah.matdongsan.service.common.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyPhotoService {
    private final FileUploadService fileUploadService;
    private final PropertyPhotoRepository propertyPhotoRepository;

    public void create(List<MultipartFile> photos, Property property) {
        if (photos == null || photos.isEmpty()) {
            throw new IllegalArgumentException("Photos list must not be empty.");
        }

        List<String> photosUrl = fileUploadService.uploadFiles(photos);

        List<PropertyPhoto> propertyPhotos = photosUrl.stream()
                .map(url -> new PropertyPhoto(url, property))
                .toList();

        propertyPhotoRepository.saveAll(propertyPhotos);
    }

}
