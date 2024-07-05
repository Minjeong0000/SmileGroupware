package smile.office.groupware.question.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import smile.office.groupware.question.service.QuestionService;

@Controller
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService service;

}
