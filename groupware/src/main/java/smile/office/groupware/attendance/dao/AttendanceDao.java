package smile.office.groupware.attendance.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.attendance.mapper.AttendanceMapper;
import smile.office.groupware.attendance.vo.AttendanceVo;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class AttendanceDao {
    private final AttendanceMapper mapper;


    public List<AttendanceVo> getAttendanceCal(String empId) {

        return mapper.getAttendanceCal(empId);


    }

    public int insertStartTime(String empId) {
        return mapper.insertStartTime(empId);
    }

    public int updateEndTime(String empId) {
        return mapper.updateEndTime(empId);
    }
}
