package smile.office.groupware.notice.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smile.office.groupware.board.vo.BoardVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.notice.service.NoticeService;
import smile.office.groupware.notice.vo.NoticeVo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/notice")
@RequiredArgsConstructor
@CrossOrigin
public class NoticeRestController {

    private final NoticeService service;

    private final AmazonS3 s3;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;


    //공지사항 목록조회
   @GetMapping("list")
   public List<NoticeVo> getNoticeList(){
       List<NoticeVo>voList = service.getNoticeList();
       return voList;

        }


    //공지사항 작성하기
    @PostMapping
    public int write(HttpServletRequest request, NoticeVo vo) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");

        String writerNo = loginEmployeeVo.getEmpId();
        vo.setWriterNo(writerNo);
        int result = service.write(vo);

        if (result != 1) {
            throw new RuntimeException("Error writing notice");
        }

        return result;
    }


    //게시글 상세조회
    @GetMapping("detail")
    public NoticeVo getNoticeByNo(HttpServletRequest request, @RequestParam("no") String no, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        NoticeVo vo = service.getNoticeByNo(no);
        model.addAttribute("vo",vo);
        return vo;

    }

    //삭제하기
    @DeleteMapping("delete")
    public int deleteNoticeByNo(String no){
       int result = service.delete(no);
       return result;
    }

    //수정하기
    @PutMapping
    public int edit(NoticeVo vo){
       int result = service.edit(vo);
       return result;
    }





    //s3에 업로드
    @PostMapping("upload")
    @ResponseBody
    public String write(@RequestParam("fileList") List<MultipartFile> fileList) throws Exception {


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
