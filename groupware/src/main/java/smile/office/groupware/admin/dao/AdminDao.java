package smile.office.groupware.admin.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.admin.mapper.AdminMapper;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminDao {

    private final AdminMapper mapper;

    public AdminVo login(AdminVo vo) {
        return mapper.login(vo);
    }

    public List<EmployeeVo> getEmployees() {
        return mapper.getEmployees();
    }

    public int addAdmin(AdminVo vo) throws Exception {
        try {
            System.out.println("DAO layer received admin data: " + vo);
            return mapper.addAdmin(vo);
        } catch (Exception e) {
            // 예외 메시지 출력
            System.err.println("Error while adding admin: " + e.getMessage());
            throw new Exception("관리자 추가 중 오류 발생: " + e.getMessage());
        }
    }

    public List<AdminVo> adminInquiry() {
        return mapper.adminInquiry();
    }

    public int delete(String num) {
        return mapper.delete(num);
    }
}
