package com.develop_ping.union.photo.presentation;

import com.develop_ping.union.photo.domain.dto.PhotoCommand;
import com.develop_ping.union.photo.domain.service.PhotoService;
import com.develop_ping.union.photo.presentation.dto.PhotoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping("/photo/save")
    public ResponseEntity<Void> savePhoto(@RequestBody PhotoRequest request) {
        log.info("[ CALL: PhotoController.savePhoto() ]");

        // TODO: @RequestHeader("Authorization")로 토큰 받기
        String token = "token";

        // TODO: command에 token 추가하기
        PhotoCommand command = request.toCommand();
        photoService.savePhotos(command);

        return ResponseEntity.ok().build();
    }
}
