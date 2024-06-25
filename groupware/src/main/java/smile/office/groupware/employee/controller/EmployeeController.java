package smile.office.groupware.employee.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.employee.service.EmployeeService;
import smile.office.groupware.employee.vo.EmployeeVo;

@Controller
@RequiredArgsConstructor
@RequestMapping("emp")
public class EmployeeController {


    private final EmployeeService service;

    //사원 로그인화면
    @GetMapping("login")
    public String login(){

        return "emp/login";

    }
    //사원로그인
    @PostMapping("login")
    public String login(EmployeeVo vo, HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = service.login(vo);

        if(loginEmployeeVo != null ){
            session.setAttribute("loginEmployeeVo", loginEmployeeVo);
            //일단 근태페이지로 보냄
            return "emp/attendance/cal";

        }else{
            model.addAttribute("errMsg","로그인 실패");
                    return "common/error";
        }







    }









}
