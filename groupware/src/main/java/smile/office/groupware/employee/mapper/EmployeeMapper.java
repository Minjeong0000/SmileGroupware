package smile.office.groupware.employee.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.employee.vo.EmployeeVo;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM EMPLOYEE WHERE ID = #{id} AND  PASSWORD = #{password}")
    EmployeeVo login(EmployeeVo vo);
}
