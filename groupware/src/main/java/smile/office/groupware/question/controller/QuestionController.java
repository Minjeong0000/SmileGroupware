package smile.office.groupware.question.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.question.service.QuestionService;
import smile.office.groupware.question.vo.QuestionVo;
import smile.office.groupware.employee.vo.EmployeeVo;

@Controller
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService service;

    @GetMapping("add")
    public String addQuestion(HttpServletRequest request, Model model) {
        try {
            HttpSession session = request.getSession();
            EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");

            if (loginEmployeeVo == null) {
                return "redirect:/login"; // 로그인 페이지로 리다이렉트
            }

            model.addAttribute("empName", loginEmployeeVo.getEmpName());
            return "question/add";
        } catch (Exception e) {
            e.printStackTrace();
            return "error/500";
        }
    }

    @PostMapping("add")
    @ResponseBody
    public String addQuestion(@RequestBody QuestionVo question, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");

            if (loginEmployeeVo == null) {
                return "error";
            }

            question.setWriterNo(Integer.parseInt(loginEmployeeVo.getEmpId())); // 로그인한 사용자 ID 설정
            int result = service.addQuestion(question);

            if (result != 0) {
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("question")
    public String inquiry() {
        return "question/question";
    }
}
