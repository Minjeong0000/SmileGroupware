package smile.office.groupware.question.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.question.vo.QuestionVo;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO QUESTION (NO, WRITER_NO, TITLE, CONTENT, BOARD_PWD, DEL_YN, WRITE_DATE) VALUES (SEQ_QUESTION.NEXTVAL, #{writerNo}, #{title}, #{content}, #{boardPwd}, 'N', SYSDATE)")
    int insertQuestion(QuestionVo question);

    @Select("SELECT NO, WRITER_NO, TITLE, WRITE_DATE FROM QUESTION")
    List<QuestionVo> getAllQuestions();
}
