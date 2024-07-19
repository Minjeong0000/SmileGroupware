package smile.office.groupware.notice.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.notice.mapper.NoticeMapper;
import smile.office.groupware.notice.vo.NoticeVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeDao {

    private final NoticeMapper mapper;

    public int write(NoticeVo vo) {
        System.out.println("vo = " + vo);
        return mapper.write(vo);
    }



    public List<NoticeVo> getNoticeList() {

        return mapper.getNoticeList();
    }

    public NoticeVo getNoticeByNo(String no) {
        return mapper.getNoticeByNo(no);
    }
}
