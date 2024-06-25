package smile.office.groupware.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import smile.office.groupware.admin.service.AdminService;
import smile.office.groupware.admin.vo.AdminVo;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    // 운영자 로그인 화면
    @GetMapping("login")
    public String login() {
        return "admin/login";
    }

    // 운영자 로그인 기능구현
    @PostMapping("login")
    public String login(@ModelAttribute AdminVo vo, HttpSession session) {
        try {
            AdminVo loginAdminVo = service.login(vo);

            if (loginAdminVo != null) {
                session.setAttribute("alertMsg", "관리자 로그인 성공!!");
                session.setAttribute("loginAdminVo", loginAdminVo);
                System.out.println("로그인 성공");
                return "redirect:/webtoon/home";
            } else {
                session.setAttribute("alertMsg", "관리자 로그인 실패...");
                System.out.println("로그인 실패: ID 또는 비밀번호 불일치 또는 계정이 삭제됨");
                return "common/error";
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errMsg", "[ERROR-M0002] 로그인 중 에러 발생 ...");
            return "common/error";
        }
    }
}
