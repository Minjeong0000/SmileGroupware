package smile.office.groupware.board.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.eclipse.tags.shaded.org.apache.regexp.RE;
import org.eclipse.tags.shaded.org.apache.regexp.REDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smile.office.groupware.board.service.BoardService;
import smile.office.groupware.board.vo.BoardReplyVo;
import smile.office.groupware.board.vo.BoardVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.page.PageVo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    private ServletContext servletContext;

    private final BoardService service;

    private final AmazonS3 s3;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;


    //----------------------------게시글 작성 화면------------------------
    @GetMapping("write")
    public String write(){
        return "board/write";
    }

    //--------------------------게시글 작성하기---------------------------
    @PostMapping("write")
    public ResponseEntity<?> write(HttpServletRequest request,BoardVo vo){
        try{
            HttpSession session = request.getSession();
            EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
            String writerNo = loginEmployeeVo.getEmpId();
            int result = service.write(writerNo,vo);
            if(result==1){
                return ResponseEntity.ok("게시글 작성 성공");
            }else{
                return ResponseEntity.badRequest().body("게시글 작성 실패");
            }
            }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }
    //------게시글 수정 화면
    @GetMapping("edit")
    public String edit(@RequestParam("no")String no, Model model){
        BoardVo vo = service.getBoardByNo(no);
        model.addAttribute("vo",vo);
        return "board/edit";
    }
    //게시글 수정 미리 작성된 내용 ajax불러오기(썸머노트용)
    @GetMapping("edit/getPost")
    public ResponseEntity<?> loadEditContent(String no){
        BoardVo vo = service.getBoardByNo(no);
        System.out.println("vo = " + vo);
        if (vo!=null){
            return ResponseEntity.ok(vo);
        }
        return ResponseEntity.badRequest().body("게시글 정보 불러오기 실패");
    }

    //----------게시글 수정 기능

    @PostMapping("edit")
    public ResponseEntity<?> edit(BoardVo vo){
        try{

            int result = service.edit(vo);
            System.out.println("result = " + result);
            if(result==1){
                return ResponseEntity.ok("게시글 수정 성공");
            }else{
                return ResponseEntity.badRequest().body("게시글 수정 실패");
            }

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





    //------------------게시글 상세조회 화면-----------------------------
    @GetMapping("detail")
    public String detail(HttpServletRequest request,@RequestParam("no") String no, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        BoardVo vo = service.getBoardByNo(no);
        // 작성자와 로그인한 사용자가 다를 때만 조회수 증가
        if (!vo.getWriterNo().equals(loginEmployeeVo.getEmpId())) {
            service.increaseHit(no); // 조회수 증가 메서드 호출
        }
        model.addAttribute("vo",vo);
        return "board/detail";
    }

    //추천토글
    @PostMapping("like")
    public ResponseEntity<?>toggleLike(HttpServletRequest request, String no){
        try{
            HttpSession session = request.getSession();
            EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
            String empId = loginEmployeeVo.getEmpId();

            boolean isLiked = service.toggleLike(no,empId);
            return ResponseEntity.ok(isLiked ? "liked":"unliked");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("추천 혹은 추천 취소 실패");
        }

    }
    //게시글 삭제
    @PutMapping("delete")
    public ResponseEntity<?>deleteBoardByNo(String no){
        int result = service.deleteBoardByNo(no);
        if(result ==1){
           return ResponseEntity.ok("게시글 삭제 성공, 목록으로 이동합니다.");
        }else{
            System.out.println("no = " + no);
            System.out.println("result = " + result);
            return ResponseEntity.badRequest().body("게시글 삭제 실패");
        }
    }
    //--------------------게시글별 댓글 불러오기----------------------------
    @GetMapping("replyList")
    @ResponseBody
    public ResponseEntity<?>getBoardReply(HttpServletRequest request, @RequestParam("refNo") String refNo,Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        List<BoardReplyVo>replyVoList = service.getBoardReply(refNo);
//        Map<String, Object> response = new HashMap<>();
//        response.put("empId",empId);
//        response.put("replyVoList",replyVoList);
        //
        model.addAttribute("replyVoList",replyVoList);
        return ResponseEntity.ok(replyVoList);
    }
    //------------댓글 삭제---------------
    @PutMapping("reply/delete")
    public ResponseEntity<?>deleteReply(@RequestParam("no")String no){


        int result = service.deleteReply(no);
        if(result==1){
            System.out.println("if문 no = " + no);
            System.out.println("result = " + result);

            return ResponseEntity.ok("댓글을 삭제했습니다.");
        }else{
            System.out.println("else문 no = " + no);
            System.out.println("result = " + result);
            return ResponseEntity.badRequest().body("댓글 삭제 중 오류가 발생했습니다.");
        }
    }
    //--------------댓글 작성-----------------
    @PostMapping("reply/write")
    public ResponseEntity<?>writeReply(BoardReplyVo replyVo){
        try{
            System.out.println("replyVo = " + replyVo);
            int result = service.writeReply(replyVo);

            if(result==1){
                return ResponseEntity.ok("작성 성공");
            }else{
                return ResponseEntity.badRequest().body("작성 실패");
            }


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }


        //---------------------게시글 목록 화면-------------------
    @GetMapping("list")
    public String list(){
        return "board/list";
    }
    //----------------게시글 목록 조회------------------
    @GetMapping("list/data")
    @ResponseBody
    public ResponseEntity<?> getBoardList(@RequestParam(value="pno",defaultValue = "1") String pno){
        int listCount = service.getTotalBoardCount();
        int currentPage = Integer.parseInt(pno);
        int pageLimit =5;
        int boardLimit = 10;
        PageVo pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);
        List<BoardVo>boardVoList = service.getBoardList(pvo);
        Map<String,Object> response = new HashMap<>();
        response.put("boardVoList",boardVoList);
        response.put("pvo",pvo);
        return ResponseEntity.ok(response);
    }

    //----------------------------게시글 검색------------------
    @GetMapping("list/search")
    @ResponseBody
    public ResponseEntity<?>searchBoard(@RequestParam(value = "title",required = false)String title,
                                        @RequestParam(value = "content",required = false)String content,
                                        @RequestParam(value = "writerName",required = false)String writerName,
                                        @RequestParam(value = "titleContent",required = false)String titleContent,
                                        @RequestParam(value="pno",defaultValue = "1") String pno
                                        ){
        int currentPage = Integer.parseInt(pno);
        int pageLimit =5;
        int boardLimit = 10;
        List<BoardVo>boardVoList =null;
        PageVo pvo = null;
        if(title != null){
            int listCount = service.getSearchTitleCnt(title);
            pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);
            boardVoList = service.searchTitle(title,pvo);
            System.out.println("title = " + title);
            System.out.println("boardVoList = " + boardVoList);
        }
        if(content != null){
            int listCount = service.getSearchContentCnt(content);
            pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);
            boardVoList = service.searchContent(content,pvo);
            System.out.println("content = " + content);
            System.out.println("boardVoList = " + boardVoList);

        }
        if(writerName != null){
            int listCount = service.getSearchWriterNameCnt(writerName);
            pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);
            boardVoList = service.searchWriterName(writerName,pvo);
            System.out.println("writerName = " + writerName);
            System.out.println("boardVoList = " + boardVoList);

        }
        if(titleContent!=null){
            int listCount = service.getSearchTitleContentCnt(titleContent);
            pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);
            boardVoList = service.searchTitleContent(titleContent,pvo);
            System.out.println("titleContent = " + titleContent);
            System.out.println("boardVoList = " + boardVoList);

        }
        Map<String,Object> response = new HashMap<>();
        response.put("boardVoList",boardVoList);
        response.put("pvo",pvo);
        return ResponseEntity.ok(response);
    }

//s3
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFiles(@RequestParam("fileList") List<MultipartFile> fileList) throws IOException {
        MultipartFile file = fileList.get(0);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        // 파일 이름을 UUID로 변경
        String randomFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        //s3에 업로드
        s3.putObject(bucketName,randomFileName,file.getInputStream(),metadata);

        // 파일을 지정된 경로로 저장
        URL url = s3.getUrl(bucketName,randomFileName);
        System.out.println("url = " + url);
        return url.toString();

    }



}
