package smile.office.groupware.message.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.message.vo.MessageVo;

import java.util.List;

@Mapper
public interface MessageMapper {

    //받은쪽지 조회
    @Select("SELECT MESSAGE_NO, M.FORDER_NO, F.FORDER_NAME, SENDER_NO,S.EMP_NAME SENDER_NAME, RECEIVER_NO,R.EMP_NAME RECEIVER_NAME,CONTENT,SENT_AT , READ_YN, DEL_YN, DELETED_DATE FROM MESSAGE M JOIN EMPLOYEE S ON(M.SENDER_NO = S.EMP_ID) JOIN EMPLOYEE R ON(M.RECEIVER_NO = R.EMP_ID) JOIN FORDER F ON(M.FORDER_NO = F.FORDER_NO) WHERE RECEIVER_NO = #{empId} AND READ_YN = 'N'")
    List<MessageVo> getReceiveMessageList(String empId);
}
