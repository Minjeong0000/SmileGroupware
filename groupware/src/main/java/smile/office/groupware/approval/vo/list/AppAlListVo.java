package smile.office.groupware.approval.vo.list;

import lombok.Data;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.statuse.vo.StatuseVo;

@Data
public class AppAlListVo {
    private EmployeeVo employeeVo;
    private StatuseVo statuseVo;
}
