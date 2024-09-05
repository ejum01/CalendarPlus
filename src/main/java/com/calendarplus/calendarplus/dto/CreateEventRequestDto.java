package com.calendarplus.calendarplus.dto;

import com.calendarplus.calendarplus.entity.Attendee;
import com.calendarplus.calendarplus.entity.Organizer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 이벤트 생성 요청에 필요한 데이터를 담는 DTO 클래스입니다.
 *
 * @author : ejum
 * @since : 9/6/24
 */
@Getter
@Builder
public class CreateEventRequestDto {
    private LocalDateTime start;  // 시작 시간
    private LocalDateTime end;    // 종료 시간
    private String title;         // 일정 제목
    private String description;   // 일정 설명
    private String location;      // 장소
    private Organizer organizer;  // 주최자 정보
    private List<Attendee> attendees;  // 참석자 목록
}