package smile.office.groupware.attendance.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;
@Mapper
public interface AttendanceMapper {


    @Select("SELECT * FROM ATTENDANCE WHERE EMP_ID = #{empId}")
    List<AttendanceVo> getAttendanceCal(String empId);



    @Insert("INSERT INTO ATTENDANCE ( ATT_NO, EMP_ID, W_DATE ) VALUES ( SEQ_ATTENDANCE.NEXTVAL,#{vo.empId}, TRUNC(SYSDATE)")
    void insertStartTime(EmployeeVo vo);
}
