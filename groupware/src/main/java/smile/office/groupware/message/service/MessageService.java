package smile.office.groupware.message.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
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


    //메세지 영구삭제
    public int deleteMsg(String empId,List<String> msgList) {
        return dao.deleteMsg(empId,msgList);

    }

    //상세조회
    public MessageVo getMsgByNo(String num) {
        return dao.getMsgByNo(num);
    }


}
