package com.ab.templateApi.dao.handler;

import com.ab.templateApi.dao.entity.Room;
import com.ab.templateApi.dao.entity.WorkHour;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class WorkHourDto {

    private Long workHourId;

    private Room room;

    private Integer day;

    private String openingTime;

    private String closingTime;

    private String msg;

    private String code;

    public static WorkHourDto toWorkHourDto(WorkHour workHour, String msg, String code) {
        return WorkHourDto.builder()
                .workHourId(workHour.getWorkHourId())
                .day(workHour.getDay())
                .openingTime(workHour.getOpeningTime())
                .closingTime(workHour.getClosingTime())
                .msg(msg)
                .code(code)
                .build();
    }
}
