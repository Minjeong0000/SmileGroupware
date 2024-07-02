package smile.office.groupware.message.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;
import smile.office.groupware.message.vo.MessageVo;

import java.util.List;

@Mapper
public interface MessageMapper {

    //받은쪽지 조회
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN,  DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE RECEIVER_NO = #{empId} AND M.FORDER_NO != 2 AND READ_YN = 'N'")
    List<MessageVo> getReceiveMessageList(String empId);

    //중요쪽지 조회
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE RECEIVER_NO = #{empId} AND M.FORDER_NO=1")
    List<MessageVo> getImportantMsglist(String empId);

    //휴지통 쪽지 조회
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE RECEIVER_NO = #{empId} AND  M.FORDER_NO=2")
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

    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE MESSAGE_NO = #{num}")
    MessageVo getMsgByNo(String num);


}
