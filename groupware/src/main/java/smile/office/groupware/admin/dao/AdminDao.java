package smile.office.groupware.admin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.admin.mapper.AdminMapper;
import smile.office.groupware.admin.vo.AdminVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminMapper mapper;

    public AdminVo login(AdminVo vo) {
        return mapper.login(vo);
    }

    public void addAdmin(AdminVo vo) {
        mapper.addAdmin(vo);
    }

    public List<AdminVo> getAdmins() {
        return mapper.getAdmins();
    }
}
