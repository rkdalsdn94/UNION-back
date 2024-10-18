package com.develop_ping.union.image.exception;

import lombok.Getter;

@Getter
public class ImageUploadFailedException extends RuntimeException {
    private final String fileName;

    public ImageUploadFailedException(String fileName) {
        this.fileName = fileName;
    }
}
