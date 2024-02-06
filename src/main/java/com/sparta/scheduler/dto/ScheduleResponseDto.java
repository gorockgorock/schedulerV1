package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private long id;
    private String username;
    private String doList;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    private String msg;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.username = schedule.getUsername();
        this.doList = schedule.getDoList();
        this.createAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }

    public ScheduleResponseDto(String msg){
        this.msg = msg;
    }
}

