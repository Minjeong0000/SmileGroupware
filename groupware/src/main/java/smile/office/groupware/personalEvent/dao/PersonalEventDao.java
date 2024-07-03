package smile.office.groupware.personalEvent.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import smile.office.groupware.personalEvent.mapper.PersonalEventMapper;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonalEventDao {

    private final PersonalEventMapper mapper;

    public List<PersonalEventVo> getPersonalEventList() {
        return mapper.getPersonalEventList();
    }
}
