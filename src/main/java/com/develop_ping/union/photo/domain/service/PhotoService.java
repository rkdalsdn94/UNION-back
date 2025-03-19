package com.develop_ping.union.photo.domain.service;

import com.develop_ping.union.photo.domain.dto.PhotoCommand;

public interface PhotoService {
    void savePhotos(PhotoCommand command);
}
