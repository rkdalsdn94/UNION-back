package com.develop_ping.union.photo.infra;

import com.develop_ping.union.photo.domain.PhotoManager;
import com.develop_ping.union.photo.domain.entity.Photo;
import com.develop_ping.union.photo.domain.entity.TargetType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PhotoManagerImpl implements PhotoManager {
    private final PhotoRepository photoRepository;

    @Override
    @Transactional
    public List<Photo> savePhotos(Long targetId, TargetType targetType, List<String> urls) {
        List<Photo> photos = urls.stream()
                .map(url -> Photo.builder()
                        .targetId(targetId)
                        .targetType(targetType)
                        .url(url)
                        .build())
                .collect(Collectors.toList());

        return photoRepository.saveAll(photos);
    }

    @Override
    public List<String> findPostPhotos(Long targetId) {
        TargetType targetType = TargetType.POST;

        return photoRepository.findByTargetIdAndTargetType(targetId, targetType)
                .stream()
                .map(Photo::getUrl)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findGatheringPhotos(Long targetId) {
        TargetType targetType = TargetType.GATHERING;

        return photoRepository.findByTargetIdAndTargetType(targetId, targetType)
                .stream()
                .map(Photo::getUrl)
                .collect(Collectors.toList());
    }
}
