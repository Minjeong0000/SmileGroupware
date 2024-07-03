package smile.office.groupware.personalEvent.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.personalEvent.service.PersonalEventService;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@RestController
@RequestMapping("api/event")
@RequiredArgsConstructor
public class PersonalEventApiController {

    private final PersonalEventService service;



    //개인일정 목록조회
    @GetMapping("list")
    public List<PersonalEventVo> getPersonalEventList(){

        List<PersonalEventVo> voList = service.getPersonalEventList();
        System.out.println("voList = " + voList);
        return voList;
    }

    //개인일정작성
    @PostMapping
    public int write(PersonalEventVo vo){

        System.out.println("vo = " + vo);
         //TODO 나중에, 로그인한 유저 정보로 변경하기
        int result = service.write(vo);

        return result;





    }




    //개인일정수정

    //개인일정삭제

    //개인일정검색(카테고리)

    //개인일정조회
}
