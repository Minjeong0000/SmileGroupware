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
import smile.office.groupware.approval.vo.ApprovalVo;
import smile.office.groupware.approval.vo.list.AppAlListVo;
import smile.office.groupware.approval.vo.list.ApprovalDetailsDto;
import smile.office.groupware.approval.vo.list.ListApprovalVo;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.approval.vo.write.WriteVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.HashMap;
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

    @PostMapping("/api/vac")
    public ResponseEntity<Map<String, String>> processApproval(@RequestBody ApprovalDetailsDto approvalDetailsDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            Map<String, String> response = new HashMap<>();
            response.put("redirectUrl", "/login");
            return ResponseEntity.ok(response);
        }

        String empId = loginEmployeeVo.getEmpId();
        WriteVo writeVo = service.getApprovalWrite(loginEmployeeVo);
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        session.setAttribute("approvalHomeVo", appHomeVo);
        session.setAttribute("writeVo", writeVo);
        session.setAttribute("loginEmployeeVo", loginEmployeeVo);
        session.setAttribute("dto", approvalDetailsDto);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "/approval/list/writeVac");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/list/writeVac")
    public String wv(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        ApprovalDetailsDto dto = (ApprovalDetailsDto) session.getAttribute("dto");
        System.out.println("dto = " + dto);
        ApprovalVo vo=service.getVoApp(dto.getApprovalNo());

        if (dto == null) {
            // DTO가 세션에 없으면 적절한 처리 (예: 에러 페이지로 리다이렉트)
            return "redirect:/error";
        }

        // 모델에 DTO를 추가
        model.addAttribute("dto", dto);
        model.addAttribute("vo",vo);

        // 뷰 리졸버를 통해 뷰 페이지 반환
        return "approval/list/writeVac";
    }



    @PostMapping("/vac")
    public String approvalWriteVacPost(@ModelAttribute AppVacVo appVacVo, Model model){

        System.out.println("appVacVo = " + appVacVo);
        if ("save".equals(appVacVo.getAction())||"save1".equals(appVacVo.getAction())) {
            if ("save1".equals(appVacVo.getAction())){
                if("휴가".equals(appVacVo.getLeaveForm())){
                    service.getDelApp(appVacVo.getApprovalNo());
                }else{
                    service.getDelApp2(appVacVo.getApprovalNo());
                }

            }
            service.saveApproval(appVacVo);
            return "redirect:/approval/home";
        } else if ("submit".equals(appVacVo.getAction())||"submit1".equals(appVacVo.getAction())) {
            if ("submit1".equals(appVacVo.getAction())){
                if("휴가".equals(appVacVo.getLeaveForm())){
                    service.getDelApp(appVacVo.getApprovalNo());
                }else{
                    service.getDelApp2(appVacVo.getApprovalNo());
                }
            }
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
    @PostMapping("/response/search")
    public String approvalListResponseSearch(@RequestParam(name = "pro", defaultValue = "all") String pro,HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVoResSearch(empId,pro);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/list/response";
    }


    //결재 작성 중
    @GetMapping("/write")
    public String approvalListWrite(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVoResIng(empId);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/list/write";
    }

    //내 결재 목록
    @GetMapping("/my")
    public String myFinAll(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);

        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVo2(empId);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/list/my";
    }

    @PostMapping("/my/search")
    public String searchApprovals(@RequestParam(name = "typeApp", defaultValue = "all") String typeApp, @RequestParam(name = "status", defaultValue = "all") String status,HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);
        ListApprovalVo listApprovalVo=service.getlistApprovalVoSea(empId,typeApp,status);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        model.addAttribute("approvalHomeVo", appHomeVo);


        System.out.println("종류: " + typeApp);
        System.out.println("상태: " + status);



        return "approval/list/my";
    }

    //전체 결재 목록
    @GetMapping("/all")
    public String approvalFinAll(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);
        List<EmployeeVo> empVo=service.getEmpVo();

        model.addAttribute("empVo",empVo);
        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVoAll(empId);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/fin/all";
    }
    //전체 결재 목록
    @PostMapping("/all/search")
    public String approvalFinAllSearch(@RequestParam(name = "typeApp", defaultValue = "all") String typeApp, @RequestParam(name = "emp", defaultValue = "all") String emp,HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);
        List<EmployeeVo> empVo=service.getEmpVo();

        model.addAttribute("empVo",empVo);
        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVoAllSearch(typeApp,emp,empId);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/fin/all";
    }
    //부서 결재 목록
    @GetMapping("/dept")
    public String approvalFinDept(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        if (loginEmployeeVo == null) {
            return "redirect:/login";
        }
        String empId = loginEmployeeVo.getEmpId();
        String empDept=loginEmployeeVo.getDepartmentNo();
        ApprovalHomeVo appHomeVo = service.getApprovalHome(empId);
        List<EmployeeVo> empVo=service.getEmpVo();

        model.addAttribute("empVo",empVo);
        model.addAttribute("approvalHomeVo", appHomeVo);
        ListApprovalVo listApprovalVo=service.getlistApprovalVoDept(empId,empDept);
        model.addAttribute("listApprovalVo",listApprovalVo);
        model.addAttribute("loginEmployeeVo", loginEmployeeVo);
        return "approval/fin/dept";
    }







    //개인 결재 목록
    @GetMapping("/private")
    public String approvalFinPrivate(){return "approval/fin/private";}


    @GetMapping("/test")
    public String test(){
        return "approval/list/response_ex";
    }

}