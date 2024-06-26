package smile.office.groupware.attendance.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.attendance.vo.AttendanceVo;

import java.util.List;
@Mapper
public interface AttendanceMapper {


    @Select("SELECT * FROM ATTENDANCE WHERE EMP_ID = #{empId}")
    List<AttendanceVo> getAttendanceCal(String empId);


}
