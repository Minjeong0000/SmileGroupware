package smile.office.groupware.approval.vo;

import lombok.Data;

@Data
public class ApprovalListVo {
    private String approvalNo;
    private String priority;
    private String category;
    private String title;
    private String content;
    private String createDate;
    private String approver;
    private String approvalLine;
    private String status;
}