package smile.office.groupware.attendance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.attendance.service.AttendanceService;
import smile.office.groupware.attendance.vo.AttendanceVo;

import java.util.List;

@Controller
@RequestMapping("/emp/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService service;

    //달력 화면
    @GetMapping("/cal")
    public String getAttendanceCal(){
//  List<AttendanceVo> voList = service.getAttendanceCal();
        return "emp/attendance/cal";
    }

//근태 기간 조회 화면
    @GetMapping("/history")
    public String getAttendanceHistory(){
        return "emp/attendance/history";
    }


}
