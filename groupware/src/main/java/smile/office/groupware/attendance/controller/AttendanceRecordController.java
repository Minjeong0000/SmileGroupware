package smile.office.groupware.attendance.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
//출근시간기록
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


    // 퇴근시간 기록& 여러번 찍으면 업데이트
    @GetMapping("/end")
    public String handleAttendanceEnd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();

        // 이미 퇴근 기록이 있는지 확인
        if (service.hasCheckOutToday(empId)) {//true일때
           int endTimeOverWrite =  service. updateEndTimeAgain(empId);
            System.out.println("endTimeOverWrite = " + endTimeOverWrite);
            if(endTimeOverWrite == 1){
                return "success";

            }else {return "error";}
        }else {
            int endTimeFirstWrite = service.updateEndTime(empId);
            System.out.println("endTimeFirstWrite = " + endTimeFirstWrite);
            if( endTimeFirstWrite == 1){
                return "success";

            }else {return "error";}
        }
    }

    // 출퇴근 기록 json으로 불러오기(달력에뿌려주기용)
    @GetMapping("/list")
    public ResponseEntity<List<AttendanceVo>> getAttendanceList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        List<AttendanceVo> attendanceList = service.getAttendanceList(empId);

        return ResponseEntity.ok(attendanceList);
    }
    //화면 좌측 오늘의 출퇴근시간 기록 가져오기
    @GetMapping("todayRecord")
    public AttendanceVo getTodayAttRecord(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        AttendanceVo attendanceVo = service.getTodayAttRecord(empId);
        model.addAttribute("attendanceVo", attendanceVo);
        return attendanceVo;
    }

    //기간선택해서 조회기능
    @GetMapping("history")
    public ResponseEntity <List<AttendanceVo>> getAttendanceHistory(HttpServletRequest request, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        System.out.println("empId = " + empId);
        System.out.println("startDate = " + startDate);
        System.out.println("endDate = " + endDate);
        List<AttendanceVo> attendanceList = service.getAttendanceHistory(startDate,endDate, empId);

        return ResponseEntity.ok(attendanceList);

    }






}
