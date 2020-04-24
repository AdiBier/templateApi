package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/rooms")
public class RoomApi {

    private final RoomService roomService;

    @Autowired
    public RoomApi(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room addRoom(@Valid @RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping
    public Room updateRoom(@Valid @RequestBody Room room) {
        return roomService.update(room);
    }
}
