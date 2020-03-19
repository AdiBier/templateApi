package com.ab.templateApi.dao.handler;

import com.ab.templateApi.dao.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {

    private Long userId;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String phone;

    private String role;

    private String msg;

    private String code;

    public UserDto(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public static UserDto toUserDto(User user, String msg, String code) {
        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .role(user.getRole())
                .msg(msg)
                .code(code)
                .build();
    }
}
