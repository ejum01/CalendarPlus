package com.calendarplus.calendarplus.controller;

import com.calendarplus.calendarplus.dto.SignupRequestDto;
import com.calendarplus.calendarplus.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController 입니다.
 *
 * @author : ejum
 * @since : 9/4/24
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto signupRequest) {
        // 회원가입 로직 처리
        try {
            userService.save(signupRequest);
            return ResponseEntity.status(200).body("회원가입이 성공했습니다.");
        } catch (Exception e) {
            log.error("EEE: UserController.signup {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }


}