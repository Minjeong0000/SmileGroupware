package smile.office.groupware.admin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.admin.vo.AdminVo;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("SELECT * FROM ADMIN WHERE ADMIN_ID = #{adminId} AND ADMIN_PWD = #{adminPwd}")
    AdminVo login(AdminVo vo);

    @Insert("INSERT INTO ADMIN (ADMIN_NO, ADMIN_ID, ADMIN_PWD, ADMIN_NICK, ADMIN_EMAIL, ADMIN_LEVEL) VALUES (SEQ_ADMIN_NO.NEXTVAL, #{adminId}, #{adminPwd}, #{adminNick}, #{adminEmail}, #{adminLevel})")
    void addAdmin(AdminVo vo);

    //현재는 관리자로 되있지만 나중에 회원으로 바꿀 예정
    @Select("SELECT * FROM ADMIN")
    List<AdminVo> getAdmins();
}
