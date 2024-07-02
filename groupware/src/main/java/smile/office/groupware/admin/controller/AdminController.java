package smile.office.groupware.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import smile.office.groupware.admin.service.AdminService;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    ////////////////////////////////////////////////////////////////

    // 운영자 로그인 (화면)
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
    }

    ////////////////////////////////////////////////////////////////

    // 운영자 홈 (화면)
    @GetMapping("home")
    public String home(){
        return "admin/adminHome";
    }

    ////////////////////////////////////////////////////////////////

    // 운영자 유저 정보 수정 (화면)
    @GetMapping("userEdit")
    public String userEdit(){
        return "admin/userEdit";
    }

    // 사원 목록 가져오기
    @GetMapping("getEmployees")
    @ResponseBody
    public List<EmployeeVo> getEmployees() {
        List<EmployeeVo> employees = service.getEmployees();
        System.out.println("Fetched employees: " + employees);  // 로그 추가
        return employees;
    }

    ////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////

    //관리자 추가 (화면)
    @GetMapping("adminAdd")
    public String adminAdd(){
        return "admin/adminAdd";
    }

    // 관리자 추가 기능구현
    @PostMapping("adminAdd")
    public String adminAdd(@ModelAttribute AdminVo vo, Model model) {
        try {
            int result = service.addAdmin(vo);
            System.out.println("result = " + result);
            return "redirect:/admin/login";
        } catch (Exception e) {
            model.addAttribute("errMsg", "관리자 추가 실패: " + e.getMessage());
            return "common/error";
        }
    }

    ///////////////////////////////////////////////////////////////

    //관리자 조회
    @GetMapping("inquiry")
    @ResponseBody
    public List<AdminVo> adminInquiry(AdminVo vo){
        List<AdminVo> adminVoList = service.adminInquiry();
        return adminVoList;
    }
    
    ///////////////////////////////////////////////////////////////

    // 관리자 삭제
    @DeleteMapping("delete")
    @ResponseBody
    public String delete(@RequestParam("num") String num){
        System.out.println("num = " + num);
        int result = service.delete(num);
        return result == 1 ? num + "번 삭제 완료!" : "삭제 실패...";
    }

    ///////////////////////////////////////////////////////////////

    // 사원 추가 (화면)
    @GetMapping("userAdd")
    public String userAdd() {
        return "admin/userAdd";
    }

    // 사원 추가 기능 구현
    @PostMapping("userAdd")
    public String userAdd(@ModelAttribute EmployeeVo vo, @RequestParam("profileFile") MultipartFile profileFile, Model model) {
        try {
            // 프로필 사진 저장
            if (!profileFile.isEmpty()) {
                String uploadDir = "D:/smailOffice/groupware/src/main/resources/static/img";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String fileName = profileFile.getOriginalFilename();
                File saveFile = new File(uploadDir, fileName);
                profileFile.transferTo(saveFile);
                vo.setProfile(fileName);
            }

            int result = service.addEmployee(vo);
            if (result > 0) {
                return "redirect:/admin/home";
            } else {
                model.addAttribute("errMsg", "사원 추가 실패");
                return "common/error";
            }
        } catch (IOException e) {
            model.addAttribute("errMsg", "파일 업로드 실패: " + e.getMessage());
            return "common/error";
        } catch (Exception e) {
            model.addAttribute("errMsg", "사원 추가 실패: " + e.getMessage());
            return "common/error";
        }
    }//method

    ///////////////////////////////////////////////////////////////////////

    
    


}//class


