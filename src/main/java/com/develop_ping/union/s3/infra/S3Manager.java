package com.develop_ping.union.s3.infra;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Manager {
    List<String> uploadImages(MultipartFile[] images, String token);
    void validateObjectExists(String url);
}
