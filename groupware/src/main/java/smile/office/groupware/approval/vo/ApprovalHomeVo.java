package smile.office.groupware.approval.vo;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalHomeVo {
    CntAppVo cntAppVo;
    TemCntVo temCntVo;
    AppCntVo appCntVo;
    List<ApprovalListVo> approvalListVoList;
    List<ApprovalProcessVo> approvalProcessVoList;

}