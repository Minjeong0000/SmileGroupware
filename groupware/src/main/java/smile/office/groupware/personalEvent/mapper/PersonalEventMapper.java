
package smile.office.groupware.personalEvent.mapper;

import org.apache.ibatis.annotations.*;
import smile.office.groupware.personalEvent.vo.PersonalEventVo;

import java.util.List;

@Mapper
public interface PersonalEventMapper {

    @Select("SELECT * FROM PERSONAL_EVENT ")//Todo WHERE EMP_ID = #{empId}이거 붙여서 하면 널값이 나와
    List<PersonalEventVo> getPersonalEventList(String empId);


    @Insert("INSERT INTO PERSONAL_EVENT (PERSONAL_NO, TYPE_NO, EMP_ID, TITLE, CONTENT, LOCATION, DEL_YN, ENROLL_DATE, START_TIME, END_TIME) " +
            "VALUES (SEQ_PERSONAL_EVENT.NEXTVAL, #{typeNo}, #{empId}, #{title}, #{content}, #{location}, 'N', SYSTIMESTAMP, TO_TIMESTAMP(#{startTime}, 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP(#{endTime}, 'YYYY-MM-DD HH24:MI:SS'))")
    int write(PersonalEventVo vo);

    @Update("UPDATE PERSONAL_EVENT SET TITLE = #{title}, CONTENT = #{content}, START_TIME = #{startTime}, END_TIME = #{endTime} , TYPE_NO = #{typeNo} WHERE EMP_ID = #{empId} AND PERSONAL_NO = #{personalNo}")
    int edit(PersonalEventVo vo);


    @Delete("DELETE PERSONAL_EVENT WHERE EMP_ID = #{empId} AND PERSONAL_NO = #{personalNo}")
    int delete(String num);

    @Select({"<script>",
            "SELECT * FROM PERSONAL_EVENT",
            "<where>",
            "    <if test='title != null'>",
            "        AND TITLE = #{title}",
            "    </if>",
            "    <if test='empId != null'>",
            "        AND EMP_ID = #{empId}",
            "    </if>",
            "    <if test='startTime != null and endTime != null'>",
            "        AND START_TIME BETWEEN #{startTime} AND #{endTime}",
            "    </if>",
            "    <if test='content != null'>",
            "        AND CONTENT = #{content}",
            "    </if>",
            "</where>",
            "</script>"})
    List<PersonalEventVo> searchPersonalEvents(PersonalEventVo vo);


}