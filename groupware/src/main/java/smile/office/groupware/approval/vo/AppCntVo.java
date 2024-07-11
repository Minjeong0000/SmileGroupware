package smile.office.groupware.approval.vo;

import lombok.Data;

@Data
public class AppCntVo {
    private int approvalCount;
    private int approvalOkCnt;
    private int approvalNoCnt;
    private int approvalAIngCnt;
    private int approvalWIngCnt;
}