package caps.testing.controller;

import caps.testing.domain.Work;
import caps.testing.dto.work.WorkEndDto;
import caps.testing.dto.work.WorkStartDto;
import caps.testing.repository.WorkRepository;
import caps.testing.service.TeamService;
import caps.testing.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkRepository workRepository;
    private final WorkService workService;
    private final TeamService teamService;

    //멤버 ID별 근무 시작 데이터 쌓는 API
    @PostMapping(value = "/api/work/start/{member_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long register_start_time(@RequestBody @Valid WorkStartDto workStartDto, @PathVariable @Valid Long member_id){
        return workService.save_startTime(workStartDto, member_id);
    }

    //멤버 ID별 근무 종료 데이터와 근무 총 시간 데이터 쌓는 API
    @PostMapping(value = "/api/work/end/{member_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register_end_time(@RequestBody @Valid WorkEndDto workEndDto, @PathVariable @Valid Long member_id){
        workService.save_endTime(workEndDto, member_id);
        return "일 종료";
    }

    @GetMapping("/api/work/today")
    public String get_today_work(){
        workService.calculate_work_time();
        return "오늘 일한 시간 체크";
    }

    @GetMapping("/api/work/start/location/{team_id}")
    public Map<String, Double> get_location(@PathVariable @Valid Long team_id){
        return teamService.findLocation(team_id);
    }

    //멤버 ID별 일한 근무 데이터 조회하는 API
    @GetMapping("/api/work/{member_id}")
    public Work get_total_work(@PathVariable @Valid Long member_id) {
        Optional<Work> workAll = workRepository.findById(member_id);
        List<Work> myWorkList = new ArrayList<>();
        Work work = workAll.get();
//        Map<String, Timestamp> map = new HashMap<>();
//        map.put("일 시작 시간", work.getWork_start_time());
//        map.put("일 종료 시간", work.getWork_end_time());
        return work;
    }

    // 날짜별 일한 시간
    @GetMapping("/api/work/chart/{member_id}")
    public Map<String, Long> get_work_data(@PathVariable @Valid Long member_id){
        List<String> workDateForChart = workRepository.findWorkDateForChart(member_id);
        List<Long> workTimeForChart = workRepository.findWorkTimeForChart(member_id);
        Map<String, Long> map = new LinkedHashMap<>();

        for (int i = 0; i < workDateForChart.size(); i++) {
            map.put(workDateForChart.get(i), workTimeForChart.get(i));
        }

        return map;
    }

    // 월별 일한 총 시간
    @GetMapping("/api/work/month/chart/{member_id}")
    public Map<String, Long> get_month_work_data(@PathVariable @Valid Long member_id){
        List<String> workDateForChart = workRepository.findWorkDateForChart(member_id);
        List<String> workMonthDateList = new ArrayList<>();
        for (int i = 0; i < workDateForChart.size(); i++) {
            workMonthDateList.add(workDateForChart.get(i).substring(5,7));
        }
        List<Long> workTimeForChart = workRepository.findWorkTimeForChart(member_id);
        Map<String, Long> map = new LinkedHashMap<>();

        for (int i = 0; i < workMonthDateList.size(); i++) {
            if(map.containsKey(workMonthDateList.get(i))){
                map.put(workMonthDateList.get(i), map.get(workMonthDateList.get(i)) + workTimeForChart.get(i));
            }
            else{
                map.put(workMonthDateList.get(i), workTimeForChart.get(i));
            }
        }

        return map;
    }

}