package smile.office.groupware.attendance.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.attendance.service.AttendanceService;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class AttendanceRecordController {

    private final AttendanceService service;



    @PostMapping("/start")
    public String handleAttendanceStart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        System.out.println("출근기록 요청들어옴");
        // 이미 출근 기록이 있는지 확인
        if (service.hasCheckInToday(empId)) {
            System.out.println("출근 기록 이미 있음");
            return "false";
        }
         // 출근 기록 저장
        int result = service.insertStartTime(empId);
        if (result != 1) {
            System.out.println("출근기록 저장 실패");
            return "error";

        } else {
            System.out.println("result = " + result);
            session.setAttribute("alertMsg","출근 기록 저장에 성공했습니다.");
            return "success";
        }

    }


    // 퇴근 기록하기
    @GetMapping("/end")
    public String handleAttendanceEnd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        System.out.println("loginEmployeeVo = " + loginEmployeeVo);
        String empId = loginEmployeeVo.getEmpId();

        // 이미 퇴근 기록이 있는지 확인
        if (service.hasCheckOutToday(empId)) {
            return "false";
        }
        // 퇴근 기록 업데이트
        int result = service.updateEndTime(empId);
        if (result != 1) {
            System.out.println("퇴근 기록 저장 실패");
            return "error";

        } else {
            System.out.println("기록 성공");
            return "success";
        }

    }

    // 출퇴근 기록 json으로 불러오기
    @GetMapping("/list")
    public ResponseEntity<List<AttendanceVo>> getAttendanceList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        System.out.println(empId);
        List<AttendanceVo> attendanceList = service.getAttendanceList(empId);

        return ResponseEntity.ok(attendanceList);
    }

    //TODO 오늘의 출퇴근기록 가져와서 화면에 띄우기
    @GetMapping("todayRecord")
    public AttendanceVo getTodayAttRecord(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        System.out.println(empId);
        AttendanceVo attendanceVo = service.getTodayAttRecord(empId);
        System.out.println("attendanceVo = " + attendanceVo);
        model.addAttribute("attendanceVo", attendanceVo);
        return attendanceVo;
    }






}
