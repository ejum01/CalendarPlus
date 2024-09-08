package com.calendarplus.calendarplus.controller;

import com.calendarplus.calendarplus.entity.Event;
import com.calendarplus.calendarplus.entity.EventAttendee;
import com.calendarplus.calendarplus.repository.TestRepository;
import com.calendarplus.calendarplus.service.TestService;
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
@Controller
public class TestController {

    private final TestService testService;

    private final TestRepository testRepository;

    public TestController(TestService testService, TestRepository testRepository) {
        this.testService = testService;
        this.testRepository = testRepository;
    }

    @PostMapping("/{eventId}/share")
    public ResponseEntity<String> shareEvent(
            @PathVariable Long eventId,
            @RequestParam List<String> emails) {
        try {
            Event event = testService.getEventById(eventId);

            for (String email : emails) {
                // 참석자 정보를 DB에 저장
                EventAttendee attendee = new EventAttendee();
                attendee.setEmail(email);
                attendee.setEvent(event);
                testRepository.save(attendee);

                // 참석자에게 이메일 발송
                String subject = "이벤트 초대: " + event.getTitle();
                String body = "다음 이벤트에 초대되었습니다: " + event.getTitle() +
                        "\n설명: " + event.getDescription() +
                        "\n날짜: " + event.getStart() +
                        "\n참석을 확인하려면 '예'를 클릭하세요: " +
                        "http://localhost:8080/events/attend?eventId=" + eventId + "&email=" + email + "&response=yes" +
                        "\n참석이 불가능하다면 '아니오'를 클릭하세요: " +
                        "http://localhost:8080/events/attend?eventId=" + eventId + "&email=" + email + "&response=no";
                testService.sendEmail(email, subject, body);
            }

            return ResponseEntity.ok("이벤트가 성공적으로 공유되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이벤트를 찾을 수 없습니다.");
        }
    }

    @PostMapping("/events/attend")
    public ResponseEntity<String> attendEvent(
            @RequestParam Long eventId,
            @RequestParam String email,
            @RequestParam String response) {
        EventAttendee attendee = testRepository.findByEventIdAndEmail(eventId, email);
        if (attendee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendee not found");
        }

        if ("yes".equalsIgnoreCase(response)) {
            attendee.setPartstat(true);
        } else {
            attendee.setPartstat(false);
        }

        testRepository.save(attendee);
        return ResponseEntity.ok("Attendance response recorded successfully");
    }


}
