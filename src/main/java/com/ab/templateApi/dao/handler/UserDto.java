package com.ab.templateApi.dao.handler;

import com.ab.templateApi.dao.entity.User;
import com.ab.templateApi.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class UserDto {

    @JsonView(UserView.UserDto.class)
    private User user;
}
