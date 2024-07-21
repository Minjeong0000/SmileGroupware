package smile.office.groupware.approval.vo.list;

import lombok.Data;
import smile.office.groupware.approvalLine.vo.ApprovalLineVo;
import smile.office.groupware.approvalResponse.vo.ApprovalResponseVo;

import java.util.List;

@Data
public class AlListResVo {
    private String approvalLineNo;
    private String empId;
    private String empName;
    private String seq;
}
