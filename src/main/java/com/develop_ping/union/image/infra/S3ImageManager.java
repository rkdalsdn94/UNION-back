package com.develop_ping.union.image.infra;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3ImageManager {
    List<String> uploadImages(MultipartFile[] images, String token);
}
