package com.develop_ping.union.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.develop_ping.union.common.exception.UnsupportedFileFormatException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3ImageManager {
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList(
            ".jpg", ".jpeg", ".png", ".gif", ".webp", ".svg"
    );

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    // 이미지 1개 업로드
    public String uploadImage(MultipartFile file, String userToken) throws IOException {
        // 파일 확장자 추출
        String originalFileName = file.getOriginalFilename();
        String fileName = createFileName(originalFileName);

        // 유저별 폴더 경로 설정
        String userFolder = userToken + "/";
        String filePath = userFolder + fileName;

        // 메타데이터 설정 (파일의 크기, 타입 등)
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // S3에 파일 업로드
        amazonS3Client.putObject(bucketName, filePath, file.getInputStream(), metadata);

        // 이미지 url 반환
        return amazonS3Client.getUrl(bucketName, filePath).toString();
    }

    // 이미지 여러 개 업로드
    public List<String> uploadImages(MultipartFile[] files, String userToken) throws IOException {
        List<String> uploadedImageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String uploadedImageUrl = uploadImage(file, userToken);
            uploadedImageUrls.add(uploadedImageUrl);
        }

        // 이미지 url 리스트 반환
        return uploadedImageUrls;
    }

    private String createFileName(String originalFileName) {
        // 파일 확장자 추출
        String extension = extractExtension(originalFileName);

        // 확장자 검증
        if (!isSupportedExtension(extension)) {
            throw new UnsupportedFileFormatException(extension);
        }

        return UUID.randomUUID().toString() + extension;
    }

    private String extractExtension(String originalFileName) {
        try {
            return originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();  // 확장자를 소문자로 변환
        } catch (StringIndexOutOfBoundsException e) {
            // 확장자가 없는 경우 에러 발생
            throw new UnsupportedFileFormatException("null");
        }
    }

    private boolean isSupportedExtension(String extension) {
        return SUPPORTED_EXTENSIONS.contains(extension);
    }

    public void delete(String fileUrl) {
        // url에서 파일 이름 추출
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

        amazonS3Client.deleteObject(bucketName, fileName);
    }
}
