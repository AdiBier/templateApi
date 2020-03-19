package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.User;
import com.ab.templateApi.dao.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    private final Long USER_ID = 1L;

    @Before
    public void setUp() {
        user = new User(USER_ID, "Julia", "Kittoki", "Kittoki@gmail.com", "Kitto123", "789355718", "CUSTOMER");
    }

    //findAll BEGINS
    @Test
    public void shouldReturnFindAllUserOK() {
        //given
        List<User> userListOK = prepareUsers();
        when(userRepository.findAll()).thenReturn(userListOK);

        //when
        Iterable<User> foundAllOK = userService.findAll();

        //then
        assertEquals(foundAllOK, userListOK);
    }

    @Test
    public void shouldReturnFindAllUserERR() {
        //given
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        //when
        Iterable<User> foundAllERR = userService.findAll();

        //then
        assertEquals(foundAllERR, Collections.EMPTY_LIST);
    }
    //ENDS

    @Test
    public void shouldReturnFindUserById() {
        //given
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));

        //when
        Optional<User> foundUserByIdOK = userService.findById(1L);

        //then
        assertEquals(foundUserByIdOK.get().getUserId(), user.getUserId());
        assertEquals(foundUserByIdOK.get().getName(), user.getName());
        assertEquals(foundUserByIdOK.get().getSurname(), user.getSurname());
        assertEquals(foundUserByIdOK.get().getEmail(), user.getEmail());
        assertEquals(foundUserByIdOK.get().getPassword(), user.getPassword());
        assertEquals(foundUserByIdOK.get().getPhone(), user.getPhone());
        assertEquals(foundUserByIdOK.get().getRole(), user.getRole());
    }

    //findUserByEmail BEGINS
    @Test
    public void shouldReturnFindUserByEmailOK() {
        //given
        when(userRepository.findUserByEmail("Kittoki@gmail.com")).thenReturn(user);

        //when
        User foundAllDtoOK = userService.findUserByEmail("Kittoki@gmail.com");

        //then
        assertEquals(foundAllDtoOK.getUserId(), user.getUserId());
        assertEquals(foundAllDtoOK.getName(), user.getName());
        assertEquals(foundAllDtoOK.getSurname(), user.getSurname());
        assertEquals(foundAllDtoOK.getEmail(), user.getEmail());
        assertEquals(foundAllDtoOK.getPassword(), user.getPassword());
        assertEquals(foundAllDtoOK.getPhone(), user.getPhone());
        assertEquals(foundAllDtoOK.getRole(), user.getRole());
    }
    //ENDS

    //findUserByPhone BEGINS
    @Test
    public void shouldReturnFindUserByPhoneOK() {
        //given
        when(userRepository.findUserByPhone("789355718")).thenReturn(user);

        //when
        User foundAllDtoOK = userService.findUserByPhone("789355718");

        //then
        assertEquals(foundAllDtoOK.getUserId(), user.getUserId());
        assertEquals(foundAllDtoOK.getName(), user.getName());
        assertEquals(foundAllDtoOK.getSurname(), user.getSurname());
        assertEquals(foundAllDtoOK.getEmail(), user.getEmail());
        assertEquals(foundAllDtoOK.getPassword(), user.getPassword());
        assertEquals(foundAllDtoOK.getPhone(), user.getPhone());
        assertEquals(foundAllDtoOK.getRole(), user.getRole());
    }
    //ENDS

    //findUserByPhone BEGINS
    @Test
    public void shouldReturnFindUserByRoleOK() {
        //given
        List<User> userListOK = prepareUsers().stream()
                .filter(role -> role.getRole().equals("CUSTOMER"))
                .collect(toList());
        when(userRepository.findUserByRole("CUSTOMER")).thenReturn(userListOK);

        //when
        List<User> foundAllOK = userService.findUserByRole("CUSTOMER");

        //then
        assertEquals(foundAllOK, userListOK);
    }

    @Test
    public void shouldReturnFindUserByRoleERR() {
        //given
        when(userRepository.findUserByRole("CUSTOMER")).thenReturn(Collections.EMPTY_LIST);

        //when
        List<User> foundAllERR = userService.findUserByRole("CUSTOMER");

        //then
        assertEquals(foundAllERR, Collections.EMPTY_LIST);
    }
    //ENDS

    private List<User> prepareUsers() {
        User user1 = new User("Eugeniusz", "Tostownik", "tostownik@wp.pl", "Tostownik123", "795192231", "CUSTOMER");
        User user2 = new User("Dawid", "Niezgodny", "Niezgodny123!@", "niezgodny@wp.pl", "713999233", "CUSTOMER");
        User user3 = new User("Waldermar", "Wolski", "Wolski123!@", "wolski@wp.pl", "712344233", "ADMIN");
        User user4 = new User("Jakub", "Najdrowski", "Najdrowski123!@", "najdrowski@wp.pl", "725444567", "ADMIN");
        User user5 = new User("Karol", "Wojtyla", "Wojtyla123!@", "wojtyla@wp.pl", "711414233", "ADMIN");

        return Arrays.asList(user1, user2, user3, user4, user5);
    }

}
