package smile.office.groupware.board.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import smile.office.groupware.board.mapper.BoardMapper;
import smile.office.groupware.board.vo.BoardVo;
import smile.office.groupware.page.PageVo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDao {
    private final BoardMapper mapper;


    public int write(String writerNo, BoardVo vo) {
        return mapper.write(writerNo,vo);
    }


    public BoardVo getBoardByNo(String no) {
        return mapper.getBoardByNo(no);
    }

    public int getTotalBoardCount() {
        return mapper.getTotalBoardCount();
    }

    public List<BoardVo> getBoardList(PageVo pvo) {
        int offset = pvo.getStartNum()-1;
        int limit = pvo.getBoardLimit();
        RowBounds rb = new RowBounds(offset,limit);
        return mapper.getBoardList(rb);
    }
}