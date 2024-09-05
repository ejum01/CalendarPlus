package com.calendarplus.calendarplus.controller;

import com.calendarplus.calendarplus.common.Constans;
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
 * UserController는 사용자 관련 API 요청을 처리하는 컨트롤러입니다.
 * 이 클래스는 회원가입과 같은 사용자 관련 작업을 관리합니다.
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

    /**
     * 회원가입 요청을 처리하는 메서드입니다.
     * 클라이언트로부터 받은 회원가입 정보를 기반으로 사용자를 등록합니다.
     * <p>
     * 요청 URI: /api/user/signup
     * 요청 타입: POST
     * 요청 본문: JSON 형식의 회원가입 데이터 (SignupRequestDto에 해당하는 데이터)
     *
     * @param signupRequest 회원가입 요청 데이터가 담긴 DTO 객체
     * @return 회원가입 성공 메시지를 담은 응답
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto signupRequest) {
        // 회원가입 로직
        userService.save(signupRequest);
        // 회원가입 성공 메시지를 응답으로 반환
        return ResponseEntity.status(200).body(Constans.SIGNUP_SUCCESS);
    }
}