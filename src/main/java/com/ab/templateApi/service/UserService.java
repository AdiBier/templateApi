package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.User;
import com.ab.templateApi.dao.handler.UserDto;
import com.ab.templateApi.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User findUserByPhone(String phone){
        return userRepository.findUserByPhone(phone);
    }

    public List<User> findUserByRole(String role) {
        return userRepository.findUserByRole(role);
    }

    public User save(User newUser){
        User user = new User(newUser.getName(), newUser.getSurname(), newUser.getEmail(), newUser.getPassword(), newUser.getPhone(), "CUSTOMER");
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        save(new User("Max", "Maxlore", "maxlore@gmail.com", "customer", "798963369", "CUSTOMER"));
        save(new User("Juliusz", "Herbatnikowy", "easy@gmail.com", "admin", "852789951", "ADMIN"));
    }
}
