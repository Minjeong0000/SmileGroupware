package smile.office.groupware.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.admin.dao.AdminDao;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.department.vo.DepartmentVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.position.vo.PositionVo;
import smile.office.groupware.question.vo.QuestionVo;
import smile.office.groupware.role.vo.RoleVo;

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

    public List<DepartmentVo> getDepartments() {
        return dao.getDepartments();
    }

    public List<RoleVo> getRoles() {
        return dao.getRoles();
    }

    public List<PositionVo> getPositions() {
        return dao.getPositions();
    }



    public EmployeeVo getEmployeeById(String empId) {
        return dao.getEmployeeById(empId);
    }

    //사원 삭제
    public int employeeDelete(String employeeNum) {
        return dao.employeeDelete(employeeNum);
    }

    public List<QuestionVo> getQuestions() {
        List<QuestionVo> questions = dao.getQuestions();
        System.out.println("Fetched questions: " + questions);
        return questions;
    }
}
