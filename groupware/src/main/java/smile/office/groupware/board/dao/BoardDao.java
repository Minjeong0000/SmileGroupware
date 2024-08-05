package smile.office.groupware.board.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import smile.office.groupware.board.mapper.BoardMapper;
import smile.office.groupware.board.vo.BoardReplyVo;
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
    //제목검색게시글수
    public int getSearchTitleCnt(String title) {
        return mapper.getSearchTitleCnt(title);
    }
    //제목검색
    public List<BoardVo> searchTitle(String title, PageVo pvo) {
        int offset = pvo.getStartNum()-1;
        int limit = pvo.getBoardLimit();
        RowBounds rb = new RowBounds(offset,limit);
        return mapper.searchTitle(title,rb);
    }
    //내용검색 게시글수
    public int getSearchContentCnt(String content) {
        return mapper.getSearchContentCnt(content);
    }
    //내용검색
    public List<BoardVo> searchContent(String content, PageVo pvo) {
        int offset = pvo.getStartNum()-1;
        int limit = pvo.getBoardLimit();
        RowBounds rb = new RowBounds(offset,limit);
        return mapper.searchContent(content,rb);

    }


    public int increaseHit(String no) {
        return mapper.increaseHit(no);
    }

    public int checkLike(String no, String empId) {
        return mapper.checkLike(no,empId);
    }

    public int insertLike(String no, String empId) {
        return mapper.insertLike(no,empId);
    }

    public int deleteLike(String no, String empId) {
        return mapper.deleteLike(no,empId);
    }

    public int deleteBoardByNo(String no) {
        return mapper.deleteBoardByNo(no);
    }

    public List<BoardReplyVo> getBoardReply(String refNo) {
        return mapper.getBoardReply(refNo);
    }

    public int deleteReply(String no) {
        return mapper.deleteReply(no);
    }

    public int writeReply(BoardReplyVo replyVo) {
        return mapper.writeReply(replyVo);
    }


    public int getSearchWriterNameCnt(String writerName) {
        return mapper.getSearchWriterNameCnt(writerName);
    }

    public List<BoardVo> searchWriterName(String writerName, PageVo pvo) {
        int offset = pvo.getStartNum()-1;
        int limit = pvo.getBoardLimit();
        RowBounds rb = new RowBounds(offset,limit);
        return mapper.searchWriterName(writerName,rb);
    }

    public int getSearchTitleContentCnt(String titleContent) {
        return mapper.getSearchTitleContentCnt(titleContent);
    }

    public List<BoardVo> searchTitleContent(String titleContent, PageVo pvo) {
        int offset = pvo.getStartNum()-1;
        int limit = pvo.getBoardLimit();
        RowBounds rb = new RowBounds(offset,limit);
        return mapper.searchTitleContent(titleContent,rb);
    }

    public int edit(BoardVo vo) {
        return mapper.edit(vo);
    }
}
