package smile.office.groupware.personalEvent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import smile.office.groupware.personalEvent.dao.PersonalEventDao;
import smile.office.groupware.personalEvent.service.PersonalEventService;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@Controller
public class PersonalEventController {

    @Autowired
    private PersonalEventService service;

    //개인일정조회
    @GetMapping("event/personallist")
    public void getPersonalList(){
        //service
        List<PersonalEventVo> voList = service.getPersonalList();
        for (PersonalEventVo personalEventVo : voList) {
            System.out.println("personalEventVo = " + personalEventVo);
        }
    }

    //개인일정등록
    @GetMapping("event/personalenroll")
    public void personalEnroll(PersonalEventVo vo){
        //service
        int result = service.personalEnroll(vo);
        System.out.println("result = " + result);
    }


    //개인일정수정

    //개인일정삭제

    //개인일정검색(카테고리)
}
