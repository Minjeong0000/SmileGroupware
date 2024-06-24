package smile.office.groupware.personalEvent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import smile.office.groupware.personalEvent.mapper.PersonalEventMapper;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@Repository
public class PersonalEventDao {

    @Autowired
    private PersonalEventMapper mapper;

    public List<PersonalEventVo> getPersonalList() {
        return mapper.getPersonalList();
    }

    public int personalEnroll(PersonalEventVo vo) {
        return mapper.personalEnroll(vo);
    }
}
