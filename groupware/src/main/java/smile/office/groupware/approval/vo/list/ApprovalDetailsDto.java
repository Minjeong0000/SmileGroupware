package smile.office.groupware.approval.vo.list;

import lombok.Data;

@Data
public class ApprovalDetailsDto {
    private String approvalNo;
    private String empId;
    private String approver1;
    private String approver1ImageUrl;
    private String approver2;
    private String approver2ImageUrl;
    private String approver3;
    private String approver3ImageUrl;
    private String leaveForm;
    private String priority;
    private String startDate;
    private String endDate;
    private int usageCount;
    private String reason;
    private String approvalDate;
    private String approvalLine1;
    private String approvalLine2;
    private String approvalLine3;
}
