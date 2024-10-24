package com.develop_ping.union.s3.exception;

import lombok.Getter;

@Getter
public class InvalidS3UrlException extends RuntimeException {
    private final String url;

    public InvalidS3UrlException(String url) {
        this.url = url;
    }
}
