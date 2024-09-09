package com.calendarplus.calendarplus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * EventAttendee 클래스 입니다.
 *
 * @author : ejum
 * @since : 9/8/24
 */
@Entity
@Getter
@SuperBuilder
@Setter
@NoArgsConstructor
public class EventAttendee extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)  // 명시적으로 Event와의 관계를 정의
    @JsonIgnore  // 순환 참조 방지
    private Event event;  // Event 엔티티와의 관계 매핑

    private String email;

    private boolean partstat; // 참석 여부를 저장
}
