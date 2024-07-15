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
import java.util.Map;

@RestController
@RequestMapping("api/event")
@RequiredArgsConstructor
@CrossOrigin
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
        System.out.println("PersonalEventApiController.write");
        System.out.println("vo = " + vo);
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo)session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        vo.setEmpId(empId);
        System.out.println("empId = " + empId);
        System.out.println("vo = " + vo);
        System.out.println("일정등록요청들어옴");

        int result = service.write(vo);

        return result;

    }


    //개인일정수정
    @PutMapping
    public String edit(PersonalEventVo vo) {
        System.out.println("vo = " + vo);
        int result = service.edit(vo);
        if(result==1){
            return "수정성공";
        }

        return "수정실패";
    }


    //개인일정삭제
    @DeleteMapping
    public String delete(@RequestBody Map<String, String> request) {
        String personalNo = request.get("personalNo");
        System.out.println("Received personalNo for deletion: " + personalNo); // 로그 추가

        int result = service.delete(personalNo);
        if (result == 1) {
            return "삭제 성공";
        }
        return "삭제 실패";
    }



    //개인일정검색(카테고리)

    @GetMapping("search")
    public ResponseEntity<List<PersonalEventVo>> searchPersonalEvents (PersonalEventVo vo){
        System.out.println("vo = " + vo);
        List<PersonalEventVo> voList = service.searchPersonalEvents(vo);
        return ResponseEntity.ok(voList);
    }




}