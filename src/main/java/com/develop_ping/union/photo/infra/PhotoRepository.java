package com.develop_ping.union.photo.infra;

import com.develop_ping.union.photo.domain.entity.Photo;
import com.develop_ping.union.photo.domain.entity.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByTargetIdAndTargetType(Long targetId, TargetType targetType);
}
