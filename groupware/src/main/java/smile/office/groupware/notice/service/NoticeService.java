package smile.office.groupware.notice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smile.office.groupware.notice.dao.NoticeDao;
import smile.office.groupware.notice.vo.NoticeVo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeDao dao;

    public int write(NoticeVo vo) {

        return dao.write(vo);
    }

    public NoticeVo getNoticeByNo(String no) {
        return dao.getNoticeByNo(no);
    }

    public List<NoticeVo> getNoticeList() {
        return dao.getNoticeList();
    }

    public int delete(String no) {
        return dao.delete(no);
    }

    public int edit(NoticeVo vo) {
        return dao.edit(vo);
    }
}
