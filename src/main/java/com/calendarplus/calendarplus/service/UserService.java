package com.calendarplus.calendarplus.service;

import com.calendarplus.calendarplus.dto.SignupRequestDto;
import com.calendarplus.calendarplus.entity.User;
import com.calendarplus.calendarplus.exception.UserAlreadyExistException;
import com.calendarplus.calendarplus.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * UserService 클래스입니다.
 *
 * @author : ejum
 * @since : 9/5/24
 */
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(SignupRequestDto signupRequest) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new UserAlreadyExistException();
        }

        User user = User.builder()
                .username(signupRequest.getUsername())
                .password(signupRequest.getPassword())
                .email(signupRequest.getEmail())
                .build();

        userRepository.save(user);
    }
}