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

    //오늘의 출퇴근 기록 가져오기
    @Select("SELECT ATT_NO, E.EMP_ID, A.START_TIME, A.END_TIME,A.W_DATE,A.DAY_WORK_TIME FROM ATTENDANCE A JOIN EMPLOYEE E ON A.EMP_ID = E.EMP_ID WHERE E.EMP_ID = #{empId} AND A.W_DATE = TRUNC(SYSDATE)")
    AttendanceVo getTodayAttRecord (String empId);



}
