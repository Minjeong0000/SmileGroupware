package smile.office.groupware.question.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.question.mapper.QuestionMapper;
import smile.office.groupware.question.vo.QuestionVo;

@Repository
@RequiredArgsConstructor
public class QuestionDao {

    private final QuestionMapper mapper;

    public int insertQuestion(QuestionVo question) {
        return mapper.insertQuestion(question);
    }
}
