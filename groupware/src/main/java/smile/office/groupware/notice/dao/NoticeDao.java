package smile.office.groupware.notice.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smile.office.groupware.notice.mapper.NoticeMapper;
import smile.office.groupware.notice.vo.NoticeVo;

@Repository
@RequiredArgsConstructor
public class NoticeDao {

    private final NoticeMapper mapper;

    public int write(NoticeVo vo) {
        return mapper.write(vo);
    }

    public NoticeVo getNoticeByNo(String no) {
        return mapper.getNoticeByNo(no);
    }
}
