package com.calendarplus.calendarplus.controller;

import com.calendarplus.calendarplus.entity.Event;
import com.calendarplus.calendarplus.entity.EventAttendee;
import com.calendarplus.calendarplus.repository.CalendarRepository;
import com.calendarplus.calendarplus.repository.TestRepository;
import com.calendarplus.calendarplus.service.CalendarService;
import com.calendarplus.calendarplus.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author : ejum
 * @since : 9/8/24
 */
@Slf4j
@Controller
public class TestController {

    private final TestService testService;

    private final TestRepository testRepository;

    private final CalendarService calendarService;

    public TestController(TestService testService, TestRepository testRepository, CalendarRepository calendarRepository, CalendarService calendarService) {
        this.testService = testService;
        this.testRepository = testRepository;
        this.calendarService = calendarService;
    }

    @PostMapping("/{eventId}/share")
    public ResponseEntity<String> shareEvent(
            @PathVariable Long eventId,
            @RequestParam List<String> emails) {
        try {
            log.info("Request event ID: {}", eventId);
            log.info("Emails to share with: {}", emails);

            Event event = calendarService.getEventById(eventId);

            for (String email : emails) {

                EventAttendee eventAttendee = EventAttendee.builder()
                        .event(event)
                        .email(email)
                        .build();

                testService.saveAttendee(eventAttendee); // 서비스에서 처리

                String subject = "이벤트 초대: " + event.getTitle();
                String body = "다음 이벤트에 초대되었습니다: " + event.getTitle() +
                        "\n설명: " + event.getDescription() +
                        "\n날짜: " + event.getStart() +
                        "\n참석을 확인하려면 '예'를 클릭하세요: " +
                        "http://localhost:8080/events/attend?eventId=" + eventId + "&email=" + email + "&response=yes" +
                        "\n참석이 불가능하다면 '아니오'를 클릭하세요: " +
                        "http://localhost:8080/events/attend?eventId=" + eventId + "&email=" + email + "&response=no";

                testService.sendEmail(email, subject, body); // 이메일 서비스에서 처리
            }

            return ResponseEntity.ok("이벤트가 성공적으로 공유되었습니다.");
        } catch (Exception e) {
            log.error("Error in sharing event", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이벤트를 공유하는 데 실패했습니다.");
        }
    }

    @PostMapping("/events/attend")
    public ResponseEntity<String> attendEvent(
            @RequestParam Long eventId,
            @RequestParam String email,
            @RequestParam String response) {
        try {
            boolean partstat = response.equalsIgnoreCase("yes");
            testService.updateAttendeeResponse(eventId, email, partstat);
            return ResponseEntity.ok("참석 응답이 성공적으로 기록되었습니다.");
        } catch (Exception e) {
            log.error("Error in recording attendance response", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("참석 응답을 기록하는 데 실패했습니다.");
        }
    }

}
