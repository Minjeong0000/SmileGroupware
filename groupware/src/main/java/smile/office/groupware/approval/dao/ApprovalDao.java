package smile.office.groupware.approval.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.approval.mapper.ApprovalMapper;
import smile.office.groupware.approval.vo.*;
import smile.office.groupware.approval.vo.list.AppAlListVo;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.prioritie.vo.PrioritieVo;
import smile.office.groupware.vacCate.vo.VacCateVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApprovalDao {
    private final ApprovalMapper mapper;

    public CntAppVo getCntApp(String empId) {
        return mapper.getCntApp(empId);
    }

    public TemCntVo getTemCnt(String empId) {
        return mapper.getTemCnt(empId);
    }

    public AppCntVo getAppCnt(String empId) {
        return mapper.getAppCnt(empId);
    }

    public List<ApprovalListVo> getAppList(String empId) {
        return mapper.getAppList(empId);
    }

    public List<ApprovalProcessVo> getAppProList(String empId) {
        return mapper.getAppProList(empId);
    }

    public List<VacCateVo> getVacCateVoList() {
        return mapper.getVacCateVoList();
    }

    public List<PrioritieVo> getPriVoList() {
        return mapper.getPriVoList();
    }

    public List<EmployeeVo> getEmpVoList2(EmployeeVo loginEmployeeVo) {
        return mapper.getEmpVoList2(loginEmployeeVo);
    }

    public List<EmployeeVo> getEmpVoList3(EmployeeVo loginEmployeeVo) {
        return mapper.getEmpVoList3(loginEmployeeVo);
    }

    public List<EmployeeVo> getEmpVoList1(EmployeeVo loginEmployeeVo) {
        return mapper.getEmpVoList1(loginEmployeeVo);
    }

    public void saveApproval(AppVacVo appVacVo) {
        mapper.saveApproval(appVacVo);
    }

    public void submitApproval(AppVacVo appVacVo) {
        mapper.submitApprobal(appVacVo);
    }

    public List<ApprovalListVo> getAppListIng(String empId) {
        return mapper.getAppListIng(empId);
    }

    public List<AppAlListVo> getApprovalLines(int approvalNo) {
        return mapper.getApprovalLines(approvalNo);
    }

    public void getSendMassage(String empId, String empNo, String message) {
        mapper.getSendMassage(empId,empNo,message);
    }

    public List<ApprovalListVo> getAppListIngRes(String empId) {
        return mapper.getAppListIngRes(empId);
    }
}