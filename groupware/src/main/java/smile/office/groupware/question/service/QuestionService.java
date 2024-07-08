package smile.office.groupware.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.question.mapper.QuestionMapper;
import smile.office.groupware.question.vo.QuestionVo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionMapper mapper;

    @Transactional
    public int addQuestion(QuestionVo question) throws Exception {
        return mapper.insertQuestion(question);
    }

    public List<QuestionVo> getAllQuestions() {
        return mapper.getAllQuestions();
    }
}
