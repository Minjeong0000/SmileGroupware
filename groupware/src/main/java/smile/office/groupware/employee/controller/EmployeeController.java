package smile.office.groupware.employee.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smile.office.groupware.employee.service.EmployeeService;

@Controller
@RequiredArgsConstructor
@RequestMapping("employee")
public class EmployeeController {


    private final EmployeeService service;

    //사원 로그인화면
    @GetMapping("login")
    public String employeeLogin(){

        return "employee/login";

    }
    //사원로그인
    @PostMapping("login")
    public String login(){



    }









}
