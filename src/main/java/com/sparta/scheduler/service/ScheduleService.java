package com.sparta.scheduler.service;

import com.sparta.scheduler.dto.ScheduleRequestDto;
import com.sparta.scheduler.dto.ScheduleResponseDto;
import com.sparta.scheduler.entity.Schedule;
import com.sparta.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {

        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        Schedule savedschedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(savedschedule);

        return scheduleResponseDto;
    }

    public List<ScheduleResponseDto> getSchedules() {
        // DB 조회

        return scheduleRepository.findAllByOrderBOrderByModifiedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional //변경감지 적용하려면 꼭 트랜잭셔날 필요하다.
    public Long updateSchedule (Long id, ScheduleRequestDto requestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = new Schedule(requestDto);
        schedule = findSchedule(id);

        if (schedule.getUsername().equals(requestDto.getUsername())){
            schedule.update(requestDto);
        } else  {throw new IllegalArgumentException("권한이 없는 사용자입니다."); }

        //if (memo != null) {
        // memo 내용 수정
        // memoRepository.update(id, requestDto);

        schedule.update(requestDto);

        return id;

        //else {
        //throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        //}
    }

    public Long deleteSchedule (Long id , ScheduleRequestDto requestDto) {

        // 해당 메모가 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);
        // if (memo != null) {
        // memo 삭제
        if (schedule.getUsername().equals(requestDto.getUsername())) {
            scheduleRepository.delete(schedule);
        } else { throw new IllegalArgumentException("권한이 없는 사용자입니다.");}

        return id;


    }

    private Schedule findSchedule (Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택하신 일정은 존재하지 않습니다")
        ); //원래 옵셔널은 널 체크 해야 하는데 예외처리 사용하면 간단하게 가능.
    }
}
