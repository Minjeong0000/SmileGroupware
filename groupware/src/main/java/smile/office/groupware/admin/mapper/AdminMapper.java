package smile.office.groupware.admin.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.department.vo.DepartmentVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.position.vo.PositionVo;
import smile.office.groupware.question.vo.QuestionVo;
import smile.office.groupware.role.vo.RoleVo;

import java.util.List;

@Mapper
public interface AdminMapper {

    //관리자 로그인
    @Select("SELECT * FROM ADMIN " +
            "WHERE ADMIN_ID = #{adminId} " +
            "AND ADMIN_PWD = #{adminPwd}")
    AdminVo login(AdminVo vo);

    //관리자 추가
    @Insert("INSERT INTO ADMIN (ADMIN_NO, ADMIN_ID, ADMIN_PWD, ADMIN_NICK, ADMIN_EMAIL, ADMIN_LEVEL) " +
            "VALUES (SEQ_ADMIN_NO.NEXTVAL, #{adminId}, #{adminPwd}, #{adminNick}, #{adminEmail}, #{adminLevel})")
    int addAdmin(AdminVo vo);

    //관리자 조회
    @Select("SELECT * FROM ADMIN")
    List<AdminVo> adminInquiry();

    //회원삭제
    @Delete("DELETE ADMIN WHERE ADMIN_NO = #{num}")
    int delete(@RequestParam("num") String num);

    //회원추가
    @Insert("INSERT INTO EMPLOYEE (EMP_ID, COMPANY_NO, EMP_NAME, ID, PASSWORD, PHONE, EMP_NO, EMAIL, HIRE_DATE, DEPARTMENT_NO, ROLE_NO, POSITION_NO, PROFILE) " +
            "VALUES (SEQ_EMPLOYEE_ID.NEXTVAL, #{companyNo}, #{empName}, #{id}, #{password}, #{phone}, #{empNo}, #{email}, #{hireDate}, #{departmentNo}, #{roleNo}, #{positionNo}, #{profile})")
    int addEmployee(EmployeeVo vo);

    //부서추가
    @Select("SELECT * FROM DEPARTMENTS")
    List<DepartmentVo> getDepartments();

    //역할추가
    @Select("SELECT * FROM ROLES")
    List<RoleVo> getRoles();

    //직위추가
    @Select("SELECT * FROM POSITION")
    List<PositionVo> getPositions();

    ///////////////////////////////////////////////////////


    //EMPLOYEE, DEPARTMENTS, POSITION, ROLES 연결해버림!
    @Select("SELECT e.emp_id AS empId, " +
            "       e.emp_name AS empName, " +
            "       e.email, " +
            "       e.phone, " +
            "       p.position_name AS positionName, " +
            "       r.role_name AS roleName, " +
            "       e.profile " +
            "FROM employee e " +
            "JOIN position p ON e.position_no = p.position_no " +
            "JOIN roles r ON e.role_no = r.role_no " +
            "WHERE e.emp_id = #{empId}")
    EmployeeVo getEmployeeById(String empId);

    //회원조회
    @Select("SELECT e.emp_id AS empId, " +
            "       e.emp_name AS empName, " +
            "       e.email, " +
            "       e.phone, " +
            "       p.position_name AS positionName, " +
            "       r.role_name AS roleName, " +
            "       e.profile " +
            "FROM employee e " +
            "JOIN position p ON e.position_no = p.position_no " +
            "JOIN roles r ON e.role_no = r.role_no " +
            "WHERE e.ent_yn = 'N' " +
            "ORDER BY e.emp_id ASC")
    List<EmployeeVo> getEmployees();

    @Update("UPDATE EMPLOYEE SET ENT_YN = 'Y' WHERE EMP_ID = #{employeeNum}")
    int employeeDelete(String employeeNum);

    // 문의사항 목록 가져오기
    @Select("SELECT * FROM QUESTION")
    List<QuestionVo> getQuestions();

}
