package smile.office.groupware.admin.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.WebResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import smile.office.groupware.admin.dao.AdminDao;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final AdminDao dao;

    public AdminVo login(AdminVo vo) {
        return dao.login(vo);
    }

    public List<EmployeeVo> getEmployees() {
        return dao.getEmployees();
    }

    public int addAdmin(AdminVo vo) throws Exception {
        System.out.println("Service layer received admin data: " + vo);
        return dao.addAdmin(vo);
    }

    public List<AdminVo> adminInquiry() {
        return dao.adminInquiry();
    }


    public int delete(String num) {
        return dao.delete(num);
    }

    public int addEmployee(EmployeeVo vo) throws Exception{
        return dao.addEmployee(vo);
    }

    public EmployeeVo getEmployeeById(String empId) {
        return dao.getEmployeeById(empId);
    }

}
