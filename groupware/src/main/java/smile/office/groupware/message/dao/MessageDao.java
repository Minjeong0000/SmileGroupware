package smile.office.groupware.message.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.message.mapper.MessageMapper;
import smile.office.groupware.message.vo.MessageVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageDao {

    private final MessageMapper mapper;

    public List<MessageVo> getReceiveMessageList(String empId) {
        return mapper.getReceiveMessageList(empId);
    }

    public List<MessageVo> getImportantMsglist(String empId) {
        return mapper.getImportantMsglist(empId);
    }

    public List<MessageVo> getTrashMsgList(String empId) {
        return mapper.getTrashMsgList(empId);


    }

    public List<MessageVo> getSentMsgList(String empId) {
        return mapper.getSentMsgList(empId);
    }

    public int updateReadStatus(String empId,List<String>msgList) {
        return mapper.updateReadStatus(empId,msgList);
    }




    public int updateForderStatusTrash(String empId, List<String> msgList) {

    return mapper.updateForderStatusTrash(empId, msgList);
    }

//    public int updateForderStatusImportant(String empId, List<String> msgList) {
//        return  mapper.updateForderStatusImportant(empId,msgList);
//
//    }


    //영구삭제
    public int deleteMsg(String empId,List<String> msgList) {
        return mapper.deleteMsg(empId,msgList);
    }


    //상세조회
    public MessageVo getMsgByNo(String empId, String num) {
        return mapper.getMsgByNo(empId,num);
    }

    //상세조회시 읽음처리
    public int readMessage(String empId, String num) {
        return mapper.readMessage(empId,num);
    }

    //메세지보내기
    public int insertMessage(String senderNo, MessageVo msgVo) {
        return mapper.insertMessage(senderNo, msgVo);

    }
    //발신자상태메세지insert
    public int insertSenderMessage(String senderNo) {
        return mapper.insertSenderMessage(senderNo);
    }

    public int insertReceiverMessage(MessageVo msgVo) {
        return mapper.insertReceiverMessage(msgVo);
    }

    public int bookmarkMessage(String empId, String num) {
        return mapper.bookmarkMessage(empId,num);
    }

    public int unbookmarkMessage(String empId, String num) {
        return mapper.unbookmarkMessage(empId,num);
    }

    public int restoreMessage(String empId, List<String> msgList) {
        return mapper.restoreMessage(empId,msgList);
    }
}
