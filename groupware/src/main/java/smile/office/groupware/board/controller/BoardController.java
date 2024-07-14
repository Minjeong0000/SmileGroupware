package smile.office.groupware.board.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smile.office.groupware.board.service.BoardService;
import smile.office.groupware.board.vo.BoardVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.page.PageVo;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    //----------------------------게시글 작성 화면------------------------
    @GetMapping("write")
    public String write(){
        return "board/write";
    }

    //--------------------------게시글 작성하기---------------------------
    @PostMapping("write")
    public String write(HttpServletRequest request,BoardVo vo){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String writerNo = loginEmployeeVo.getEmpId();
        int result = service.write(writerNo,vo);
        return "redirect:/board/list";
    }
    //------------------게시글 상세조회 화면-----------------------------
    @GetMapping("detail")
    public String detail(@RequestParam String no){
        return "board/detail";
    }
    //----------------------게시글 상세 조회 기능-------------------------
    @GetMapping("detail/{no}")
    @ResponseBody
    public BoardVo getBoardByNo(@PathVariable String no){
        BoardVo vo = service.getBoardByNo(no);
        return vo;
    }
    //---------------------게시글 목록 화면-------------------
    @GetMapping("list")
    public String list(){
        return "board/list";
    }
    //----------------게시글 목록 조회------------------
    @GetMapping("list/data")
    @ResponseBody
    public ResponseEntity<?> getBoardList(@RequestParam("pno") String pno){
        int listCount = service.getTotalBoardCount();
        int currentPage = Integer.parseInt(pno);
        System.out.println("pno = " + pno);
        int pageLimit =5;
        int boardLimit = 10;
        PageVo pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);
        List<BoardVo>boardVoList = service.getBoardList(pvo);
        Map<String,Object> response = new HashMap<>();
        response.put("boardVoList",boardVoList);
        response.put("pvo",pvo);
        return ResponseEntity.ok(response);
    }


    // 사진 업로드
    @PostMapping("upload")
    @ResponseBody
    public List<String> uploadFile(@RequestParam("fileList") List<MultipartFile> fileList, HttpServletRequest req) throws IOException {
        // 루트 경로 얻기
        String rootPath = req.getServletContext().getRealPath("/");
        String uploadDir = "static/img/";
        String fullUploadPath = rootPath + uploadDir;
        // 업로드된 파일의 URL을 저장할 리스트
        List<String> fileUrls = new ArrayList<>();
        // 파일 리스트 반복
        for (MultipartFile file : fileList) {
            // 원본 파일 이름 가져오기
            String originalFilename = file.getOriginalFilename();
            // 파일 확장자 추출
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // UUID를 사용하여 고유한 파일 이름 생성
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            // 타겟 파일 생성 경로
            File targetFile = new File(fullUploadPath + uniqueFilename);
            // 디렉토리가 존재하지 않으면 생성
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 파일을 타겟 파일에 저장
            file.transferTo(targetFile);
            // 저장된 파일의 URL을 리스트에 추가
            fileUrls.add(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img/board/" + uniqueFilename);
        }

        // 모든 파일의 URL 리스트 반환
        return fileUrls;
    }

}
