package com.calendarplus.calendarplus.service;

import com.calendarplus.calendarplus.common.Constans;
import com.calendarplus.calendarplus.dto.CreateEventRequestDto;
import com.calendarplus.calendarplus.dto.UpdateEventDto;
import com.calendarplus.calendarplus.entity.Event;
import com.calendarplus.calendarplus.entity.User;
import com.calendarplus.calendarplus.exception.UserNotFoundException;
import com.calendarplus.calendarplus.repository.CalendarRepository;
import com.calendarplus.calendarplus.repository.TestRepository;
import com.calendarplus.calendarplus.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 이벤트와 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 *
 * @author : ejum
 * @since : 9/6/24
 */
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    private final TestRepository testRepository;

    public CalendarService(CalendarRepository calendarRepository, UserRepository userRepository, TestRepository testRepository) {
        this.calendarRepository = calendarRepository;
        this.userRepository = userRepository;
        this.testRepository = testRepository;
    }

    /**
     * 새로운 이벤트를 생성하는 메서드입니다.
     * <p>
     * 이벤트 요청 데이터를 검증한 후, 이벤트를 데이터베이스에 저장합니다.
     * </p>
     *
     * @param eventRequestDto 이벤트 생성 요청 데이터
     */
    public void createEvent(CreateEventRequestDto eventRequestDto) {

        validateEventRequest(eventRequestDto);

        Event event = Event.builder()
                .start(eventRequestDto.getStart())
                .end(eventRequestDto.getEnd())
                .location(eventRequestDto.getLocation())
                .title(eventRequestDto.getTitle())
                .description(eventRequestDto.getDescription())
                .organizer(eventRequestDto.getOrganizer())
                .build();

        calendarRepository.save(event);
    }

    /**
     * 이벤트 요청 데이터의 유효성을 검증하는 메서드입니다.
     * <p>
     * 주최자 이메일과 사용자 이름을 확인하여 유효하지 않은 경우 예외를 발생시킵니다.
     * </p>
     *
     * @param eventRequestDto 이벤트 생성 요청 데이터
     */
    private void validateEventRequest(CreateEventRequestDto eventRequestDto) {

        User user = User.builder()
                .username(eventRequestDto.getOrganizer().getDisplayName())
                .email(eventRequestDto.getOrganizer().getEmail())
                .build();
        // 주최자 이메일이 유효한지 확인
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException(Constans.INVALID_ORGANIZER_EMAIL);
        }
        // 주최자 이름이 유효한지 확인
        if (!userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException(Constans.INVALID_ORGANIZER_USERNAME);
        }
    }

    /**
     * 특정 사용자의 일정을 조회하는 메서드입니다.
     * <p>
     * 사용자의 이메일을 기반으로 관련된 이벤트를 조회하여 반환합니다.
     * </p>
     *
     * @param username 조회할 사용자의 사용자 이름
     * @return 해당 사용자의 일정 목록
     * @throws UserNotFoundException 사용자를 찾을 수 없는 경우 발생
     */
    public List<Event> getEventsForUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(Constans.USER_NOR_FOUND);
        }
        return calendarRepository.findByOrganizerEmail(user.getEmail());
    }

    /**
     * 이벤트를 업데이트하는 메서드입니다.
     * <p>
     * 주어진 ID에 해당하는 이벤트를 찾아서 제목, 설명, 장소를 업데이트합니다.
     * </p>
     *
     * @param updateEventDto 업데이트할 이벤트의 정보가 담긴 DTO
     * @throws IllegalArgumentException 이벤트를 찾을 수 없는 경우 발생
     */
    @Transactional
    public void updateEvent(UpdateEventDto updateEventDto) {
        int rowsUpdated = calendarRepository.updateEventDetails(
                updateEventDto.getId(),
                updateEventDto.getTitle(),
                updateEventDto.getDescription(),
                updateEventDto.getLocation()
        );
        if (rowsUpdated == 0) {
            throw new IllegalArgumentException(Constans.EVENT_NOT_FOUND);
        }
    }

    /**
     * 이벤트를 삭제하는 메서드입니다.
     * <p>
     * 주어진 ID에 해당하는 이벤트를 삭제합니다.
     * </p>
     *
     * @param id 삭제할 이벤트의 ID
     * @throws IllegalArgumentException 이벤트를 찾을 수 없는 경우 발생
     */
    @Transactional
    public void deleteEvent(Long id) {
        if (!calendarRepository.existsById(id)) {
            throw new IllegalArgumentException(Constans.EVENT_NOT_FOUND);
        }
        calendarRepository.deleteById(id);
    }

    public Event getEventById(Long eventId) throws Exception {
        return calendarRepository.findById(eventId)
                .orElseThrow(() -> new Exception(Constans.EVENT_NOT_FOUND));
    }


}