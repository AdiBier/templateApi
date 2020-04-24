package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.User;
import com.ab.templateApi.dao.handler.UserDto;
import com.ab.templateApi.service.UserServiceImpl;
import com.ab.templateApi.view.UserView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApi {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserApi(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @JsonView(UserView.UserDto.class)
    @GetMapping("/users/all")
    public ResponseEntity<List<UserDto>> getAllUsersWithoutRelations() {
        return new ResponseEntity<>(userServiceImpl.findAllUsersDto(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/users")
    public ResponseEntity<User> deleteUserById(@RequestParam(name = "id") final long id) {
        final User user = userServiceImpl.findUserById(id);
        if (user != null) {
            userServiceImpl.deleteById(id);
        }
        return new ResponseEntity<>(user == null ? HttpStatus.NOT_FOUND : HttpStatus.NO_CONTENT);
    }

    @JsonView(UserView.WithRelations.class)
    @GetMapping("/users/allDetails")
    public ResponseEntity<List<User>> getAllUsersWithRelations() {
        return new ResponseEntity<>(userServiceImpl.findAllUsersList(), HttpStatus.OK);
    }

    @JsonView(UserView.WithRelations.class)
    @GetMapping("/users")
    public ResponseEntity<User> getUserById(@RequestParam(name = "id") final long id) {
        final User user = userServiceImpl.findUserById(id);
        return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @JsonView(UserView.WithUserId.class)
    @GetMapping("/userId")
    public ResponseEntity<User> getUserIdByEmail(@RequestParam(name = "email") final String email) {
        final User user = userServiceImpl.findUserIdByEmail(email);
        return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
