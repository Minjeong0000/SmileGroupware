package smile.office.groupware.approval.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.approval.dao.ApprovalDao;
import smile.office.groupware.approval.vo.*;
import smile.office.groupware.approval.vo.list.*;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.approval.vo.write.WriteVo;
import smile.office.groupware.approvalLine.vo.ApprovalLineVo;
import smile.office.groupware.approvalResponse.vo.ApprovalResponseVo;
import smile.office.groupware.documentTemplate.vo.DocumentTemplateVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.prioritie.vo.PrioritieVo;
import smile.office.groupware.vacCate.vo.VacCateVo;
import smile.office.groupware.vacationTemplate.vo.VacationTemplateVo;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

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

    public ListApprovalVo getlistApprovalVo(String empId) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIng(empId);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public List<AppAlListVo> getApprovalLines(int approvalNo) {
        return dao.getApprovalLines(approvalNo);
    }

    public void getSendMassage(String empId, String empNo, String message) {
        dao.getSendMassage(empId,empNo,message);
    }

    public ListApprovalVo getlistApprovalVoRes(String empId) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIngRes(empId);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public void insertAppAl(String responseText, String appNo, String signUrl, String empId) {
        String lineNo=dao.getLineNo(appNo,empId);
        String seq=dao.getSeq(appNo,empId);
        String vac=dao.getVacType(appNo);
        String apEmp=dao.getApEmp(appNo);
        String use=dao.getUseCnt(appNo);
        if(seq.equals("3")){
            System.out.println("lineNo = " + lineNo);
            dao.updateAl(lineNo);
            dao.insertAppAl(lineNo,signUrl,responseText,empId);
            dao.updateA(appNo);
            if(vac.equals("휴가")){
                dao.updateAppUse(apEmp,use);
            }
        }else{
            System.out.println("lineNo = " + lineNo);
            dao.updateAl(lineNo);
            dao.insertAppAl(lineNo,signUrl,responseText,empId);
        }

    }

    public List<AlListResVo> getAlLisetResVos(String approvalNo) {
        AlListResVo alListResVo = new AlListResVo();
        List<AlListResVo> approvalLineVos=dao.getApprovalLine(approvalNo);

        return  approvalLineVos;
    }

    public List<ApprovalResponseVo> getResponseAl(List<AlListResVo> alListResVos) {
        // approvalLineNo만 추출하여 리스트 생성
        List<String> approvalLineNos = alListResVos.stream()
                .map(AlListResVo::getApprovalLineNo)
                .collect(Collectors.toList());

        // 매퍼 메서드 호출
        return dao.getResponseAl(approvalLineNos);
    }


    public ApprovalVo getApprovalVo(String approvalNo) {
        return dao.getApprovalVo(approvalNo);
    }

    public VacationTemplateVo getVacTempVo(String approvalNo) {
        return dao.getVacTempVo(approvalNo);
    }

    public DocumentTemplateVo getDocTempVo(String approvalNo) {
        return  dao.getDocTempVo(approvalNo);
    }

    public void rejectApproval(String empId, String approvalNo, String response) {
        String al=dao.al(empId,approvalNo);
        dao.alDes(empId,approvalNo);
        dao.setAlRes(al,response);
        dao.aDes(approvalNo);
    }

    public ListApprovalVo getlistApprovalVoResIng(String empId) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIngResIng(empId);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public ApprovalVo getVoApp(String approvalNo) {
        return dao.getVoApp(approvalNo);
    }

    public void getDelApp(String approvalNo) {
        dao.getDelApp(approvalNo);
        System.out.println("approvalNo = " + approvalNo);
    }

    public void getDelApp2(String approvalNo) {
        dao.getDelApp2(approvalNo);
        System.out.println("approvalNo = " + approvalNo);
    }

    public ListApprovalVo getlistApprovalVo2(String empId) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIng2(empId);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public ListApprovalVo getlistApprovalVoSea(String empId, String typeApp, String status) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIngSea(empId,typeApp,status);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public ListApprovalVo getlistApprovalVoAll(String empId) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIngAll(empId);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public List<EmployeeVo> getEmpVo() {
        return dao.getEmpVo();
    }

    public ListApprovalVo getlistApprovalVoAllSearch(String typeApp, String emp, String empId) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIngAllSearch(typeApp,emp,empId);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public ListApprovalVo getlistApprovalVoDept(String empId,String empDept) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<EmployeeVo> highEmpVo= dao.getHighEmpVo(empId,empDept);

        List<ApprovalListVo> approvalListVo=dao.getAppListIngAll(empId);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }

    public ListApprovalVo getlistApprovalVoResSearch(String empId, String pro) {
        ListApprovalVo listApprovalVo=new ListApprovalVo();

        List<ApprovalListVo> approvalListVo=dao.getAppListIngResSearch(empId,pro);

        listApprovalVo.setApprovalListVoList(approvalListVo);

        System.out.println("approvalListVo = " + approvalListVo);

        return listApprovalVo;
    }
}