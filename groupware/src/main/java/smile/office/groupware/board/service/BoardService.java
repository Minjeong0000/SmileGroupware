package smile.office.groupware.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.office.groupware.board.dao.BoardDao;
import smile.office.groupware.board.vo.BoardVo;
import smile.office.groupware.page.PageVo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao dao;

    public int write(String writerNo,BoardVo vo) {
        return dao.write(writerNo,vo);
    }


    public BoardVo getBoardByNo(String no) {
        return dao.getBoardByNo(no);
    }

    //페이징처리용 전체 게시글 개수가져오기
    public int getTotalBoardCount() {

        return dao.getTotalBoardCount();

    }

    public List<BoardVo> getBoardList(PageVo pvo) {
        return dao.getBoardList(pvo);
    }
}
