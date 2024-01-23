package com.sparta.scheduler.dto;

import com.sparta.scheduler.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private long id;
    private int makeDate;
    private int date;
    private int time;
    private String name;
    private String doList;

    private String msg;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.makeDate = schedule.getMakeDate();
        this.date = schedule.getDate();
        this.time = schedule.getTime();
        this.name = schedule.getName();
        this.doList = schedule.getDoList();
    }

    public ScheduleResponseDto(String msg){
        this.msg = msg;
    }
}

