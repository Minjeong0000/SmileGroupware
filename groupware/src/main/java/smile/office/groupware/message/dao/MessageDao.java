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
}
