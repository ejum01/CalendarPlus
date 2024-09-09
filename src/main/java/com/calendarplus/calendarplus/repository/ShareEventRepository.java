package com.calendarplus.calendarplus.repository;

import com.calendarplus.calendarplus.entity.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 클래스 입니다.
 *
 * @author : ejum
 * @since : 9/8/24
 */
@Repository
public interface ShareEventRepository extends JpaRepository<EventAttendee, Long> {

//    // 참석자 상태(partstat) 업데이트
//    @Modifying
//    @Transactional
//    @Query("UPDATE EventAttendee e SET e.partstat = :partstat WHERE e.event = :eventId AND e.email = :email")
//    void updatePartstat(@Param("eventId") Long eventId, @Param("email") String email, @Param("partstat") boolean partstat);

    @Modifying
    @Transactional
    @Query("UPDATE EventAttendee e SET e.partstat = :partstat WHERE e.event.id = :eventId AND e.email = :email")
    void updatePartstat(@Param("eventId") Long eventId, @Param("email") String email, @Param("partstat") boolean partstat);

}
