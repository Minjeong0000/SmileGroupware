package smile.office.groupware.approval.vo;

import lombok.Data;

@Data
public class ApprovalVo {
    private String approvalNo;
    private String priorityNo;
    private String empId;
    private String statusNo;
    private String title;
    private String content;
    private String createDate;
    private String approvalRange;
    private String temporaryStorageYn;
}
