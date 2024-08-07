package smile.office.groupware.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import smile.office.groupware.admin.service.AdminService;
import smile.office.groupware.admin.vo.AdminVo;
import smile.office.groupware.admin.vo.AttendanceDetailVo;
import smile.office.groupware.attendanceStatistics.vo.AttendanceStatisticsVo;
import smile.office.groupware.department.vo.DepartmentVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.position.vo.PositionVo;
import smile.office.groupware.question.vo.QuestionVo;
import smile.office.groupware.role.vo.RoleVo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return "admin/adminHome";
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

    // 관리자 추가 (화면)
    @GetMapping("adminAdd")
    public String adminAdd(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");

        if (loginAdminVo != null && "1".equals(loginAdminVo.getAdminLevel())) {
            return "admin/adminAdd";
        } else {
            model.addAttribute("errMsg", "접근 권한이 없습니다.");
            return "common/error";
        }
    }

    // 관리자 추가 기능구현
    @PostMapping("adminAdd")
    public String adminAdd(@ModelAttribute AdminVo vo, Model model) {
        try {
            int result = service.addAdmin(vo);
            System.out.println("result = " + result);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errMsg", "관리자 추가 실패: " + e.getMessage());
            return "common/error";
        }
    }

    ///////////////////////////////////////////////////////////////

    // 관리자 삭제
    @DeleteMapping("delete")
    @ResponseBody
    public String delete(@RequestParam("num") String num){
        System.out.println("num = " + num);
        int result = service.delete(num);
        return result == 1 ? num + "번 관리자 삭제 완료!" : "삭제 실패...";
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
            if (!profileFile.isEmpty()) {
                String uploadDir = "D:\\smailOffice\\groupware\\src\\main\\resources\\static\\img\\userProfile";
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
            if (result == 1) {
                return "redirect:/admin/userEdit";
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

    ////////////////////////////////////////////////////////////////

    // 부서 목록 가져오기 (REST API)
    @GetMapping("/api/departments")
    @ResponseBody
    public List<DepartmentVo> getDepartments() {
        return service.getDepartments();
    }

    // 역할 목록 가져오기 (REST API)
    @GetMapping("/api/roles")
    @ResponseBody
    public List<RoleVo> getRoles() {
        return service.getRoles();
    }

    // 직위 목록 가져오기 (REST API)
    @GetMapping("/api/positions")
    @ResponseBody
    public List<PositionVo> getPositions() {
        return service.getPositions();
    }

    ////////////////////////////////////////////////////////////////

    //관리자 조회
    @GetMapping("inquiry")
    @ResponseBody
    public List<AdminVo> adminInquiry(AdminVo vo){
        List<AdminVo> adminVoList = service.adminInquiry();
        return adminVoList;
    }

    //adminCheck 관리자 조회 및 삭제 (화면)
    @GetMapping("adminCheck")
    public String adminCheck(){
        return "admin/adminCheck";
    }

    //userEdut 사용자 수정 모달로 구현
    @GetMapping("getEmployeeDetail")
    @ResponseBody
    public EmployeeVo getEmployeeDetail(@RequestParam("empId") String empId) {
        return service.getEmployeeById(empId);
    }

    //유저 삭제 (삭제는 Ajax로 처리함)
    @DeleteMapping("/employeeDelete")
    @ResponseBody
    public String employeeDelete(@RequestBody Map<String, String> requestData) {
        String employeeNum = requestData.get("employeeNum");
        System.out.println("Deleting employee with ID: " + employeeNum); // Debug log
        int result = service.employeeDelete(employeeNum);
        return result == 1 ? employeeNum + "번 사용자 삭제 완료!" : "삭제 실패...";
    }

    //////////////////////////////////////////////////////////////////////////

    // 문의사항 조회 (화면)
    @GetMapping("adminQuestion")
    public String questionOverview() {
        return "admin/adminQuestion";
    }

    // 문의사항 목록 가져오기 (AJAX)
    @GetMapping("getQuestions")
    @ResponseBody
    public List<QuestionVo> getQuestions() {
        return service.getQuestions();
    }

    ////////////////////////////////////////////////////////////////

    //관리자 로그아웃
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); // 세션 무효화
        return "redirect:/login";
    }

    ///////////////////////////////////////////////////////////////////////////

    // 출근 통계 화면
    @GetMapping("attendanceStatistics")
    public String attendanceStatistics() {
        return "admin/attendanceStatistics";
    }

    // 모든 직원의 출근 통계 데이터 가져오기
    @GetMapping("getAttendanceStatistics")
    @ResponseBody
    public List<AttendanceStatisticsVo> getAttendanceStatistics() {
        return service.getAttendanceStatistics();
    }

    // 특정 직원의 출근 상세 데이터 가져오기
    @GetMapping("getAttendanceDetails/{empId}")
    @ResponseBody
    public List<AttendanceDetailVo> getAttendanceDetails(@PathVariable("empId") String empId) {
        return service.getAttendanceDetails(empId);
    }


}//class
