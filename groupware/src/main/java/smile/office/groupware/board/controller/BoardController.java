package smile.office.groupware.board.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smile.office.groupware.board.service.BoardService;
import smile.office.groupware.board.vo.BoardVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.page.PageVo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        System.out.println("result = " + result);
        return "redirect:/board/list";
    }
    //------------------게시글 상세조회 화면-----------------------------
    @GetMapping("detail")
    public String detail(HttpServletRequest request,@RequestParam(name = "no") String no, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        System.out.println(loginEmployeeVo.getEmpId());
        BoardVo vo = service.getBoardByNo(no);
        // 작성자와 로그인한 사용자가 다를 때만 조회수 증가
        if (!vo.getWriterNo().equals(loginEmployeeVo.getEmpId())) {

            service.increaseHit(no); // 조회수 증가 메서드 호출
        }
        model.addAttribute("vo",vo);
        System.out.println("vo = " + vo);
        return "board/detail";
    }
//    //----------------------게시글 상세 조회 기능-------------------------
//    @GetMapping("detail/{no}")
//    @ResponseBody
//    public BoardVo getBoardByNo(@PathVariable String no){
//        BoardVo vo = service.getBoardByNo(no);
//        return vo;
//    }
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
        int pageLimit =5;
        int boardLimit = 10;
        PageVo pvo= new PageVo(listCount,currentPage,pageLimit,boardLimit);
        List<BoardVo>boardVoList = service.getBoardList(pvo);
        Map<String,Object> response = new HashMap<>();
        response.put("boardVoList",boardVoList);
        response.put("pvo",pvo);
        return ResponseEntity.ok(response);
    }


    // 여러 파일 업로드 처리
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFiles(@RequestParam("fileList") List<MultipartFile> fileList) throws IOException {
        MultipartFile file = fileList.get(0);
        String realPath = servletContext.getRealPath("/");
        String targetPath = "src\\main\\";
        int index = realPath.indexOf(targetPath);
        String desiredPath = realPath.substring(0, index + targetPath.length())+"resources\\static\\img\\board\\";

        System.out.println("desiredPath = " + desiredPath);

            // 파일 저장 경로 설정
            Path uploadPath = Paths.get(desiredPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일을 지정된 경로로 저장
            File targetFile = new File(desiredPath + file.getOriginalFilename());
            file.transferTo(targetFile);

            // 업로드된 파일의 URL 생성
            String fileUrl = "http://192.168.40.105:5500/" + file.getOriginalFilename();


        return fileUrl;
    }

}
