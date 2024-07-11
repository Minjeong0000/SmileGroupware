package smile.office.groupware.approval.vo;

import lombok.Data;

@Data
public class ApprovalProcessVo {
    private int approvalNo;
    private String approver;
    private int approvalOrder;
    private String approvalLine;
    private String processingStatus;
    private String responseText;
    private String respondedDate;
}
