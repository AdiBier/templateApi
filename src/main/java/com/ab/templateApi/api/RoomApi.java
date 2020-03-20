package com.ab.templateApi.api;

import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.dao.handler.RoomDto;
import com.ab.templateApi.response.ServerResponse;
import com.ab.templateApi.response.ServerResponseConstants;
import com.ab.templateApi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.ab.templateApi.dao.handler.RoomDto.toRoomDto;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/rooms")
public class RoomApi {

    private final RoomService roomService;

    @Autowired
    public RoomApi(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public Iterable<RoomDto> getAllRooms() {
        Iterable<Room> roomIterable = roomService.findAll();
        if (roomIterable.iterator().hasNext()) {
            return StreamSupport.stream(roomIterable.spliterator(), false)
                    .map(rooms -> {
                        return new RoomDto(rooms.getRoomId(), ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
                    }).collect(toList());
        }
        List<RoomDto> roomDtoList = new ArrayList<>();
        roomDtoList.add(new RoomDto(null, ServerResponseConstants.TAE1003_MSG, ServerResponseConstants.TAE1003_CODE));
        return roomDtoList;
    }

    @GetMapping
    public RoomDto getById(@RequestParam Long id) {
        Optional<Room> room = roomService.findById(id);
        if (room.isPresent()) {
            return toRoomDto(room.get(), ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new RoomDto(null, ServerResponseConstants.TAE1002_MSG, ServerResponseConstants.TAE1002_CODE);
    }

    @PostMapping
    public Room addRoom(@Valid @RequestBody Room room) {
        return roomService.save(room);
    }

    @PutMapping
    public Room updateRoom(@Valid @RequestBody Room room) {
        return roomService.update(room);
    }

    @DeleteMapping
    public ServerResponse deleteMovieById(@RequestParam Long id) {
        if (roomService.findById(id).isPresent()){
            roomService.delete(id);
            return new ServerResponse(ServerResponseConstants.OK_MSG, ServerResponseConstants.OK_CODE);
        }
        return new ServerResponse(ServerResponseConstants.TAE1003_MSG, ServerResponseConstants.TAE1003_CODE);
    }
}
