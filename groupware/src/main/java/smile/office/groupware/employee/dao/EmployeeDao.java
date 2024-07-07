package smile.office.groupware.employee.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.department.vo.DepartmentVo;
import smile.office.groupware.employee.mapper.EmployeeMapper;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeDao {

    private final EmployeeMapper mapper;


    public EmployeeVo login(EmployeeVo vo) {

        return mapper.login(vo);

    }


    public List<DepartmentVo> getAllDepartments() {
        return mapper.getAllDepartments();
    }

    public List<EmployeeVo> getEmployeesByDepartment(String departmentNo) {
        return mapper.getEmployeesByDepartment(departmentNo);
    }


}
