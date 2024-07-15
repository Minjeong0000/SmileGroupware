package smile.office.groupware.admin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.admin.mapper.AdminMapper;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.admin.vo.AttendanceDetailVo;
import smile.office.groupware.attendanceStatistics.vo.AttendanceStatisticsVo;
import smile.office.groupware.department.vo.DepartmentVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.position.vo.PositionVo;
import smile.office.groupware.question.vo.QuestionVo;
import smile.office.groupware.role.vo.RoleVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminMapper mapper;

    //관리자 로그인
    public AdminVo login(AdminVo vo) {
        return mapper.login(vo);
    }

    //사원 리스트
    public List<EmployeeVo> getEmployees() {
        return mapper.getEmployees();
    }

    //관리자 추가
    public int addAdmin(AdminVo vo) throws Exception {
        try {
            System.out.println("DAO layer received admin data: " + vo);
            return mapper.addAdmin(vo);
        } catch (Exception e) {
            // 예외 메시지 출력
            System.err.println("Error while adding admin: " + e.getMessage());
            throw new Exception("관리자 추가 중 오류 발생: " + e.getMessage());
        }
    }

    //관리자 리스트(조회)
    public List<AdminVo> adminInquiry() {
        return mapper.adminInquiry();
    }

    //사원 삭제
    public int delete(String num) {
        return mapper.delete(num);
    }

    //사원 추가
    public int addEmployee(EmployeeVo vo) {
        return mapper.addEmployee(vo);
    }

    public List<DepartmentVo> getDepartments() {
        return mapper.getDepartments();
    }

    public List<RoleVo> getRoles() {
        return mapper.getRoles();
    }

    public List<PositionVo> getPositions() {
        return mapper.getPositions();
    }




    public EmployeeVo getEmployeeById(String empId) {
        return mapper.getEmployeeById(empId);
    }

    //사원 삭제
    public int employeeDelete(String employeeNum) {
        return mapper.employeeDelete(employeeNum);
    }

    // 문의사항 목록 가져오기
    public List<QuestionVo> getQuestions() {
        return mapper.getQuestions();
    }

    public List<AttendanceStatisticsVo> getAttendanceStatistics() {
        return mapper.getAttendanceStatistics();
    }

    public List<AttendanceDetailVo> getAttendanceDetails(String empId) {
        return mapper.getAttendanceDetails(empId);
    }

}
