package smile.office.groupware.message.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
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

    public int updateReadStatus(List<String>msgList) {
        return mapper.updateReadStatus(msgList);
    }

    public MessageVo getMsgByNo(String num) {
        return mapper.getMsgByNo(num);
    }


    public int updateForderStatusTrash(List<String> msgList) {

    return mapper.updateForderStatusTrash(msgList);
    }
}
