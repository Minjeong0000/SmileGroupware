package smile.office.groupware.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.office.groupware.department.vo.DepartmentVo;
import smile.office.groupware.employee.dao.EmployeeDao;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeDao dao;


    public EmployeeVo login(EmployeeVo vo) {
        return dao.login(vo);
    }

    public List<DepartmentVo> getAllDepartments() {
        return dao.getAllDepartments();
    }

    public List<EmployeeVo> getEmployeesByDepartment(String departmentNo) {
        return dao.getEmployeesByDepartment(departmentNo);
    }



}
