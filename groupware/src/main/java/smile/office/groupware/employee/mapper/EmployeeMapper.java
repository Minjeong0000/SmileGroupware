package smile.office.groupware.employee.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.employee.vo.EmployeeVo;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT EMP_ID,COMPANY_NO,EMP_NAME,ID,PASSWORD,PHONE,EMP_NO,EMAIL,HIRE_DATE,ENT_DATE,REMAIN_VAC_CNT,E.DEPARTMENT_NO,E.ROLE_NO,E.POSITION_NO,PROFILE,D.DEPARTMENT_NAME ,P.POSITION_NAME,R.ROLE_NAME FROM EMPLOYEE E JOIN DEPARTMENTS D ON (E.DEPARTMENT_NO = D.DEPARTMENT_NO) JOIN POSITION P ON (E.POSITION_NO = P.POSITION_NO) JOIN ROLES R ON(E.ROLE_NO = R.ROLE_NO) WHERE ID = #{id} AND  PASSWORD = #{password}")
    EmployeeVo login(EmployeeVo vo);
}
