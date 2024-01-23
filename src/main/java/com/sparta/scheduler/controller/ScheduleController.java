package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.ScheduleRequestDto;
import com.sparta.scheduler.dto.ScheduleResponseDto;
import com.sparta.scheduler.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final Map<Long, Schedule> scheduleList = new HashMap<>();
    private static long schedeleIndex;

    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        schedeleIndex++;

        Schedule schedule = new Schedule(requestDto);

        schedule.setId(schedeleIndex);


        scheduleList.put(schedule.getId(), schedule);


        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule() {

        //내림차순 정렬 스트림
        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
                .map(ScheduleResponseDto::new)
                .sorted(Comparator.comparing(ScheduleResponseDto::getMakeDate).reversed())
                .toList();

        return responseList;
    }

    @PutMapping("/schedule/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        if (scheduleList.containsKey(id)) {

            Schedule schedule = scheduleList.get(id);

            if (schedule.getPw().equals(requestDto.getPw())) {
                schedule.modify(requestDto);
                return new ScheduleResponseDto(schedule);
            } else {
                return new ScheduleResponseDto("비밀번호가 일치하지 않습니다");
            }
        } else {
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/schedule/{id}")
    public ScheduleResponseDto deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        if (scheduleList.containsKey(id)) {
            Schedule schedule = scheduleList.get(id);
            if (schedule.getPw().equals(requestDto.getPw())) {

                scheduleList.remove(id);
                return new ScheduleResponseDto("일정이 삭제되었습니다");
            } else { return new ScheduleResponseDto("비밀번호가 일치하지 않습니다");
            }
        } else {
            throw new IllegalArgumentException("일정이 존재하지 않습니다.");
        }
    }
}

