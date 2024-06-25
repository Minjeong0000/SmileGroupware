package smile.office.groupware.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.office.groupware.admin.dao.AdminDao;
import smile.office.groupware.admin.vo.AdminVo;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminDao dao;

    public AdminVo login(AdminVo vo) {
        return dao.login(vo);
    }
}
