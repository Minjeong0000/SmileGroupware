package smile.office.groupware.personalEvent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.personalEvent.service.PersonalEventService;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;
@CrossOrigin
@RequestMapping("event/personal")
@Controller
@RequiredArgsConstructor
public class PersonalEventController {

    private PersonalEventService service;

    //개인일정 달력 (화면)
    @GetMapping("calendar")
    public String calendar(){

        System.out.println("PersonalEventController.calendar");
        return "event/personal";

    }



    }



