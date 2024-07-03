
package smile.office.groupware.personalEvent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@Mapper
public interface PersonalEventMapper {

    @Select("SELECT * FROM PERSONAL_EVENT ")//Todo WHERE EMP_ID = #{empId}이거 붙여서 하면 널값이 나와
    List<PersonalEventVo> getPersonalEventList();


    @Insert("INSERT INTO PERSONAL_EVENT (PERSONAL_NO, TYPE_NO, EMP_ID, TITLE, CONTENT, LOCATION, DEL_YN, ENROLL_DATE, START_TIME, END_TIME) " +
            "VALUES (SEQ_PERSONAL_EVENT.NEXTVAL, #{typeNo}, #{empId}, #{title}, #{content}, #{location}, 'N', SYSTIMESTAMP, TO_TIMESTAMP(#{startTime}, 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(#{endTime}, 'YYYY-MM-DD HH24:MI:SS'))")
    int write(PersonalEventVo vo);
}