package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.User;
import com.ab.templateApi.dao.handler.UserDto;
import com.ab.templateApi.exception.UserNotFoundException;
import com.ab.templateApi.response.ServerResponse;
import com.ab.templateApi.response.ServerResponseConstants;
import com.ab.templateApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    @Autowired
    public UserApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Iterable<UserDto> getAllUsers() {
        Iterable<User> userIterable = userService.findAll();
        if (userIterable.iterator().hasNext()) {
            return StreamSupport.stream(userIterable.spliterator(), false)
                    .map(users -> {
                        return new UserDto(users.getUserId(), users.getName(), users.getSurname(), users.getEmail(), users.getPassword(), users.getPhone(), users.getRole(),
                                ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
                    }).collect(toList());
        }
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(new UserDto(null, null, null, null, null, null, null, ServerResponseConstants.TAE1001_MSG, ServerResponseConstants.TAE1001_CODE));
        return userDtoList;
    }

    @GetMapping
    public Optional<User> getById(@RequestParam Long id) {
        return Optional.ofNullable(userService.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping
    public ServerResponse deleteUserById(@RequestParam Long id) {
        if (userService.findById(id).isPresent()) {
            userService.delete(id);
            return new ServerResponse(ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new ServerResponse(ServerResponseConstants.TAE1001_MSG, ServerResponseConstants.TAE1001_CODE);
    }
}
