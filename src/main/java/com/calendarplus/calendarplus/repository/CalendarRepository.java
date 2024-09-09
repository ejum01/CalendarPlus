package com.calendarplus.calendarplus.repository;

import com.calendarplus.calendarplus.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 이벤트 관련 데이터베이스 작업을 처리하는 리포지토리입니다.
 *
 * @author : ejum
 * @since : 9/6/24
 */
@Repository
public interface CalendarRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.title = :title, e.description = :description, e.location = :location WHERE e.id = :id")
    int updateEventDetails(@Param("id") Long id,
                           @Param("title") String title,
                           @Param("description") String description,
                           @Param("location") String location);

}