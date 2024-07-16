package smile.office.groupware.notice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smile.office.groupware.notice.vo.NoticeVo;

@Controller
@RequiredArgsConstructor
@RequestMapping("notice")
public class NoticeController {

    //공지사항 (화면)
    @GetMapping("write")
    public String write(){
        return "notice/write";
    }

    //공지사항 목록조회 (화면)
    @GetMapping("list")
    public String list(){
        return "notice/list";
    }

    //공지사항 상세조회 (화면)
    @GetMapping("detail")
    public String detail(NoticeVo vo){
        return "notice/detail";
    }

    //공지사항 수정하기 (화면)
    @GetMapping("edit")
    public String edit(){
        return "notice/edit";
    }


}
