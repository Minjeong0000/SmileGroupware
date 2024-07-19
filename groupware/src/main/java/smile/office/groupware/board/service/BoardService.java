package smile.office.groupware.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smile.office.groupware.board.dao.BoardDao;
import smile.office.groupware.board.vo.BoardReplyVo;
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

    //조회수증가
    public int increaseHit(String no) {
        return dao.increaseHit(no);
    }

    //좋아요기능 혹은 취소
    public boolean toggleLike(String no, String empId) {
        int count = dao.checkLike(no, empId);
        if (count == 0) {
            dao.insertLike(no, empId);
            return true; // liked
        } else {
            dao.deleteLike(no, empId);
            return false; // unliked
        }
    }

    public int deleteBoardByNo(String no) {
        return dao.deleteBoardByNo(no);
    }
    //게시글 댓글 불러오기
    public List<BoardReplyVo> getBoardReply(String refNo) {
        return dao.getBoardReply(refNo);
    }

    public int deleteReply(String no) {
        return dao.deleteReply(no);

    }

    public int writeReply(BoardReplyVo replyVo) throws Exception {
        if(replyVo.getWriterNo() == null){
            throw new Exception("로그인 후 이용해주세요.");
        }
        if(replyVo.getContent() == null){
            throw new Exception("내용을 작성해주세요.");
        }
        if(replyVo.getContent().contains("사장놈")){
            throw new Exception("부적절한 단어가 포함되어있습니다.");
        }
        return dao.writeReply(replyVo);

    }

    //제목으로검색
    public List<BoardVo> searchTitle(String title, PageVo pvo) {
        return dao.searchTitle(title,pvo);
    }
    //제목검색게시글수
    public int getSearchTitleCnt(String title) {
        return dao.getSearchTitleCnt(title);
    }

    public int getSearchContentCnt(String content) {
        return dao.getSearchContentCnt(content);
    }

    public List<BoardVo> searchContent(String content, PageVo pvo) {
        return dao.searchContent(content,pvo);
    }
    //작성자이름 게시글수
    public int getSearchWriterNameCnt(String writerName) {
        return dao.getSearchWriterNameCnt(writerName);
    }

    public List<BoardVo> searchWriterName(String writerName, PageVo pvo) {
        return dao.searchWriterName(writerName,pvo);
    }

    public int getSearchTitleContentCnt(String titleContent) {
        return dao.getSearchTitleContentCnt(titleContent);
    }

    public List<BoardVo> searchTitleContent(String titleContent, PageVo pvo) {
        return dao.searchTitleContent(titleContent,pvo);

    }
}
