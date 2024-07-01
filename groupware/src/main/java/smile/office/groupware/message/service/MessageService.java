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
}
