package com.calendarplus.calendarplus.repository;

import com.calendarplus.calendarplus.entity.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 클래스 입니다.
 *
 * @author : ejum
 * @since : 9/8/24
 */
@Repository
public interface TestRepository extends JpaRepository<EventAttendee, Long> {

    EventAttendee findByEventIdAndEmail(Long eventId, String email);

}
