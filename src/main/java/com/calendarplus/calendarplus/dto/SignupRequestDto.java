package com.calendarplus.calendarplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입에 필요한 Dto입니다.
 *
 * @author : ejum
 * @since : 9/4/24
 */
@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "사용자 이름은 필수 입력 사항입니다.")
    @Size(min = 3, max = 50, message = "사용자 이름은 3자 이상, 50자 이하로 입력하세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
    @Size(min = 3, message = "비밀번호는 최소 3자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String email;
}
