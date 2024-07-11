package smile.office.groupware.approval.vo.write;

import lombok.Data;

@Data
public class AppVacVo {
    private String action;
    private String appTitle;
    private String appContent;
    private int vacNo;
    private int prioritieNo;
    private int appLine1;
    private int appLine2;
    private int appLine3;
    private String start;
    private String end;
    private int use;
    private String vacContent;
    private String note;
    private String range;
    private int statusNo;
}