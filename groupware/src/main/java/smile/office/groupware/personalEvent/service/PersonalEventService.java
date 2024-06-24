package smile.office.groupware.personalEvent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smile.office.groupware.personalEvent.dao.PersonalEventDao;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@Service
public class PersonalEventService {

    @Autowired
    private PersonalEventDao dao;

    public List<PersonalEventVo> getPersonalList() {
      //dao
        return dao.getPersonalList();
    }

    public int personalEnroll(PersonalEventVo vo) {
        return dao.personalEnroll(vo);
    }
}
