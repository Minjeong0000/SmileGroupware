package smile.office.groupware.attendance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.attendance.dao.AttendanceDao;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceService {

    private final AttendanceDao dao;


    public List<AttendanceVo> getAttendanceCal(String empId) {
        return dao.getAttendanceCal(empId);
    }

    public void insertStartTime(EmployeeVo vo) {
        return dao.insertStartTime(vo);

    }
}
