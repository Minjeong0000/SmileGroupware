package smile.office.groupware.message.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.message.service.MessageService;
import smile.office.groupware.message.vo.MessageVo;
import smile.office.groupware.page.PageVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/message")
@CrossOrigin
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

    //중요쪽지 조회 list

    @GetMapping("important")
    public ResponseEntity<?>getImportantMsglist(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return ResponseEntity.internalServerError().body("비정상적인 접근입니다. 로그인 페이지로 돌아갑니다.");
        }
        String empId = loginEmployeeVo.getEmpId();
        //확인
        System.out.println(empId);
        List<MessageVo>messageVoList = service.getImportantMsglist(empId);
        //수,발신 구분해야하므로 empId함께 리턴
        Map<String, Object> response = new HashMap<>();
        response.put("empId",empId);
        response.put("messageVoList",messageVoList);
        return ResponseEntity.ok(response);

    }
    //휴지통 쪽지 조회
    @GetMapping("trashList")
    public ResponseEntity<?>getTrashMsgList(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return ResponseEntity.internalServerError().body("비정상적인 접근입니다. 로그인 페이지로 돌아갑니다.");
        }
        String empId = loginEmployeeVo.getEmpId();
        //확인
        List<MessageVo>messageVoList = service.getTrashMsgList(empId);
        //empId, messageVoList함께 반환
        Map<String, Object> response = new HashMap<>();
        response.put("empId",empId);
        response.put("messageVoList",messageVoList);

        return ResponseEntity.ok(response);

//        return ResponseEntity.ok(messageVoList);

    }
    //보낸쪽지함 조회
    @GetMapping("sentList")
    public ResponseEntity<?>getSentMsgList(HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return ResponseEntity.internalServerError().body("비정상적인 접근입니다. 로그인 페이지로 돌아갑니다.");
        }
        String empId = loginEmployeeVo.getEmpId();
        //확인
        System.out.println(empId);
        List<MessageVo>messageVoList = service.getSentMsgList(empId);
        return ResponseEntity.ok(messageVoList);
    }
    //읽음처리
    @PutMapping("changeRead")
    public int updateReadStatus(HttpServletRequest request,@RequestBody List<String> msgList){

        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        int result = service.updateReadStatus(empId,msgList);
        System.out.println("result = " + result);
        return result;
    }

    //휴지통으로 보내기(여러개)
    @PutMapping("sendTrash")
    public int updateForderStatusTrash(HttpServletRequest request, @RequestBody List<String> msgList){

        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        int result = service.updateForderStatusTrash(empId,msgList);
        System.out.println("result = " + result);
        return result;
    }

    //휴지통에서영구삭제(여러개)
    @DeleteMapping("delete")
   public int deleteMsg(HttpServletRequest request, @RequestBody List<String>msgList){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        int result = service.deleteMsg(empId, msgList);
        System.out.println("result = " + result);
        return result;
    }

    //휴지통에서 복구
    @PutMapping("restore")
    public int restoreMessage(HttpServletRequest request, @RequestBody List<String> msgList){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        int result = service.restoreMessage(empId,msgList);
        System.out.println("result = " + result);
        return result;
    }


    //쪽지 보내기
    @PostMapping
    public ResponseEntity<?> insertMessage(HttpServletRequest request, MessageVo msgVo){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String senderNo = loginEmployeeVo.getEmpId();
        System.out.println(senderNo);
        int result = service.insertMessage(senderNo,msgVo);
        int result1 = service.insertSenderMessage(senderNo);
        int result2 = service.insertReceiverMessage(msgVo);
        System.out.println("result = " + result);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        if(result*result1*result2==1){
            return ResponseEntity.ok("쪽지 전송 성공");
        }
            return ResponseEntity.internalServerError().body("쪽지 전송 실패");
    }

    //쪽지 상세조회
    @GetMapping("detail/{num}")
    public ResponseEntity<MessageVo> getMsgByNo(HttpServletRequest request,@PathVariable("num") String num){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        MessageVo vo = service.getMsgByNo(empId,num);
        System.out.println("num = " + num);
        if(vo!=null && vo.getReceiverNo().equals(empId)){
            int result = service.readMessage(empId,num);
        }

        return ResponseEntity.ok(vo);

    }
//중요쪽지지정(하나)
@PutMapping("bookmark")
public int bookmarkMessage(HttpServletRequest request, @RequestBody String num){
    HttpSession session = request.getSession();
    EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
    String empId = loginEmployeeVo.getEmpId();
    System.out.println("num = " + num);
    int result = service.bookmarkMessage(empId, num);
    System.out.println("result = " + result);
    return result;
}
//중요쪽지해제
    @PutMapping("unbookmark")
    public int unbookmarkMessage(HttpServletRequest request,@RequestBody String num){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        int result = service.unbookmarkMessage(empId, num);
        System.out.println("result = " + result);
        return result;
    }





}



