package smile.office.groupware.board.vo;

import lombok.Data;

@Data
public class BoardVo {
    private String bNo;
    private String title;
    private String content;
    private String writerNo;
    private String hit;
    private String delYn;
    private String writeDate;
    private String writerName; // 작성자 이름
    private int likeCount; // 좋아요 수

}
