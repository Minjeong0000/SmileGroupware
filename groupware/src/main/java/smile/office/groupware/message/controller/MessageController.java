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

    //받은쪽지함화면
    @GetMapping("/received")
    public String receivedMsg(){
        return "message/received";
    }

    //받은 쪽지 list



    //쪽지보내기 화면

    //쪽지 보내기 기능

}
