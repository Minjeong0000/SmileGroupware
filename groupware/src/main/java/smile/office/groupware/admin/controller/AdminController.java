package smile.office.groupware.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import smile.office.groupware.admin.service.AdminService;
import smile.office.groupware.admin.vo.AdminVo;

import java.util.List;

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
    public String login(AdminVo vo, HttpServletRequest request, Model model) {
        System.out.println("로그인 시도: " + vo.getAdminId());
        AdminVo loginAdminVo = service.login(vo);

        if (loginAdminVo != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginAdminVo", loginAdminVo);
            System.out.println("로그인 성공: " + loginAdminVo.getAdminId());
            return "admin/home";
        } else {
            System.out.println("로그인 실패: " + vo.getAdminId());
            model.addAttribute("errMsg", "로그인 실패: ID 또는 비밀번호 불일치 또는 계정이 삭제됨");
            return "common/error";
        }
    }//method

    // 운영자 홈화면
    @GetMapping("home")
    public String home(){
        return "admin/adminHome";
    }//method

    // 운영자 유저 정보 수정 화면
    @GetMapping("userEdit")
    public String userEdit(){
        return "admin/userEdit";
    }//method

    // 운영자 유저 정보 수정 기능구현
    @PostMapping("userEdit")
    public String userEdit(@RequestBody AdminVo vo) {
        // 관리자를 추가하는 로직
        service.addAdmin(vo);
        return "redirect:/admin/userEdit";
    }

    // 관리자 목록 가져오기
    @GetMapping("getAdmins")
    @ResponseBody
    public List<AdminVo> getAdmins() {
        return service.getAdmins();
    }

    //관리자 추가 adminAdd 화면
    @GetMapping("adminAdd")
    public String adminAdd(){
        return "admin/adminAdd";
    }
}
