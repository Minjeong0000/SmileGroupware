package smile.office.groupware.message.vo;

import lombok.Data;

@Data
public class MessageVo {
    private String messageNo;
    private String forderNo;
    private String forderName;
    private String senderNo;
    private String senderName;
    private String receiverNo;
    private String receiverName;
    private String content;
    private String sentAt;
    private String deletedDate;
    private String readYn;



}
