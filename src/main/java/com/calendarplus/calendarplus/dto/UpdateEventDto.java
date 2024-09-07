package com.calendarplus.calendarplus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 이벤트 업데이트에 필요한 dto 클래스 입니다.
 *
 * @author : ejum
 * @since : 9/6/24
 */
@Getter
@Setter
public class UpdateEventDto {
    private Long id;
    private String title;         // 일정 제목
    private String description;   // 일정 설명
    private String location;      // 장소
}
