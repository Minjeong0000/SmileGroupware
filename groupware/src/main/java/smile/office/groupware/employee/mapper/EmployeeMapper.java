package smile.office.groupware.employee.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.department.vo.DepartmentVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT EMP_ID,COMPANY_NO,EMP_NAME,ID,PASSWORD,PHONE,EMP_NO,EMAIL,HIRE_DATE,ENT_DATE,REMAIN_VAC_CNT,E.DEPARTMENT_NO,E.ROLE_NO,E.POSITION_NO,PROFILE,D.DEPARTMENT_NAME ,P.POSITION_NAME,R.ROLE_NAME FROM EMPLOYEE E JOIN DEPARTMENTS D ON (E.DEPARTMENT_NO = D.DEPARTMENT_NO) JOIN POSITION P ON (E.POSITION_NO = P.POSITION_NO) JOIN ROLES R ON(E.ROLE_NO = R.ROLE_NO) WHERE ID = #{id} AND  PASSWORD = #{password}")
    EmployeeVo login(EmployeeVo vo);


    @Select("SELECT DEPARTMENT_NO, DEPARTMENT_NAME FROM DEPARTMENTS")
    List<DepartmentVo> getAllDepartments();

    @Select("SELECT E.EMP_ID, E.EMP_NAME, R.ROLE_NAME, D.DEPARTMENT_NAME FROM EMPLOYEE E JOIN DEPARTMENTS D ON E.DEPARTMENT_NO = D.DEPARTMENT_NO JOIN ROLES R ON E.ROLE_NO = R.ROLE_NO WHERE D.DEPARTMENT_NO = #{departmentNo}")
    List<EmployeeVo> getEmployeesByDepartment(@Param("departmentNo") String departmentNo);
}


