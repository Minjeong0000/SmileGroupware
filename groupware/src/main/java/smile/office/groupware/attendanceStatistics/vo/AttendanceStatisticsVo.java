package smile.office.groupware.attendanceStatistics.vo;

import lombok.Data;
import smile.office.groupware.admin.vo.AttendanceDetailVo;

import java.util.List;

@Data
public class AttendanceStatisticsVo {
    private String empId;
    private String empName;
    private Double totalWorkTime;
    private List<AttendanceDetailVo> details;
}
