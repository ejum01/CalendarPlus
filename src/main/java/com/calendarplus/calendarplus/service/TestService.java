package com.calendarplus.calendarplus.service;

import com.calendarplus.calendarplus.common.Constans;
import com.calendarplus.calendarplus.entity.Event;
import com.calendarplus.calendarplus.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 클래스 입니다.
 *
 * @author : ejum
 * @since : 9/8/24
 */
@Service
public class TestService {

    private final JavaMailSender mailSender;

    private final TestRepository testRepository;

    @Autowired
    public TestService(JavaMailSender mailSender, TestRepository testRepository) {
        this.mailSender = mailSender;
        this.testRepository = testRepository;
    }

    // 이메일 발송 메서드
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    // EventId로 이벤트를 찾는 메서드
    public Event getEventById(Long eventId) throws Exception {
        return testRepository.findById(eventId)
                .orElseThrow(() -> new Exception(Constans.EVENT_NOT_FOUND)).getEvent();
    }
}
