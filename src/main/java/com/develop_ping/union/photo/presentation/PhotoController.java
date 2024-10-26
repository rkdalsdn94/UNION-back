package com.develop_ping.union.photo.presentation;

import com.develop_ping.union.photo.domain.dto.PhotoCommand;
import com.develop_ping.union.photo.domain.service.PhotoService;
import com.develop_ping.union.photo.presentation.dto.PhotoRequest;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping("/photo/save")
    public ResponseEntity<Void> savePhoto(@RequestBody PhotoRequest request,
                                          @AuthenticationPrincipal User user) {
        log.info("[ CALL: PhotoController.savePhoto() ]");

        PhotoCommand command = request.toCommand(user);
        photoService.savePhotos(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
