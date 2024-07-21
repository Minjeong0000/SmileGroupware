package smile.office.groupware.approval.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.approval.mapper.ApprovalMapper;
import smile.office.groupware.approval.vo.*;
import smile.office.groupware.approval.vo.list.AlLVo;
import smile.office.groupware.approval.vo.list.AlListResVo;
import smile.office.groupware.approval.vo.list.AppAlListVo;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.approvalLine.vo.ApprovalLineVo;
import smile.office.groupware.approvalResponse.vo.ApprovalResponseVo;
import smile.office.groupware.documentTemplate.vo.DocumentTemplateVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.prioritie.vo.PrioritieVo;
import smile.office.groupware.vacCate.vo.VacCateVo;
import smile.office.groupware.vacationTemplate.vo.VacationTemplateVo;

import java.net.URL;
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



    public String getLineNo(String appNo, String empId) {
        return mapper.getLineNo(appNo,empId);
    }

    public void insertAppAl(String lineNo, String signUrl, String responseText, String empId) {
        mapper.insertAppAl(lineNo,signUrl,responseText,empId);
    }

    public void updateAl(String lineNo) {
        mapper.updateAl(lineNo);
    }

    public List<AlListResVo> getApprovalLine(String approvalNo) {
        return mapper.getApprovalLine(approvalNo);
    }


    public List<ApprovalResponseVo> getResponseAl(List<String> approvalLineNos) {
        return mapper.getResponseAl(approvalLineNos);
    }

    public ApprovalVo getApprovalVo(String approvalNo) {
        return mapper.getApprovalVo(approvalNo);
    }

    public VacationTemplateVo getVacTempVo(String approvalNo) {
        return mapper.getVacTempVo(approvalNo);
    }

    public DocumentTemplateVo getDocTempVo(String approvalNo) {
        return mapper.getDocTempVo(approvalNo);
    }

    public String al(String empId, String approvalNo) {
        return mapper.al(empId,approvalNo);
    }

    public void alDes(String empId, String approvalNo) {
        mapper.alDes(empId,approvalNo);
    }

    public void setAlRes(String al, String response) {
        mapper.setAlRes(al,response);
    }

    public void aDes(String approvalNo) {
        mapper.aDes(approvalNo);
    }

    public String getSeq(String appNo, String empId) {
        return mapper.getSeq(appNo,empId);
    }

    public void updateA(String appNo) {
        mapper.updateA(appNo);
    }

    public List<ApprovalListVo> getAppListIngResIng(String empId) {
        return mapper.getAppListIngResIng(empId);
    }

    public ApprovalVo getVoApp(String approvalNo) {
        return mapper.getVoApp(approvalNo);
    }

    public void getDelApp(String approvalNo) {
        mapper.getDelApp(approvalNo);
    }

    public void getDelApp2(String approvalNo) {
        mapper.getDelApp2(approvalNo);
    }

    public List<ApprovalListVo> getAppListIng2(String empId) {
        return mapper.getAppListIng2(empId);
    }

    public List<ApprovalListVo> getAppListIngSea(String empId, String typeApp, String status) {
        return mapper.getAppListIngSea(empId,typeApp,status);
    }

    public List<ApprovalListVo> getAppListIngAll(String empId) {
        return mapper.getAppListIngAll(empId);
    }

    public List<EmployeeVo> getEmpVo() {
        return mapper.getEmpVo();
    }

    public List<ApprovalListVo> getAppListIngAllSearch(String typeApp, String emp, String empId) {
        return mapper.getAppListIngAllSearch(typeApp,emp,empId);
    }

    public List<EmployeeVo> getHighEmpVo(String empId, String empDept) {
        return mapper.getHighEmpVo(empId,empDept);
    }

    public List<ApprovalListVo> getAppListIngResSearch(String empId, String pro) {
        return mapper.getAppListIngResSearch(empId,pro);
    }

    public String getVacType(String appNo) {
        return mapper.getVacType(appNo);
    }

    public String getUseCnt(String appNo) {
        return mapper.getUseCnt(appNo);
    }

    public void updateAppUse(String apEmp, String use) {
        mapper.updateAppUse(apEmp,use);
    }

    public String getApEmp(String appNo) {
        return mapper.getApEmp(appNo);
    }
}