package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.User;
import com.ab.templateApi.dao.handler.UserDto;
import com.ab.templateApi.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Lazy
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(final Long id) {
        final Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<UserDto> findAllUsersDto() {
        final List<User> users = userRepository.findAll();
        final List<UserDto> usersDto = new LinkedList<>();
        UserDto userDto;
        for (var user : users) {
            userDto = new UserDto();
            userDto.setUser(user);
            usersDto.add(userDto);
        }
        return usersDto;
    }

    @Override
    public List<User> findAllUsersList() {
        final List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User findUserByEmail(final String email) {
        final Optional<User> user = userRepository.findUserByEmail(email);
        return user.orElse(null);
    }

    @Override
    public List<User> findUserByRole(String role) {
        return userRepository.findUserByRole(role);
    }

    @Override
    public void deleteById(final long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserIdByEmail(String email) {
        final Optional<User> user = userRepository.findUserByEmail(email);
        user.get().getUserId();
        return user.orElse(null);
    }

    public User registerUser(User newUser) {
        User addUser = new User(newUser.getName(), newUser.getSurname(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()), newUser.getPhone(), "CUSTOMER");
        return userRepository.save(addUser);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) throw new UsernameNotFoundException("Invalid username or password");
        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.get().getRole().toString())));
    }
}
