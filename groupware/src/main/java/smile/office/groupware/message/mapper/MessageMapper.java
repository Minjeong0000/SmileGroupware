package smile.office.groupware.message.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;
import smile.office.groupware.message.vo.MessageVo;

import java.util.List;

@Mapper
public interface MessageMapper {

    //받은쪽지 조회
    @Select("SELECT MU_RECEIVER.MESSAGE_USER_NO, M.MESSAGE_NO, MU_RECEIVER.FORDER_NO, F.FORDER_NAME, M.CONTENT, M.SENT_AT, M.SENDER_NO, S.EMP_NAME AS SENDER_NAME, M.RECEIVER_NO, R.EMP_NAME AS RECEIVER_NAME, MU_RECEIVER.READ_YN FROM MESSAGE M JOIN EMPLOYEE S ON M.SENDER_NO = S.EMP_ID JOIN EMPLOYEE R ON M.RECEIVER_NO = R.EMP_ID LEFT JOIN MESSAGE_USER MU_RECEIVER ON M.MESSAGE_NO = MU_RECEIVER.MESSAGE_NO AND MU_RECEIVER.EMP_ID = R.EMP_ID JOIN FORDER F ON MU_RECEIVER.FORDER_NO = F.FORDER_NO WHERE R.EMP_ID = #{empId} AND MU_RECEIVER.EMP_ID = #{empId} AND MU_RECEIVER.FORDER_NO!=2 ORDER BY M.MESSAGE_NO DESC")
    List<MessageVo> getReceiveMessageList(String empId);

    //중요쪽지 조회
    @Select("SELECT M.MESSAGE_NO, MU.MESSAGE_USER_NO, M.SENDER_NO, S.EMP_NAME AS SENDER_NAME, M.RECEIVER_NO, R.EMP_NAME AS RECEIVER_NAME, M.CONTENT, M.SENT_AT, MU.READ_YN, MU.DELETED_DATE, F.FORDER_NO FROM MESSAGE M JOIN MESSAGE_USER MU ON M.MESSAGE_NO = MU.MESSAGE_NO AND MU.EMP_ID = #{empId} JOIN EMPLOYEE S ON M.SENDER_NO = S.EMP_ID JOIN EMPLOYEE R ON M.RECEIVER_NO = R.EMP_ID JOIN FORDER F ON MU.FORDER_NO = F.FORDER_NO WHERE MU.FORDER_NO = 1 AND R.EMP_ID = #{empId} ORDER BY M.MESSAGE_NO DESC")
    List<MessageVo> getImportantMsglist(String empId);

    //휴지통 쪽지 조회
    @Select("SELECT M.MESSAGE_NO, MU.MESSAGE_USER_NO, M.SENDER_NO, S.EMP_NAME AS SENDER_NAME, M.RECEIVER_NO, R.EMP_NAME AS RECEIVER_NAME, M.CONTENT, M.SENT_AT, MU.READ_YN, MU.DELETED_DATE, F.FORDER_NO, F.FORDER_NAME FROM MESSAGE M JOIN MESSAGE_USER MU ON M.MESSAGE_NO = MU.MESSAGE_NO AND MU.EMP_ID =#{empId} JOIN EMPLOYEE S ON M.SENDER_NO = S.EMP_ID JOIN EMPLOYEE R ON M.RECEIVER_NO = R.EMP_ID JOIN FORDER F ON MU.FORDER_NO = F.FORDER_NO WHERE MU.FORDER_NO = 2 ORDER BY M.MESSAGE_NO DESC")
    List<MessageVo> getTrashMsgList(String empId);

    //보낸쪽지함
    @Select("SELECT MU_SENDER.MESSAGE_USER_NO, M.MESSAGE_NO, M.CONTENT, M.SENT_AT, R.EMP_NAME AS RECEIVER_NAME, M.RECEIVER_NO, S.EMP_NAME AS SENDER_NAME, M.SENDER_NO, MU_RECEIVER.READ_YN FROM MESSAGE M JOIN EMPLOYEE S ON M.SENDER_NO = S.EMP_ID JOIN EMPLOYEE R ON M.RECEIVER_NO = R.EMP_ID JOIN MESSAGE_USER MU_SENDER ON M.MESSAGE_NO = MU_SENDER.MESSAGE_NO AND MU_SENDER.EMP_ID = S.EMP_ID LEFT JOIN MESSAGE_USER MU_RECEIVER ON M.MESSAGE_NO = MU_RECEIVER.MESSAGE_NO AND MU_RECEIVER.EMP_ID = R.EMP_ID WHERE S.EMP_ID = #{empId} AND MU_SENDER.EMP_ID = #{empId} AND MU_SENDER.FORDER_NO !=2 ORDER BY M.MESSAGE_NO DESC")
    List<MessageVo> getSentMsgList(String empId);


    //메세지 여러개 읽음
    @Update({
            "<script>",
            "UPDATE MESSAGE_USER",
            "SET READ_YN = 'Y'",
            "WHERE EMP_ID = #{empId}",
            "AND MESSAGE_USER_NO IN (",
            "SELECT MU2.MESSAGE_USER_NO",
            "FROM MESSAGE_USER MU2",
            "JOIN MESSAGE M ON MU2.MESSAGE_NO = M.MESSAGE_NO",
            "WHERE MU2.EMP_ID = #{empId}",
            "AND M.MESSAGE_NO IN",
            "<foreach item='no' collection='msgList' open='(' separator=',' close=')'>",
            "#{no}",
            "</foreach>",
            ")",
            "</script>"
    })
    int updateReadStatus(@Param("empId") String empId,@Param("msgList")List<String>msgList);

    //휴지통으로 여러개보내기(수정후)
    @Update({
            "<script>",
            "UPDATE MESSAGE_USER",
            "SET FORDER_NO = 2",
            "WHERE EMP_ID = #{empId}",
            "AND MESSAGE_USER_NO IN (",
            "SELECT MESSAGE_USER_NO",
            "FROM MESSAGE_USER MU2",
            "JOIN MESSAGE M ON MU2.MESSAGE_NO = M.MESSAGE_NO",
            "WHERE MU2.EMP_ID = #{empId}",
            "AND M.MESSAGE_NO IN",
            "<foreach item='no' collection='msgList' open='(' separator=',' close=')'>",
            "#{no}",
            "</foreach>",
            ")",
            "</script>"
    })
    int updateForderStatusTrash(@Param("empId") String empId, @Param("msgList") List<String> msgList);


    //휴지통에서 영구삭제(여러개)
@Delete({
        "<script>",
        "DELETE FROM MESSAGE_USER",
        "WHERE EMP_ID = #{empId}",
        "AND FORDER_NO = 2",
        "AND MESSAGE_USER_NO IN (",
        "SELECT MU2.MESSAGE_USER_NO",
        "FROM MESSAGE_USER MU2",
        "JOIN MESSAGE M ON MU2.MESSAGE_NO = M.MESSAGE_NO",
        "WHERE MU2.EMP_ID = #{empId}",
        "AND M.MESSAGE_NO IN",
        "<foreach item='no' collection='msgList' open='(' separator=',' close=')'>",
        "#{no}",
        "</foreach>",
        ")",
        "</script>"
})
    int deleteMsg(@Param("empId") String empId,@Param("msgList")List<String> msgList);

//TODO휴지통에서 복구하기


    //메세지 상세조회
    @Select("SELECT M.MESSAGE_NO, MU.FORDER_NO, F.FORDER_NAME, M.SENDER_NO, S.EMP_NAME AS SENDER_NAME, M.RECEIVER_NO, R.EMP_NAME AS RECEIVER_NAME, M.CONTENT, M.SENT_AT, MU.READ_YN, MU.DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON M.SENDER_NO = S.EMP_ID JOIN EMPLOYEE R ON M.RECEIVER_NO = R.EMP_ID JOIN MESSAGE_USER MU ON M.MESSAGE_NO = MU.MESSAGE_NO JOIN FORDER F ON MU.FORDER_NO = F.FORDER_NO WHERE M.MESSAGE_NO = #{num} AND MU.EMP_ID = #{empId}")
    MessageVo getMsgByNo(@Param("empId") String empId,@Param("num")String num);


    //상세조회 클릭시 하나만 읽음처리(받은 쪽지함에서만 사용)
    @Update("UPDATE MESSAGE_USER " +
            "SET READ_YN = 'Y' " +
            "WHERE MESSAGE_NO = #{num} " +
            "AND EMP_ID = #{empId} ")
    int readMessage( @Param("empId") String empId, @Param("num") String num);



    //쪽지 보내기
    @Insert("INSERT INTO MESSAGE (MESSAGE_NO, SENDER_NO, RECEIVER_NO, CONTENT)\n" +
            "VALUES (SEQ_MESSAGE.NEXTVAL, #{senderNo}, #{msgVo.receiverNo}, #{msgVo.content})")
    int insertMessage(@Param("senderNo")String senderNo ,@Param("msgVo")MessageVo msgVo);
    //쪽지 발신인 insert
    @Insert("INSERT INTO MESSAGE_USER (MESSAGE_USER_NO, MESSAGE_NO, EMP_ID)\n" +
            "VALUES (SEQ_MESSAGE_USER.NEXTVAL, SEQ_MESSAGE.CURRVAL, #{senderNo})")
    int insertSenderMessage(@Param("senderNo")String senderNo);

    //쪽지 수신인 insert
    @Insert("INSERT INTO MESSAGE_USER (MESSAGE_USER_NO, MESSAGE_NO, EMP_ID)\n" +
            "VALUES (SEQ_MESSAGE_USER.NEXTVAL, SEQ_MESSAGE.CURRVAL, #{msgVo.receiverNo})")
    int insertReceiverMessage(@Param("msgVo")MessageVo msgVo);
    //일반->중요쪽지상태변경
    @Update("UPDATE MESSAGE_USER MU SET MU.FORDER_NO = 1 WHERE MU.EMP_ID = #{empId} AND MU.MESSAGE_USER_NO IN ( SELECT MU2.MESSAGE_USER_NO FROM MESSAGE_USER MU2 JOIN MESSAGE M ON MU2.MESSAGE_NO = M.MESSAGE_NO WHERE MU2.EMP_ID = #{empId} AND M.MESSAGE_NO = #{num} )")
    int bookmarkMessage(@Param("empId")String empId,  @Param("num")String num);
    @Update("UPDATE MESSAGE_USER MU SET MU.FORDER_NO = 3 WHERE MU.EMP_ID = #{empId} AND MU.MESSAGE_USER_NO IN ( SELECT MU2.MESSAGE_USER_NO FROM MESSAGE_USER MU2 JOIN MESSAGE M ON MU2.MESSAGE_NO = M.MESSAGE_NO WHERE MU2.EMP_ID = #{empId} AND M.MESSAGE_NO = #{num})")
    int unbookmarkMessage(@Param("empId")String empId,@Param("num") String num);

    @Update({
            "<script>",
            "UPDATE MESSAGE_USER",
            "SET FORDER_NO = 3",
            "WHERE EMP_ID = #{empId}",
            "AND MESSAGE_USER_NO IN (",
            "SELECT MESSAGE_USER_NO",
            "FROM MESSAGE_USER MU2",
            "JOIN MESSAGE M ON MU2.MESSAGE_NO = M.MESSAGE_NO",
            "WHERE MU2.EMP_ID = #{empId}",
            "AND M.MESSAGE_NO IN",
            "<foreach item='no' collection='msgList' open='(' separator=',' close=')'>",
            "#{no}",
            "</foreach>",
            ")",
            "</script>"
    })
    int restoreMessage(@Param("empId") String empId, @Param("msgList") List<String> msgList);
}

