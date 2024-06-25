package smile.office.groupware.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.office.groupware.employee.dao.EmployeeDao;
import smile.office.groupware.employee.vo.EmployeeVo;

@Service
@RequiredArgsConstructor

public class EmployeeService {

    private final EmployeeDao dao;


    public EmployeeVo login(EmployeeVo vo) {
        return dao.login(vo);
    }
}
