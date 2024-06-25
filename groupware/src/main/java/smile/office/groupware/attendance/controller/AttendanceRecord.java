package smile.office.groupware.attendance.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.attendance.vo.AttendanceVo;

@RestController
@RequestMapping("record")
public class AttendanceRecord {

    @GetMapping("start")
    public AttendanceVo hello(){

        AttendanceVo vo = new AttendanceVo();
        vo.setAttNo("777");
        vo.setState("피곤함");
        vo.setEndTime("6시");
        System.out.println("HelloController.hello");

        return vo;
    }

    @GetMapping("end")
    public AttendanceVo hi(){
        AttendanceVo vo = new AttendanceVo();
        vo.setAttNo("111");
        vo.setState("ㅗㅅ어ㅛㅗ여ㅛㅕ");
        vo.setEndTime("6시");
        System.out.println("HelloController.hello");

        return vo;
    }




}
