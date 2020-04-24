package com.ab.templateApi.service;


import com.ab.templateApi.dao.entity.User;
import com.ab.templateApi.dao.handler.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findUserById(Long id);

    List<UserDto> findAllUsersDto();

    List<User> findAllUsersList();

    User findUserByEmail(String email);

    List<User> findUserByRole(String role);

    void deleteById(long id);

    User findUserIdByEmail(String email);
}
