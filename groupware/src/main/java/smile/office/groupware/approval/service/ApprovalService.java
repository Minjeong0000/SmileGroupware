package smile.office.groupware.approval.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.approval.dao.ApprovalDao;
import smile.office.groupware.approval.vo.*;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.approval.vo.write.WriteVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.prioritie.vo.PrioritieVo;
import smile.office.groupware.vacCate.vo.VacCateVo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApprovalService {
    private final ApprovalDao dao;
    public ApprovalHomeVo getApprovalHome(String empId) {
        ApprovalHomeVo approvalHomeVo = new ApprovalHomeVo();

        CntAppVo cntAppVo=dao.getCntApp(empId);
        TemCntVo temCntVo=dao.getTemCnt(empId);
        AppCntVo appCntVo=dao.getAppCnt(empId);
        List<ApprovalListVo> approvalListVo=dao.getAppList(empId);
        List<ApprovalProcessVo> approvalProcessVoList=dao.getAppProList(empId);

        approvalHomeVo.setCntAppVo(cntAppVo);
        approvalHomeVo.setTemCntVo(temCntVo);
        approvalHomeVo.setAppCntVo(appCntVo);
        approvalHomeVo.setApprovalListVoList(approvalListVo);
        approvalHomeVo.setApprovalProcessVoList(approvalProcessVoList);

        return approvalHomeVo;
    }

    public WriteVo getApprovalWrite(EmployeeVo loginEmployeeVo) {
        WriteVo writeVo=new WriteVo();
        List<VacCateVo> vacCateVoList=dao.getVacCateVoList();
        List<PrioritieVo> prioritieVoList=dao.getPriVoList();

        List<EmployeeVo> employeeVoList1=dao.getEmpVoList1(loginEmployeeVo);
        List<EmployeeVo> employeeVoList2=dao.getEmpVoList2(loginEmployeeVo);
        List<EmployeeVo> employeeVoList3=dao.getEmpVoList3(loginEmployeeVo);

        writeVo.setVacCateVo(vacCateVoList);
        writeVo.setPrioritieVo(prioritieVoList);

        writeVo.setEmployeeVo1(employeeVoList1);
        writeVo.setEmployeeVo2(employeeVoList2);
        writeVo.setEmployeeVo3(employeeVoList3);
        return writeVo;
    }

    public void saveApproval(AppVacVo appVacVo) {
        appVacVo.setStatusNo(4);
        dao.saveApproval(appVacVo);
    }

    public void submitApproval(AppVacVo appVacVo) {
        appVacVo.setStatusNo(1);
        dao.submitApproval(appVacVo);
    }
}