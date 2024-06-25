package smile.office.groupware.employee.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.employee.mapper.EmployeeMapper;
import smile.office.groupware.employee.vo.EmployeeVo;

@Repository
@RequiredArgsConstructor
public class EmployeeDao {

    private final EmployeeMapper mapper;


    public EmployeeVo login(EmployeeVo vo) {

        return mapper.login(vo);

    }
}
