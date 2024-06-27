package smile.office.groupware.attendance.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;
@Mapper
public interface AttendanceMapper {

    @Select("SELECT * FROM ATTENDANCE WHERE EMP_ID = #{empId}")
    List<AttendanceVo> getAttendanceList(String empId);

    @Insert("INSERT INTO ATTENDANCE (ATT_NO, EMP_ID, W_DATE) VALUES (SEQ_ATTENDANCE.NEXTVAL, #{empId}, TRUNC(SYSDATE))")
    int insertStartTime(String empId);

    @Update("UPDATE ATTENDANCE SET END_TIME = SYSDATE WHERE EMP_ID = #{empId} AND W_DATE = TRUNC(SYSDATE) AND END_TIME IS NULL")
    int updateEndTime(String empId);

    @Select("SELECT COUNT(*) FROM ATTENDANCE WHERE EMP_ID = #{empId} AND W_DATE = TRUNC(SYSDATE) AND START_TIME IS NOT NULL")
    int getCheckInCountForToday(String empId);

    @Select("SELECT COUNT(*) FROM ATTENDANCE WHERE EMP_ID = #{empId} AND W_DATE = TRUNC(SYSDATE) AND END_TIME IS NOT NULL")
    int getCheckOutCountForToday(String empId);
}
