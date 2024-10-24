package com.develop_ping.union.photo.domain.entity;

import com.develop_ping.union.common.base.AuditingFields;
import com.develop_ping.union.photo.domain.dto.PhotoCommand;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "photos")
public class Photo extends AuditingFields {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long targetId;

    @Column(nullable = false)
    private TargetType targetType;

    @Column(nullable = false)
    private String url;

    @Builder
    public Photo(Long targetId, TargetType targetType, String url) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Photo[ " +
                "id= " + this.getId() +
                ", targetId= " + this.getTargetId() +
                ", targetType= " + this.getTargetType() +
                ", url= " + this.getUrl() +
                " ]\n";
    }
}
