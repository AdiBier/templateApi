package com.ab.templateApi.dao.repository;

import com.ab.templateApi.dao.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
//    List<Room> findRoomByMovie(String movie);
}
