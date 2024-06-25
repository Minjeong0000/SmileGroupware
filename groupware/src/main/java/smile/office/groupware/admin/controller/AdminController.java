package smile.office.groupware.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import smile.office.groupware.admin.service.AdminService;
import smile.office.groupware.admin.vo.AdminVo;




@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    // 운영자 로그인 화면
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    // 운영자 로그인 기능구현
    @PostMapping("login")
    public String login(AdminVo vo, HttpServletRequest request, Model model) {
        System.out.println("로그인 시도: " + vo.getAdminId());
        AdminVo loginAdminVo = service.login(vo);

        if (loginAdminVo != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginAdminVo", loginAdminVo);
            System.out.println("로그인 성공: " + loginAdminVo.getAdminId());
            return "common/result";  // 로그인 후 대시보드로 리다이렉트
        } else {
            System.out.println("로그인 실패: " + vo.getAdminId());
            model.addAttribute("errMsg", "로그인 실패: ID 또는 비밀번호 불일치 또는 계정이 삭제됨");
            return "common/error";
        }
    }
}
