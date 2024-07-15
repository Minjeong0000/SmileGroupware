package smile.office.groupware.admin.vo;

import lombok.Data;

@Data
public class AttendanceDetailVo {
    private String empId;
    private String empName;
    private String date;
    private String startTime;
    private String endTime;
    private Double workTime;
    private String state;
}
