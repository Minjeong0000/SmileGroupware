package smile.office.groupware.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smile.office.groupware.message.service.MessageService;

@RequestMapping("/message")
@RequiredArgsConstructor
@Controller
public class MessageController {

    private final MessageService service;

    //받은쪽지함화면(받은 쪽지 list)
    @GetMapping("/received")
    public String receivedMsg(){
        return "message/received";
    }

    //받은쪽지함(중요)
    @GetMapping("/important")
    public String importantMsgList(){
        return "message/important";
    }


    //휴지통
    @GetMapping("/trash")
    public String trashMsgList(){
        return "message/trash";
    }

    //보낸쪽지함
    @GetMapping("/sent")
    public String sentMsgList(){
        return "message/sent";
    }



    //쪽지 상세조회
    @GetMapping("/detail")
    public String getMsgByNo(){
        return "message/detail";
    }

    //쪽지보내기 화면
    @GetMapping("/send")
    public String sendMsg(){
        return "message/send";
    }



}
