package smile.office.groupware.message.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> getReceiveMessageList(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        //로그인정보가 없는 경우 알림창 띄우고 로그인페이지로 보냄
        if (loginEmployeeVo == null) {
            return ResponseEntity.internalServerError().body("비정상적인 접근입니다. 로그인 페이지로 돌아갑니다.");
        }

        String empId = loginEmployeeVo.getEmpId();
        System.out.println(empId);
        List<MessageVo> messageVoList = service.getReceiveMessageList(empId);
        return ResponseEntity.ok(messageVoList);
    }


    //받은메세지(전체)조회(포스트맨확인용)
//@GetMapping("list")
//public ResponseEntity<List<MessageVo>> getReceiveMessageList(@RequestParam String empId){
//        HttpSession session = request.getSession();
//        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
//        String empId = loginEmployeeVo.getEmpId();
//        System.out.println(empId);
//    List<MessageVo> messageVoList = service.getReceiveMessageList(empId);
//    return ResponseEntity.ok(messageVoList);
//}

    //중요쪽지 조회 list

    @GetMapping("important")
    public ResponseEntity<List<MessageVo>>getImportantMsglist(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        //확인
        System.out.println(empId);
        List<MessageVo>messageVoList = service.getImportantMsglist(empId);
        return ResponseEntity.ok(messageVoList);

    }
    //휴지통 쪽지 조회

    @GetMapping("trash")
    public ResponseEntity<List<MessageVo>>getTrashMsgList(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        //확인
        System.out.println(empId);
        List<MessageVo>messageVoList = service.getTrashMsgList(empId);
        return ResponseEntity.ok(messageVoList);

    }
    //보낸쪽지함 조회
    @GetMapping("sentList")
    public ResponseEntity<List<MessageVo>>getSentMsgList(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        //확인
        System.out.println(empId);
        List<MessageVo>messageVoList = service.getSentMsgList(empId);
        return ResponseEntity.ok(messageVoList);
    }

    //읽음으로 상태 변경-> ajax에서 쪽지넘버 받아오고 로그인한사람==수신자가 같아야함
    @PutMapping("changeRead")
    public int updateReadStatus(@RequestBody List<String> msgList){

        int result = service.updateReadStatus(msgList);
        System.out.println("result = " + result);
        return result;
    }
    //휴지통으로 보내기

    @PutMapping("sendTrash")
    public int updateForderStatusTrash(@RequestBody List<String> msgList){

        int result = service.updateForderStatusTrash(msgList);
        System.out.println("result = " + result);
        return result;
    }


    //완전삭제


    //쪽지 상세조회
    @GetMapping("detail")
    public ResponseEntity<MessageVo> getMsgByNo(HttpServletRequest request,String num){

        MessageVo vo = service.getMsgByNo(num);
        System.out.println("vo = " + vo);
        return ResponseEntity.ok(vo);

    }


}



