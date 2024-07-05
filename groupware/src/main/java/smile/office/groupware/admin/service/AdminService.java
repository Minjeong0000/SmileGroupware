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

    //관리자 로그인
    public AdminVo login(AdminVo vo) {
        return dao.login(vo);
    }

    //사원 리스트
    public List<EmployeeVo> getEmployees() {
        return dao.getEmployees();
    }

    //관리자 추가
    public int addAdmin(AdminVo vo) throws Exception {
        System.out.println("관리자: " + vo);
        return dao.addAdmin(vo);
    }

    //관리자 리스트(조회)
    public List<AdminVo> adminInquiry() {
        return dao.adminInquiry();
    }

    //관리자 삭제
    public int delete(String num) {
        return dao.delete(num);
    }

    //사원 추가 
    public int addEmployee(EmployeeVo vo) throws Exception{
        return dao.addEmployee(vo);
    }
    
    public EmployeeVo getEmployeeById(String empId) {
        return dao.getEmployeeById(empId);
    }

    //사원 삭제
    public int employeeDelete(String employeeNum) {
        return dao.employeeDelete(employeeNum);
    }
}
