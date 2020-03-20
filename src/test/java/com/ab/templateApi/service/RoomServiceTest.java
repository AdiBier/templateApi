package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.Movie;
import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.dao.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    private Room room;

    private final Long ROOM_ID = 1L;

    @Before
    public void setUp(){
//        room = new Room(ROOM_ID);
    }

    //findAll BEGINS
//    @Test
//    public void shouldReturnFindAllRoomOK() {
//        //given
//        List<Room> roomListOK = prepareRooms();
//        when(roomRepository.findAll()).thenReturn(roomListOK);
//
//        //when
//        Iterable<Room> foundAllOK = roomService.findAll();
//
//        //then
//        assertEquals(foundAllOK, roomListOK);
//    }

    @Test
    public void shouldReturnFindAllRoomERR() {
        //given
        when(roomRepository.findAll()).thenReturn(Collections.emptyList());

        //when
        Iterable<Room> foundAllERR = roomService.findAll();

        //then
        assertEquals(foundAllERR, Collections.EMPTY_LIST);
    }
    //ENDS

    //findRoomById BEGINS
    @Test
    public void shouldReturnFindRoomById(){
        //given
        when(roomRepository.findById(room.getRoomId())).thenReturn(Optional.of(room));

        //when
        Optional<Room> foundRoomByIdOK = roomRepository.findById(1L);

        //then
        assertEquals(foundRoomByIdOK.get().getRoomId(), room.getRoomId());
    }
    //ENDS

    //findRoomByMovie BEGINS
    @Test
    public void shouldReturnFindRoomByMovieOK(){
        //given

        //when

        //then
    }

    @Test
    public void shouldReturnFindMovieByCategoryERR(){
        //given
//        when(roomRepository.findRoomByMovie("Drama")).thenReturn(Collections.EMPTY_LIST);

        //when
//        List<Room> foundAllERR = roomService.findRoomByMovie("Drama");

        //then
//        assertEquals(foundAllERR, Collections.EMPTY_LIST);
    }
    //ENDS

//    private List<Room> prepareRooms() {
//        Room room1 = new Room(1L);
//        Room room2 = new Room(2L);
//        Room room3 = new Room(3L);
//        Room room4 = new Room(4L);
//        Room room5 = new Room(5L);
//
//        return Arrays.asList(room1, room2, room3, room4, room5);
//    }
}
