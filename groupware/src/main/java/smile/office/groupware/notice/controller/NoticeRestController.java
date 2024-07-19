package smile.office.groupware.notice.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.notice.service.NoticeService;
import smile.office.groupware.notice.vo.NoticeVo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/notice")
@RequiredArgsConstructor
@CrossOrigin
public class NoticeRestController {

    private final NoticeService service;

    @GetMapping("list")
    public List<NoticeVo> getNoticeList(){
        List<NoticeVo> voList = service.getNoticeList();
        return voList;

        }


    //공지사항 작성
    @PostMapping
    public int write(HttpServletRequest request, NoticeVo vo) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");

        String writerNo = loginEmployeeVo.getEmpId();
        vo.setWriterNo(writerNo );
        int result = service.write(vo);

//        if (result != 1) {
//            throw new RuntimeException("Error writing notice");
//        }
//
        return result;
    }


    //공지사항 상세조회(번호)
    @GetMapping("detail")
    public NoticeVo getNoticeByNo(NoticeVo vo){

        return service.getNoticeByNo(vo.getNo());
    }

    //공지사항 삭제 (번호)
    @DeleteMapping
    public void deleteNoticeByNo(){

    }

    //사진 업로드
    @PostMapping("upload")
    @ResponseBody
    public String uploadFile(@RequestParam("fileList") List<MultipartFile> fileList) throws IOException {

        MultipartFile file = fileList.get(0);

        File targetFile = new File("D:\\final\\groupware\\src\\main\\resources\\static\\img\\notice" + file.getOriginalFilename());

        file.transferTo(targetFile);

        return "http://192.168.40.110:5500/img/" + file.getOriginalFilename();
    }




}
