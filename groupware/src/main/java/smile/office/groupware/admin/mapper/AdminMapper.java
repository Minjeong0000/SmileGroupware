package smile.office.groupware.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@Mapper
public interface AdminMapper {

    //관리자 로그인
    @Select("SELECT * FROM ADMIN " +
            "WHERE ADMIN_ID = #{adminId} " +
            "AND ADMIN_PWD = #{adminPwd}")
    AdminVo login(AdminVo vo);

    //회원 조회
    @Select("SELECT * FROM EMPLOYEE")
    List<EmployeeVo> getEmployees();

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

    //EMPLOYEE, DEPARTMENTS, POSITION, ROLES 연결해버림!
    @Select("SELECT e.*, d.DEPARTMENT_NAME, p.POSITION_NAME, r.ROLE_NAME " +
            "FROM EMPLOYEE e " +
            "JOIN DEPARTMENTS d ON e.DEPARTMENT_NO = d.DEPARTMENT_NO " +
            "JOIN POSITION p ON e.POSITION_NO = p.POSITION_NO " +
            "JOIN ROLES r ON e.ROLE_NO = r.ROLE_NO " +
            "WHERE e.EMP_ID = #{empId}")
    EmployeeVo getEmployeeById(String empId);



}
