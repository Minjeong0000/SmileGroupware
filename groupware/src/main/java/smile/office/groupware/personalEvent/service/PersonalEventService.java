package smile.office.groupware.personalEvent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.personalEvent.dao.PersonalEventDao;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PersonalEventService {

    private final PersonalEventDao dao;

    public List<PersonalEventVo> getPersonalEventList(String empId) {
        return dao.getPersonalEventList(empId);
    }

    public int write(PersonalEventVo vo) {
        return dao.write(vo);
    }


    public int edit(PersonalEventVo vo) {
        return dao.edit(vo);
    }

    public int delete(String num) {
        return dao.delete(num);
    }

    public List<PersonalEventVo> searchPersonalEvents(PersonalEventVo vo) {
        return dao.searchPersonalEvents(vo);
    }
}
