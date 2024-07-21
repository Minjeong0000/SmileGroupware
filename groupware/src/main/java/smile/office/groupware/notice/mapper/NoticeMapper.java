package smile.office.groupware.notice.mapper;

import org.apache.ibatis.annotations.*;
import smile.office.groupware.notice.vo.NoticeVo;

import java.util.List;

@Mapper
public interface NoticeMapper {
    @Insert("INSERT INTO NOTICE(NO, TITLE,CONTENT,WRITER_NO) VALUES(SEQ_NOTICE.NEXTVAL, #{title}, #{content},#{writerNo})  ")
    int write(NoticeVo vo);





    @Select("SELECT * FROM NOTICE ORDER BY NO DESC ")
    List<NoticeVo> getNoticeList();

    @Select("SELECT * FROM NOTICE WHERE NO = #{no} ORDER BY NO DESC ")
    NoticeVo getNoticeByNo(String no);

    @Delete("DELETE NOTICE WHERE NO = #{no}")
    int delete(String no);


    @Update("UPDATE NOTICE SET CONTENT = #{content} WHERE NO = #{no}")
    int edit(NoticeVo vo);
}
