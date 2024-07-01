package smile.office.groupware.personalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.personalEvent.service.PersonalEventService;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;
@CrossOrigin
@RequestMapping("event/personal")
@Controller
public class PersonalEventController {

    @Autowired
    private PersonalEventService service;

    //개인일정 화면
    @GetMapping("list")
    public String list(){
        return "event/personalEvent";
    }

    //개인일정조회
    @ResponseBody
    @GetMapping("one")
    public List<PersonalEventVo> getPersonalList(){
        //service
        List<PersonalEventVo> voList = service.getPersonalList();
        return voList;

        }
    }

    //개인일정등록



    //개인일정수정

    //개인일정삭제

    //개인일정검색(카테고리)


