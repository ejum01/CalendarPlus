package com.calendarplus.calendarplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 이벤트를 나타내는 엔티티 클래스입니다.
 * 일정의 기본 정보와 주최자, 참석자 목록을 포함합니다.
 *
 * @author : ejum
 * @since : 9/5/24
 */
@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "event")
public class Event extends BaseEntity {
    @Column(name = "start", nullable = false)
    private LocalDateTime start;  // 시작 시간
    @Column(name = "end", nullable = true)
    private LocalDateTime end;  // 종료 시간
    @Column(name = "title", nullable = false)
    private String title; // 일정 제목
    @Column(name = "description", nullable = true)
    private String description;  // 일정 설명
    @Column(name = "location", nullable = true)
    private String location;  // 장소

    @Embedded
    private Organizer organizer;  // 주최자 정보

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private List<Attendee> attendees;  // 참석자 목록
}