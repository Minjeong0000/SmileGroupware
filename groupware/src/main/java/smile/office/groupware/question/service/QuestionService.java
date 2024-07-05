package smile.office.groupware.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.office.groupware.question.dao.QuestionDao;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionDao dao;

}
