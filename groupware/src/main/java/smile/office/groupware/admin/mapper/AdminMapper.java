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

    @Select("SELECT * FROM ADMIN WHERE ADMIN_ID = #{adminId} AND ADMIN_PWD = #{adminPwd}")
    AdminVo login(AdminVo vo);

    @Select("SELECT * FROM EMPLOYEE")
    List<EmployeeVo> getEmployees();


    @Insert("INSERT INTO ADMIN (ADMIN_NO, ADMIN_ID, ADMIN_PWD, ADMIN_NICK, ADMIN_EMAIL, ADMIN_LEVEL) VALUES (SEQ_ADMIN_NO.NEXTVAL, #{adminId}, #{adminPwd}, #{adminNick}, #{adminEmail}, #{adminLevel})")
    int addAdmin(AdminVo vo);

    //현재는 관리자로 되있지만 나중에 회원으로 바꿀 예정
    @Select("SELECT * FROM ADMIN")
    List<AdminVo> adminInquiry();

    @Delete("DELETE ADMIN WHERE ADMIN_NO = #{num}")
    int delete(@RequestParam("num") String num);

}
