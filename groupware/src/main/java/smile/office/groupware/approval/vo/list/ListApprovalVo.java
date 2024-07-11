package smile.office.groupware.approval.vo.list;

import lombok.Data;
import smile.office.groupware.approval.vo.ApprovalListVo;

import java.util.List;

@Data
public class ListApprovalVo {
    private List<ApprovalListVo> approvalListVoList;
    private List<AppAlListVo> appAlListVoList;
}
