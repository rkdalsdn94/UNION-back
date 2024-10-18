package com.develop_ping.union.image.exception;

import lombok.Getter;

@Getter
public class UnsupportedFileFormatException extends RuntimeException{
    private final String extension;

    public UnsupportedFileFormatException(String extension){
        this.extension = extension;
    }
}
