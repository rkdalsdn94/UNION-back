package com.develop_ping.union.image.controller;

import com.develop_ping.union.image.infra.S3ImageManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final S3ImageManager s3ImageManager;

    @PostMapping("/image")
    public ResponseEntity<List<String>> upload(@RequestParam("images") MultipartFile[] images) {
        log.info("CALL: ImageController.upload");

        // TODO: requestHeader로 token 받아오기
        String token = "userToken";

        return ResponseEntity.ok(s3ImageManager.uploadImages(images, token));
    }
}
