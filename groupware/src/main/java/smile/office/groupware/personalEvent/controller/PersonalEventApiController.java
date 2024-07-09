package smile.office.groupware.personalEvent.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.personalEvent.service.PersonalEventService;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@RestController
@RequestMapping("api/event")
@RequiredArgsConstructor
public class PersonalEventApiController {

    private final PersonalEventService service;

//
    //개인일정 목록조회
    @GetMapping("/list")
    public ResponseEntity<List<PersonalEventVo>>getPersonalEventList(HttpServletRequest request ) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo)session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
//        String empId= "1005";
        List<PersonalEventVo> voList = service.getPersonalEventList(empId);
        System.out.println("controller voList = " + voList);
        return ResponseEntity.ok(voList);
    }

    //개인일정작성
    @PostMapping
    public int write(PersonalEventVo vo,HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo)session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        System.out.println("empId = " + empId);
        System.out.println("vo = " + vo);
        //TODO 나중에, 로그인한 유저 정보로 변경하기
        int result = service.write(vo);

        return result;
    }


    //개인일정수정
    @PutMapping
    public String edit(PersonalEventVo vo) {
        System.out.println("vo = " + vo);
        int result = service.edit(vo);
        return result == 1 ? "수정성공" : "수정실패";
    }


    //개인일정삭제
    @DeleteMapping
    public String delete(String num){
        int result = service.delete(num);
        return result == 1 ? "삭제성공" : "삭제실패";
    }


    //개인일정검색(카테고리)

    @GetMapping("search")
    public ResponseEntity<List<PersonalEventVo>> searchPersonalEvents (PersonalEventVo vo){
        System.out.println("vo = " + vo);
        List<PersonalEventVo> voList = service.searchPersonalEvents(vo);
        return ResponseEntity.ok(voList);
    }




}