package smile.office.groupware.approval.vo;

import lombok.Data;

@Data
public class TemCntVo {
    private int vacationCnt;
    private int projectCnt;
    private int emergencyCnt;
    private int normalCnt;
    private int lowCnt;
}