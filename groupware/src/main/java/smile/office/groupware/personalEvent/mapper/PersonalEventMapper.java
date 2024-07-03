
package smile.office.groupware.personalEvent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@Mapper
public interface PersonalEventMapper {

    @Select("SELECT * FROM PERSONAL_EVENT ORDER BY START_TIME DESC")
    List<PersonalEventVo> getPersonalEventList();
}