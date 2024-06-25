package smile.office.groupware.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smile.office.groupware.admin.service.AdminService;
import smile.office.groupware.admin.vo.AdminVo;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    // 운영자 로그인 화면
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    // 운영자 로그인 기능구현
    @PostMapping("/login")
    public String login(AdminVo vo, HttpSession session, Model model) {
        AdminVo loginAdminVo = service.login(vo);

        if (loginAdminVo != null) {
            session.setAttribute("alertMsg", "관리자 로그인 성공!!");
            session.setAttribute("loginAdminVo", loginAdminVo);
            model.addAttribute("resultMsg", "로그인 성공");
            model.addAttribute("nick", loginAdminVo.getAdminNick());
            return "result";
        } else {
            session.setAttribute("alertMsg", "관리자 로그인 실패...");
            model.addAttribute("errMsg", "로그인 실패: ID 또는 비밀번호 불일치 또는 계정이 삭제됨");
            return "common/error";
        }
    }
}
