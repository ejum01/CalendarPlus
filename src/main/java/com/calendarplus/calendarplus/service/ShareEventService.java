package com.calendarplus.calendarplus.service;

import com.calendarplus.calendarplus.common.Constans;
import com.calendarplus.calendarplus.entity.Event;
import com.calendarplus.calendarplus.entity.EventAttendee;
import com.calendarplus.calendarplus.repository.CalendarRepository;
import com.calendarplus.calendarplus.repository.ShareEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 클래스 입니다.
 *
 * @author : ejum
 * @since : 9/8/24
 */
@Service
public class ShareEventService {

    private final JavaMailSender mailSender;

    private final ShareEventRepository shareEventRepository;

    private final CalendarRepository calendarRepository;

    @Autowired
    public ShareEventService(JavaMailSender mailSender, ShareEventRepository shareEventRepository, CalendarRepository calendarRepository) {
        this.mailSender = mailSender;
        this.shareEventRepository = shareEventRepository;
        this.calendarRepository = calendarRepository;
    }

    // 이메일 발송 메서드
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Transactional
    public void updateAttendeeResponse(Long eventId, String email, boolean partstat) {
        Optional<Event> event = calendarRepository.findById(eventId);
        if (event.isEmpty()) {
            throw new IllegalArgumentException(Constans.EVENT_NOT_FOUND);
        }

        shareEventRepository.updatePartstat(eventId, email, partstat);
    }


    public void saveAttendee(EventAttendee eventAttendee) {
        shareEventRepository.save(eventAttendee);
    }

}