package smile.office.groupware.attendance.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.attendance.service.AttendanceService;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.page.PageVo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // 이미 출근 기록이 있는지 확인
        if (service.hasCheckInToday(empId)) {
            return "exist";
        }
         // 출근 기록 저장
        int result = service.insertStartTime(empId);
        if (result != 1) {
            return "error";

        } else {
            System.out.println("result = " + result);
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
        if (service.hasCheckOutToday(empId)) {
           int endTimeOverWrite =  service. updateEndTimeAgain(empId);
            if(endTimeOverWrite == 1){
                return "success";

            }else {return "error";}
        }else {
            int endTimeFirstWrite = service.updateEndTime(empId);
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

    // 출퇴근 기록 json으로 불러오기(리스트 페이지처리용)
    @GetMapping("history/list")
    public ResponseEntity<?> getAttendanceListHistory(@SessionAttribute EmployeeVo loginEmployeeVo,@RequestParam("pno") String pno) {
        String empId = loginEmployeeVo.getEmpId();
        int listCount = service.getTotalAttendanceCount(empId);
        int currentPage = Integer.parseInt(pno);
        System.out.println("pno = " + pno);
        int pageLimit =5;
        int boardLimit = 10;
        PageVo pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);

        List<AttendanceVo> attendanceList = service.getAttendanceListHistory(empId,pvo);

        Map<String, Object> response = new HashMap<>();
        response.put("attendanceList", attendanceList);
        response.put("pvo", pvo);
        return ResponseEntity.ok(response);
    }


    //기간선택 조회기능
    @GetMapping("history")
    public ResponseEntity <List<AttendanceVo>> getAttendanceHistory(HttpServletRequest request,
                                                                    @RequestParam("startDate") String startDate,
                                                                    @RequestParam("endDate") String endDate)
    {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        List<AttendanceVo> attendanceList = service.getAttendanceHistory(startDate,endDate, empId);

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



}
