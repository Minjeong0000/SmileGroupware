package smile.office.groupware.attendance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.attendance.dao.AttendanceDao;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.page.PageVo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceService {

    private final AttendanceDao dao;

    public List<AttendanceVo> getAttendanceList(String empId) {
        return dao.getAttendanceList(empId);
    }
    public List<AttendanceVo> getAttendanceListHistory(String empId, PageVo pvo) {

        return dao.getAttendanceListHistory(empId,pvo);
    }
    //갯수조회
    public int getTotalAttendanceCount(String empId) {
        return dao.getTotalAttendanceCount(empId);
    }


    public int insertStartTime(String empId) {
        return dao.insertStartTime(empId);
    }

    public int updateEndTime(String empId) {
        return dao.updateEndTime(empId);
    }

    public boolean hasCheckInToday(String empId) {
        // 오늘 날짜에 출근 기록이 있는지 확인
        return dao.getCheckInCountForToday(empId) > 0;
    }

    public boolean hasCheckOutToday(String empId) {
        // 오늘 날짜에 퇴근 기록이 있는지 확인
        return dao.getCheckOutCountForToday(empId) > 0;
    }

    public AttendanceVo getTodayAttRecord(String empId) {

        return dao.getTodayAttRecord(empId);

    }

    public int updateEndTimeAgain(String empId) {
        return dao.updateEndTimeAgain(empId);
    }

    public List<AttendanceVo> getAttendanceHistory(String startDate, String endDate, String empId) {
        return dao.getAttendanceHistory(startDate,endDate,empId);
    }


//    public List<AttendanceVo> getAttendanceList(String empId, int page, int size) {
//
//        int offset = (page - 1) * size;
//        return dao.getAttendanceList(offset, size);
//    }
}
