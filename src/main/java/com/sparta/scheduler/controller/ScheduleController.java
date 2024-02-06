package com.sparta.scheduler.controller;

import com.sparta.scheduler.dto.ScheduleRequestDto;
import com.sparta.scheduler.dto.ScheduleResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    private final Map<Long, Schedule> scheduleList = new HashMap<>();


    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getSchedule() {

        //내림차순 정렬 스트림
//        List<ScheduleResponseDto> responseList = scheduleList.values().stream()
//                .map(ScheduleResponseDto::new)
//                .sorted(Comparator.comparing(ScheduleResponseDto::getCreateAt).reversed())
//                .toList();

        return scheduleService.getSchedules();
    }

    @PutMapping("/schedule/{id}")
    public Long updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        return scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedule/{id}")
    public ScheduleResponseDto deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        if (scheduleList.containsKey(id)) {
            Schedule schedule = scheduleList.get(id);
            if (schedule.getUsername().equals(requestDto.getUsername())) {

                scheduleList.remove(id);
                return new ScheduleResponseDto("일정이 삭제되었습니다");
            } else { return new ScheduleResponseDto("사용자 정보가 일치하지 않습니다");
            }
        } else {
            throw new IllegalArgumentException("일정이 존재하지 않습니다");
        }
    }
}

