package com.develop_ping.union.photo.domain;

import com.develop_ping.union.photo.domain.entity.Photo;
import com.develop_ping.union.photo.domain.entity.TargetType;

import java.util.List;

public interface PhotoManager {
    List<Photo> savePhotos(Long targetId, TargetType targetType, List<String> urls);
}
