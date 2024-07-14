package smile.office.groupware.board.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;
import smile.office.groupware.board.vo.BoardVo;

import java.util.List;

@Mapper
public interface BoardMapper {
//작성
    @Insert("""
            INSERT INTO BOARD
            (
                B_NO
                ,TITLE
                ,CONTENT
                ,WRITER_NO
            )
            VALUES
            (
                SEQ_BOARD.NEXTVAL
                ,#{title}
                ,#{content}
                ,#{writerNo}
            )
            """)
    int write(String writerNo, BoardVo vo);


//상세조회
    @Select("""
            
            SELECT B.B_NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
                   (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT
            FROM BOARD B
            LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
            WHERE B.B_NO = #{no}
            
            """)
    BoardVo getBoardByNo(String no);
//조회수증가
    @Update("""
            UPDATE BOARD SET HIT = HIT+1 WHERE B_NO = #{no} AND DEL_YN = 'N'
            """)
    int increaseHit(String no);

    //게시글목록조회
    @Select("""
            SELECT B.B_NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
                   (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT
            FROM BOARD B
            LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
            WHERE DEL_YN='N'
            
            """)
    List<BoardVo> getBoardList(RowBounds rb);

    //전체게시글수
    @Select("""
            SELECT COUNT(B_NO) FROM BOARD WHERE DEL_YN = 'N'
            """)
    int getTotalBoardCount();


}


