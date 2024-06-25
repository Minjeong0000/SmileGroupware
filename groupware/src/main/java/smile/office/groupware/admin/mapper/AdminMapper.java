package smile.office.groupware.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.admin.vo.AdminVo;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM ADMIN WHERE ADMIN_ID = #{adminId} AND ADMIN_PWD = #{adminPwd}")
    AdminVo login(AdminVo vo);
}
