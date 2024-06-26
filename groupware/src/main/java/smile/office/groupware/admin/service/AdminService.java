package smile.office.groupware.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.office.groupware.admin.dao.AdminDao;
import smile.office.groupware.admin.vo.AdminVo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminDao dao;

    public AdminVo login(AdminVo vo) {
        return dao.login(vo);
    }

    public void addAdmin(AdminVo vo) {
        dao.addAdmin(vo);
    }

    public List<AdminVo> getAdmins() {
        return dao.getAdmins();
    }
}
