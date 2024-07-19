package smile.office.groupware.notice.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.notice.vo.NoticeVo;

import java.util.List;

@Mapper
public interface NoticeMapper {
    @Insert("INSERT INTO NOTICE(NO, TITLE,CONTENT,WRITER_NO) VALUES(SEQ_NOTICE.NEXTVAL, #{title}, #{content},#{writerNo})")
    int write(NoticeVo vo);



    @Select("SELECT * FROM NOTICE ORDER BY NO DESC")
    List<NoticeVo> getNoticeList();

    @Select("SELECT * FROM NOTICE WHERE NO = #{no} ")
    NoticeVo getNoticeByNo(String no);
}
