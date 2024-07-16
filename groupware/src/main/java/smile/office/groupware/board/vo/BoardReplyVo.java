package smile.office.groupware.board.vo;

import lombok.Data;

@Data
public class BoardReplyVo {

    private String no;
    private String refNo;
    private String writerNo;
    private String writerName;
    private String content;
    private String writeDate;
    private String delYn;


}
