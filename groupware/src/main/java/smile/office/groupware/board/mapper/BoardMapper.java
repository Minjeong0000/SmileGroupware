package smile.office.groupware.board.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import smile.office.groupware.board.vo.BoardReplyVo;
import smile.office.groupware.board.vo.BoardVo;
import smile.office.groupware.page.PageVo;

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
                ,#{vo.title}
                ,#{vo.content}
                ,#{writerNo}
            )
            """)
    int write(String writerNo, BoardVo vo);


//상세조회
    @Select("""
            
            SELECT B.B_NO AS NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
                  (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT,
                  (SELECT COUNT(*) FROM BOARD_REPLY BR WHERE B.B_NO = BR.REF_NO AND DEL_YN='N')AS REPLY_COUNT
            FROM BOARD B
            LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
            WHERE B.B_NO = #{no} AND DEL_YN='N'
            
            """)
    BoardVo getBoardByNo(String no);
    //조회수증가
    @Update("""
            UPDATE BOARD SET HIT = HIT+1 WHERE B_NO = #{no} AND DEL_YN = 'N'
            """)
    int increaseHit(String no);

    //게시글목록조회
    @Select("""
            SELECT B.B_NO AS NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
                (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT,
                (SELECT COUNT(*) FROM BOARD_REPLY BR WHERE B.B_NO = BR.REF_NO AND DEL_YN='N')AS REPLY_COUNT
            FROM BOARD B
            LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
            WHERE DEL_YN='N' ORDER BY NO DESC
            """)
    List<BoardVo> getBoardList(RowBounds rb);

    //전체게시글수
    @Select("""
            SELECT COUNT(B_NO) FROM BOARD WHERE DEL_YN = 'N'
            """)
    int getTotalBoardCount();

    //좋아요
    @Insert("""
            INSERT INTO LIKES(B_NO,LIKED_PPL)VALUES(#{no},#{empId})
            """)
    int insertLike(String no, String empId);

    //좋아요취소
    @Delete("""
            DELETE FROM LIKES WHERE B_NO =#{no}AND LIKED_PPL = #{empId}
            """)
    int deleteLike(String no , String empId);

    //이미 추천했는지 검사
    @Select("""
        SELECT COUNT(*) FROM LIKES WHERE LIKED_PPL = #{empId} AND B_NO =#{no}
        """)
    int checkLike(String no, String empId);

    //게시글삭제
    @Update("""
            UPDATE BOARD SET DEL_YN = 'Y' WHERE B_NO = #{no} AND DEL_YN = 'N'
            """)
    int deleteBoardByNo(String no);

    //게시글수정
    @Update("""
            UPDATE BOARD SET CONTENT = #{content},TITLE =#{title} WHERE B_NO =#{no}
            """)
    int edit(BoardVo vo);
    //댓글조회
    @Select("""
            
            SELECT
                R.NO
                ,R.CONTENT
                ,R.REF_NO
                ,R.WRITE_DATE
                ,R.DEL_YN
                ,R.WRITER_NO
                ,E.EMP_NAME AS WRITER_NAME
            FROM BOARD_REPLY R
            JOIN EMPLOYEE E ON R.WRITER_NO = E.EMP_ID
            WHERE R.REF_NO = #{refNo}
            AND R.DEL_YN = 'N'
            ORDER BY R.NO DESC
            """)
    List<BoardReplyVo> getBoardReply(String refNo);

    //댓글삭제
    @Update("""
            UPDATE BOARD_REPLY SET DEL_YN = 'Y' WHERE NO =#{no} AND DEL_YN = 'N'
            """)
    int deleteReply(String no);
    //댓글작성
    @Insert("""
            INSERT INTO BOARD_REPLY( NO ,REF_NO ,WRITER_NO ,CONTENT )VALUES( SEQ_BOARD_REPLY.NEXTVAL ,#{refNo} ,#{writerNo} ,#{content} )
            """)
    int writeReply(BoardReplyVo vo);


    //제목검색수 조회
@Select("""
        SELECT COUNT(*)
        FROM BOARD B
        LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
        WHERE B.DEL_YN='N' AND B.TITLE LIKE '%'||#{title} ||'%'
        """)
    int getSearchTitleCnt(String title);

@Select("""
        SELECT B.B_NO AS NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
               (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT,
               (SELECT COUNT(*) FROM BOARD_REPLY BR WHERE B.B_NO = BR.REF_NO AND DEL_YN='N')AS REPLY_COUNT

        FROM BOARD B
        LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
        WHERE DEL_YN='N' AND B.TITLE LIKE '%'||#{title} ||'%' ORDER BY NO DESC
        """)
    List<BoardVo> searchTitle(String title, RowBounds rb);

@Select("""
        SELECT COUNT(*)
        FROM BOARD B
        LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
        WHERE B.DEL_YN='N' AND B.CONTENT LIKE '%'||#{content}||'%'
        """)
    int getSearchContentCnt(String content);


@Select("""
        SELECT B.B_NO AS NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
               (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT,
               (SELECT COUNT(*) FROM BOARD_REPLY BR WHERE B.B_NO = BR.REF_NO AND DEL_YN='N')AS REPLY_COUNT

        FROM BOARD B
        LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
        WHERE DEL_YN='N' AND B.CONTENT LIKE '%'||#{content}||'%' ORDER BY NO DESC
        """)
    List<BoardVo> searchContent(String content, RowBounds rb);


    @Select("""
            SELECT COUNT(*)
            FROM BOARD B
            LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
            WHERE B.DEL_YN='N'
              AND E.EMP_NAME LIKE '%'||#{writerName}||'%'
            """)
    int getSearchWriterNameCnt(String writerName);

    @Select("""
            SELECT B.B_NO AS NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
                   (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT,
                   (SELECT COUNT(*) FROM BOARD_REPLY BR WHERE B.B_NO = BR.REF_NO AND BR.DEL_YN='N') AS REPLY_COUNT
            FROM BOARD B
            LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
            WHERE B.DEL_YN='N'
              AND E.EMP_NAME LIKE '%'||#{writerName}||'%'
            ORDER BY B.B_NO DESC
            """)
    List<BoardVo> searchWriterName(String writerName, RowBounds rb);

    @Select("""
            SELECT COUNT(*)
            FROM BOARD B
            LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
            WHERE B.DEL_YN='N'
              AND (B.TITLE LIKE '%'||#{titleContent}||'%' OR B.CONTENT LIKE '%'||#{titleContent}||'%')
            """)
    int getSearchTitleContentCnt(String titleContent);


    @Select("""
            SELECT B.B_NO AS NO, B.TITLE, B.CONTENT, B.WRITER_NO, B.HIT, B.DEL_YN, B.WRITE_DATE, E.EMP_NAME AS WRITER_NAME,
                       (SELECT COUNT(*) FROM LIKES L WHERE L.B_NO = B.B_NO) AS LIKE_COUNT,
                       (SELECT COUNT(*) FROM BOARD_REPLY BR WHERE B.B_NO = BR.REF_NO AND DEL_YN='N') AS REPLY_COUNT
                FROM BOARD B
                LEFT JOIN EMPLOYEE E ON B.WRITER_NO = E.EMP_ID
                WHERE B.DEL_YN='N'
                  AND (B.TITLE LIKE '%'||#{titleContent}||'%' OR B.CONTENT LIKE '%'||#{titleContent}||'%')
            """)
    List<BoardVo> searchTitleContent(String titleContent, RowBounds rb);
    
}


