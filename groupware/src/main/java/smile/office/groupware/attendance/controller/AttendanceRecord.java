package smile.office.groupware.attendance.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.attendance.service.AttendanceService;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("record")
@RequiredArgsConstructor
public class AttendanceRecord {

    private final AttendanceService service;


    //출근시간 기록
    @GetMapping("start")
    public AttendanceVo insertStartTime(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo)session.getAttribute("loginEmployeeVo");
        AttendanceVo vo = new AttendanceVo();
        vo.setAttNo("777");
        vo.setState("피곤함");
        vo.setEndTime("6시");
        System.out.println("HelloController.hello");

        return vo;
    }

    //퇴근시간 기록
    @GetMapping("end")
    public AttendanceVo updateStartTime(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo)session.getAttribute("loginEmployeeVo");
        AttendanceVo vo = new AttendanceVo();
        vo.setAttNo("111");
        vo.setState("ㅗㅅ어ㅛㅗ여ㅛㅕ");
        vo.setEndTime("6시");
        System.out.println("HelloController.hello");

        return vo;
    }




    // 내 근태 조회
    // RestController => JSON 형식으로 변환
    @GetMapping("list")
    public List<AttendanceVo> getAttendanceCal(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo != null) {
            String empId = loginEmployeeVo.getEmpId();
            List<AttendanceVo> myAttendanceList = service.getAttendanceCal(empId);
            System.out.println("myAttendanceList = " + myAttendanceList);

            return myAttendanceList;
        } else {
            // 로그인되지 않은 경우 빈 리스트 반환 또는 예외 처리
            return Collections.emptyList();
        }
    }






}
