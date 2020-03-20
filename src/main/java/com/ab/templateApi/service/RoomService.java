package com.ab.templateApi.service;

import com.ab.templateApi.dao.entity.Movie;
import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.dao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public Iterable<Room> findAll() {
        return roomRepository.findAll();
    }

//    public List<Room> findRoomByMovie(String movie) {
//        return roomRepository.findRoomByMovie(movie);
//    }

    public Room save(Room newRoom) {
        Room room = new Room(newRoom.getRoomId());
        return roomRepository.save(room);
    }

    public Room update(Room room) {
        return roomRepository.save(room);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        save(new Room(1L));
        save(new Room(2L));
        save(new Room(3L));
        save(new Room(4L));
    }
}
