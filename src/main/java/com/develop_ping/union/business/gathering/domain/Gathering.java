package com.develop_ping.union.business.gathering.domain;

import com.develop_ping.union.common.base.AuditingFields;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "gatherings")
public class Gathering extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "max_member", nullable = false)
    private Integer maxMember;

    @Column(name = "gathering_date_time", nullable = false)
    private ZonedDateTime gatheringDateTime;

    @Builder
    private Gathering(String token, String title, String content, Integer maxMember, ZonedDateTime gatheringDateTime) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.gatheringDateTime = gatheringDateTime;
    }
}
