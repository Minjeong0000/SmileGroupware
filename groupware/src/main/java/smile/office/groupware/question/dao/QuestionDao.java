package smile.office.groupware.question.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.question.mapper.QuestionMapper;

@Repository
@RequiredArgsConstructor
public class QuestionDao {

    private final QuestionMapper mapper;

}
