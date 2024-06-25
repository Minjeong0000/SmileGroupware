package smile.office.groupware.admin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.admin.mapper.AdminMapper;
import smile.office.groupware.admin.vo.AdminVo;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminMapper mapper;

    public AdminVo login(AdminVo vo) {

        return mapper.login(vo);
    }
}
