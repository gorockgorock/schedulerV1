package com.sparta.scheduler.entity;

import com.sparta.scheduler.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Schedule {

        private long id;
        private String pw;
        private int makeDate;
        private int date;
        private int time;
        private String name;
        private String doList;

        public Schedule (ScheduleRequestDto scheduleRequestDto ) {
                this.pw = scheduleRequestDto.getPw();
                this.makeDate = scheduleRequestDto.getMakeDate();
                this.date = scheduleRequestDto.getDate();
                this.time = scheduleRequestDto.getTime();
                this.name = scheduleRequestDto.getName();
                this.doList = scheduleRequestDto.getDoList();
        }

        public void modify (ScheduleRequestDto scheduleRequestDto){
                this.date = scheduleRequestDto.getDate();
                this.time = scheduleRequestDto.getTime();
                this.name = scheduleRequestDto.getName();
                this.doList = scheduleRequestDto.getDoList();
        }

    }



