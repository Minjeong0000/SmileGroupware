package smile.office.groupware.personalEvent.vo;

import lombok.Data;

@Data
public class PersonalEventVo {
    private String personalNo;
    private String typeNo;
    private String reminderNo;
    private String empId;
    private String title;
    private String content;
    private String location;
    private String delYn;
    private String enrollDate;

}
