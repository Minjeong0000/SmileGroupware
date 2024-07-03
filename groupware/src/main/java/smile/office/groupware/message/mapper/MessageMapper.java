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
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE RECEIVER_NO = #{empId} AND M.FORDER_NO=1")
    List<MessageVo> getImportantMsglist(String empId);

    //휴지통 쪽지 조회
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE (RECEIVER_NO = #{empId} OR SENDER_NO = #{empId}) AND  M.FORDER_NO=2")
    List<MessageVo> getTrashMsgList(String empId);

    //보낸쪽지함
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE SENDER_NO = #{empId}")
    List<MessageVo> getSentMsgList(String empId);

    //메세지 여러개 읽음
    @Update({
            "<script>",
            "UPDATE MESSAGE",
            "SET READ_YN = 'Y'",
            "WHERE MESSAGE_NO IN",
            "<foreach item='no' collection='msgList' open='(' separator=',' close=')'>",
            "#{no}",
            "</foreach>",
            "AND READ_YN = 'N'",
            "</script>"
    })
    int updateReadStatus(@Param("msgList")List<String>msgList);

    //휴지통으로 여러개 보내기
    @Update({
            "<script>",
            "UPDATE MESSAGE",
            "SET FORDER_NO = 2",
            "WHERE MESSAGE_NO IN",
            "<foreach item='no' collection='msgList' open='(' separator=',' close=')'>",
            "#{no}",
            "</foreach>",
            "</script>"
    })
    int updateForderStatusTrash(@Param("msgList")List<String> msgList);


//휴지통에서 영구삭제(여러개)
    @Delete({
            "<script>",
            "DELETE FROM MESSAGE",
            "WHERE MESSAGE_NO IN",
            "<foreach item='no' collection='msgList' open='(' separator=',' close=')'>",
            "#{no}",
            "</foreach>",
            "</script>"
    })
    int deleteMsg(@Param("msgList")List<String> msgList);


    //TODO기능구현해야함
    //메세지 상세조회
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE MESSAGE_NO = #{num}")
    MessageVo getMsgByNo(String num);


}

