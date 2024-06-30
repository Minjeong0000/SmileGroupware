package smile.office.groupware.message.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.message.service.MessageService;
import smile.office.groupware.message.vo.MessageVo;

import java.util.List;

@RestController
@RequestMapping("api/message")
@RequiredArgsConstructor
public class MessageRestContoller {

    private final MessageService service;
    //받은메세지(전체)조회
    @GetMapping("list")
    public ResponseEntity<List<MessageVo>> getReceiveMessageList(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        System.out.println(empId);
        List<MessageVo> messageVoList = service.getReceiveMessageList(empId);
        return ResponseEntity.ok(messageVoList);
    }
}
