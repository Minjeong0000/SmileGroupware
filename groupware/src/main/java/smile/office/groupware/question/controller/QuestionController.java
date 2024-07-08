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

import java.util.List;

@Controller
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService service;

    @GetMapping("add")
    public String addQuestion(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");

        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }

        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "question/add";
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

            question.setWriterNo(loginEmployeeVo.getEmpId()); // 로그인한 사용자 ID 설정

            int result = service.addQuestion(question);

            if (result != 0) {
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("question")
    public String question() {
        return "question/question";
    }

    @GetMapping("inquiry")
    @ResponseBody
    public List<QuestionVo> inquiry() {
        return service.getAllQuestions();
    }
}
