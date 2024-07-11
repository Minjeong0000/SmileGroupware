package smile.office.groupware.approval.vo.write;

import lombok.Data;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.prioritie.vo.PrioritieVo;
import smile.office.groupware.vacCate.vo.VacCateVo;

import java.util.List;

@Data
public class WriteVo {
    private List<VacCateVo> vacCateVo;
    private List<PrioritieVo> prioritieVo;
    private List<EmployeeVo> employeeVo1;
    private List<EmployeeVo> employeeVo2;
    private List<EmployeeVo> employeeVo3;
}