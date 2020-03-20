package com.ab.templateApi.dao.handler;

import com.ab.templateApi.dao.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RoomDto {

    private Long roomId;

    private String msg;

    private String code;

    public RoomDto(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public static RoomDto toRoomDto(Room room, String msg, String code) {
        return RoomDto.builder()
                .roomId(room.getRoomId())
                .msg(msg)
                .code(code)
                .build();
    }
}
