package smile.office.groupware.attendance.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.attendance.mapper.AttendanceMapper;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class AttendanceDao {

    private final AttendanceMapper mapper;

    public List<AttendanceVo> getAttendanceList(String empId) {
        return mapper.getAttendanceList(empId);
    }

    public int insertStartTime(String empId) {
        return mapper.insertStartTime(empId);
    }

    public int updateEndTime(String empId) {
        return mapper.updateEndTime(empId);
    }
    public int updateEndTimeAgain(String empId) {
        return mapper.updateEndTimeAgain(empId);
    }

    public int getCheckInCountForToday(String empId) {
        return mapper.getCheckInCountForToday(empId);
    }

    public int getCheckOutCountForToday(String empId) {
        return mapper.getCheckOutCountForToday(empId);
    }

    public AttendanceVo getTodayAttRecord(String empId) {
        return mapper.getTodayAttRecord(empId);
    }


    public List<AttendanceVo> getAttendanceHistory(String startDate, String endDate, String empId) {
        return mapper.getAttendanceHistory(startDate,endDate,empId);
    }

//    public int getTotalAttendanceCount(String empId) {
//
//        return mapper.getTotalAttendanceCount(empId);
//
//    }
//
//    public List<AttendanceVo> getAttendanceList(int offset, int size) {
//        return mapper.getAttendanceList(offset,size);
//    }
}
