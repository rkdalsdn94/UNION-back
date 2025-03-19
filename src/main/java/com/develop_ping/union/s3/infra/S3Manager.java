package com.develop_ping.union.s3.infra;

import com.develop_ping.union.user.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Manager {
    List<String> uploadImages(MultipartFile[] images, User user);
    void validateObjectExists(String url);
}
