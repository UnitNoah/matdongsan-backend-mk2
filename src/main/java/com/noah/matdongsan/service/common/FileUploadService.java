package com.noah.matdongsan.service.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Transactional
    public String uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return null; // 값이 없으면 null 반환
        }

        String fileName = file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        // 사진 업데이트 기능 임시 주석처리
        //amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
        return "https://" + bucket + "/" + fileName;
    }

    @Transactional
    public List<String> uploadFiles(List<MultipartFile> files) {
        return files.parallelStream()
                .filter(file -> file != null && !file.isEmpty()) // 빈 파일 필터링
                .map(file -> {
                    try {
                        return uploadFile(file); // 기존의 단일 파일 업로드 메서드 호출
                    } catch (Exception e) {
                        System.err.println("파일 업로드 실패: " + file.getOriginalFilename());
                        return null; // 실패한 파일은 null로 반환
                    }
                })
                .filter(Objects::nonNull) // 실패한 파일 제거
                .toList(); // 결과를 리스트로 변환
    }
}

