package com.calendarplus.calendarplus.controller;

import com.calendarplus.calendarplus.common.Constans;
import com.calendarplus.calendarplus.dto.CreateEventRequestDto;
import com.calendarplus.calendarplus.dto.UpdateEventDto;
import com.calendarplus.calendarplus.entity.Event;
import com.calendarplus.calendarplus.service.CalendarService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * * CalendarController 클래스는 일정 관련 요청을 처리하는 REST 컨트롤러입니다.
 *
 * @author : ejum
 * @since : 9/5/24
 */
@RestController
@RequestMapping("/api/calendar")
@Slf4j
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    /**
     * 특정 사용자의 일정을 조회하는 메서드입니다.
     * <p>
     * 요청 URI: /api/calendar/{username}
     * 요청 타입: GET
     * 요청 경로 변수: {username} - 조회할 사용자의 사용자 이름
     *
     * @param username 조회할 사용자의 사용자 이름
     * @return 사용자 일정 목록을 담은 응답 (200 OK)
     */
    @GetMapping("/{username}")
    public ResponseEntity<List<Event>> getUserCalendar(@PathVariable String username) {
        List<Event> events = calendarService.getEventsForUser(username);
        return ResponseEntity.status(200).body(events);
    }

    /**
     * 새로운 일정을 생성하는 메서드입니다.
     * <p>
     * 요청 URI: /api/calendar/events
     * 요청 타입: POST
     * 요청 본문: JSON 형식의 일정 데이터 (CreateEventRequestDto에 해당하는 데이터)
     *
     * @param eventRequestDto 생성할 일정 데이터가 담긴 DTO 객체
     * @return 일정 생성 성공 메시지를 담은 응답 (201 Created)
     */
    @PostMapping("/events")
    public ResponseEntity<String> createEvent(@RequestBody @Valid CreateEventRequestDto eventRequestDto) {
        calendarService.createEvent(eventRequestDto);
        return ResponseEntity.status(201).body(Constans.EVENT_CREATION_SUCCESS);
    }

    /**
     * 기존에 있던 일정을 수정하는 메서드입니다.
     * <p>
     * 요청 URI: /api/calendar/events
     * 요청 타입: Patch
     * 요청 본문: JSON 형식의 일정 데이터 (updateEventDto 해당하는 데이터)
     * put 대신 patchMapping을 사용한 이유
     * put : 리소스의 모든 것을 업데이트 한다.
     * patch : 리소스의 일부를 업데이트 하기 때문에 patch를 사용
     *
     * @param updateEventDto 수정할 일정 데이터가 담긴 DTO 객체
     * @return 일정 생성 성공 메시지를 담은 응답 (200 Ok)
     */
    @PatchMapping("/events")
    public ResponseEntity<String> updateEvent(@RequestBody UpdateEventDto updateEventDto) {
        calendarService.updateEvent(updateEventDto);
        return ResponseEntity.status(200).body(Constans.EVENT_UPDATE_SUCCESS);
    }

    /**
     * 기존에 있던 일정을 삭제 메서드입니다.
     * <p>
     * 요청 URI: /api/calendar/events/{id}
     * 요청 타입: delete
     * 요청 본문 ex: http://localhost:8080/api/calendar/events/1
     *
     * @param id 삭제할 이벤트의 ID
     * @return 일정 생성 성공 메시지를 담은 응답 (204 No Content)
     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        calendarService.deleteEvent(id);
        return ResponseEntity.status(204).body(Constans.EVENT_DELETE_SUCCESS);
    }

}