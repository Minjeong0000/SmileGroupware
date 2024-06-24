package smile.office.groupware.attendance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.attendance.service.AttendanceService;
import smile.office.groupware.attendance.vo.AttendanceVo;

import java.util.List;

@Controller
@RequestMapping("/emp/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

//    @GetMapping("cal")
//    public List<AttendanceVo> getAttendanceCal(){
//
//        List<AttendanceVo>voList = service.getAttendanceCal();
//        return voList;
//
//    }

    @GetMapping("/cal")
    public String getAttendanceCal(){

//        List<AttendanceVo>voList = service.getAttendanceCal();
        return "emp/attendance/cal";

    }





}
