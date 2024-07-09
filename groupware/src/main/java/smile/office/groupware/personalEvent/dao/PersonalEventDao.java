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

    public List<PersonalEventVo> getPersonalEventList(String empId) {
        System.out.println("dao empId = " + empId);
        return mapper.getPersonalEventList(empId);
    }

    public int write(PersonalEventVo vo) {
        return mapper.write(vo);
    }

    public int edit(PersonalEventVo vo) {
        return mapper.edit(vo);
    }

    public int delete(String num) {
        return mapper.delete(num);
    }

    public List<PersonalEventVo> searchPersonalEvents(PersonalEventVo vo) {
        return mapper.searchPersonalEvents(vo);
    }
}
