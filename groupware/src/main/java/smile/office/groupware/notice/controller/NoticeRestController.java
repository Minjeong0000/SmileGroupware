package smile.office.groupware.notice.controller;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.notice.service.NoticeService;
import smile.office.groupware.notice.vo.NoticeVo;

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
    public HashMap<String,String>  write(NoticeVo vo){
        int result = service.write(vo);
        if(result != 1){
            throw new RuntimeException();

        }

        HashMap<String,String> map = new HashMap<>();
        map.put("msg", "write success");
        map.put("status", "200ok");

        return map;

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
}
