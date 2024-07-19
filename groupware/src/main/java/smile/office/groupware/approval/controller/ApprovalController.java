package smile.office.groupware.approval.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.approval.service.ApprovalService;
import smile.office.groupware.approval.vo.ApprovalHomeVo;
import smile.office.groupware.approval.vo.list.AppAlListVo;
import smile.office.groupware.approval.vo.list.ListApprovalVo;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.approval.vo.write.WriteVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {
    private final ApprovalService service;
    //결재 홈
    @GetMapping("/home")
    public String approvalHome(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        model.addAttribute("approvalHomeVo", appHomeVo);
        return "approval/home";
    }

    //휴가 결재 작성
    @GetMapping("/vac")
    public String approvalWriteVac(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        WriteVo writeVo=service.getApprovalWrite(loginEmployeeVo);
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        model.addAttribute("approvalHomeVo", appHomeVo);
        model.addAttribute("writeVo",writeVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/write/vacation";
    }

    @PostMapping("/vac")
    public String approvalWriteVacPost(@ModelAttribute AppVacVo appVacVo, Model model){
        if ("save".equals(appVacVo.getAction())) {
            service.saveApproval(appVacVo);
            return "redirect:/approval/home";
        } else if ("submit".equals(appVacVo.getAction())) {
            service.submitApproval(appVacVo);
            return "redirect:/approval/home";
        }
        return "redirect:/approval/vac";
    }

    //프로젝트 결재 페이지 작성
    @GetMapping("/doc")
    public String approvalWriteDoc(){
        return "approval/write/document";
    }
    //기타 결재 작성

    //결재 중
    @GetMapping("/ing")
    public String approvalListIng(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVo(empId);
        model.addAttribute("listApprovalVo",listApprovalVo);

        return "approval/list/ing";
    }

    //결재 처리/응답
    @GetMapping("/response")
    public String approvalListResponse(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVoRes(empId);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/list/response";
    }


    //결재 작성 중
    @GetMapping("/write")
    public String approvalListWrite(){return "approval/list/write";}

    //전체 결재 목록
    @GetMapping("/all")
    public String approvalFinAll(){return "approval/fin/all";}
    //부서 결재 목록
    @GetMapping("/dept")
    public String approvalFinDept(){return "approval/fin/dept";}
    //개인 결재 목록
    @GetMapping("/private")
    public String approvalFinPrivate(){return "approval/fin/private";}


    @GetMapping("/test")
    public String test(){
        return "approval/list/response_ex";
    }

}