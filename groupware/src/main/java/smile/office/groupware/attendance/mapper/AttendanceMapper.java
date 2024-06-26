package smile.office.groupware.attendance.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import smile.office.groupware.attendance.vo.AttendanceVo;

import java.util.List;
@Mapper
public interface AttendanceMapper {


    @Select("SELECT * FROM ATTENDANCE WHERE EMP_ID = #{empId}")
    List<AttendanceVo> getAttendanceCal(String empId);
    //출근버튼 클릭시 출근시간 기록
    @Insert("INSERT INTO ATTENDANCE ( ATT_NO, EMP_ID, W_DATE ) VALUES ( SEQ_ATTENDANCE.NEXTVAL, #{empId}, TRUNC(SYSDATE) )")
    int insertStartTime(String empId);

    //퇴근버튼 클릭시 퇴근시간 null->sysdate업데이트
    @Update("UPDATE ATTENDANCE SET END_TIME = SYSDATE WHERE EMP_ID = #{empID} AND W_DATE = TRUNC(SYSDATE) AND END_TIME IS NULL")
    int updateEndTime(String empId);
}
