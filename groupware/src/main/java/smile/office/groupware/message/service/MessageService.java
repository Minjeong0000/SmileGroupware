package smile.office.groupware.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.message.dao.MessageDao;
import smile.office.groupware.message.vo.MessageVo;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageDao dao;

    public List<MessageVo> getReceiveMessageList(String empId) {

        return dao.getReceiveMessageList(empId);
    }

    public List<MessageVo> getImportantMsglist(String empId) {
        return dao.getImportantMsglist(empId);

    }

    public List<MessageVo> getTrashMsgList(String empId) {
        return dao.getTrashMsgList(empId);
    }

    public List<MessageVo> getSentMsgList(String empId) {
        return dao.getSentMsgList(empId);

    }
    //읽음으로 변경(여러개)
    public int updateReadStatus(String empId,List<String> msgList) {
        return dao.updateReadStatus(empId,msgList);
    }
    //휴지통 이동(여러개)
    public int updateForderStatusTrash(String empId,List<String> msgList) {
        return dao.updateForderStatusTrash(empId,msgList);
    }
    //중요쪽지처리(여러개)
//    public int updateForderStatusImportant(String empId, List<String> msgList) {
//        return dao.updateForderStatusImportant(empId,msgList);
//    }

    //메세지 영구삭제
    public int deleteMsg(String empId,List<String> msgList) {
        return dao.deleteMsg(empId,msgList);

    }

    //상세조회
    public MessageVo getMsgByNo(String empId, String num) {

        return dao.getMsgByNo(empId,num);
    }

    public int readMessage(String empId, String num) {
        return dao.readMessage(empId,num);
    }



    public int insertMessage(String senderNo, MessageVo msgVo) {
            return dao.insertMessage(senderNo,msgVo);

    }

    public int insertSenderMessage(String senderNo) {
        return dao.insertSenderMessage(senderNo);
    }

    public int insertReceiverMessage(MessageVo msgVo) {
        return dao.insertReceiverMessage(msgVo);
    }
}
