package smile.office.groupware.approval.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.approval.service.ApprovalService;
import smile.office.groupware.approval.vo.ApprovalHomeVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;

@RestController
@RequestMapping("api/approval")
@CrossOrigin
@RequiredArgsConstructor
public class ApprovalRestController {
    private final ApprovalService service;

//    @GetMapping("home")
//    public ResponseEntity<?> getApprovalHome(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
//        if (loginEmployeeVo == null) {
//            return ResponseEntity.internalServerError().body("로그인 후 이용해 주세요");
//        }
//        String empId = loginEmployeeVo.getEmpId();
//        List<ApprovalHomeVo> appVoList=service.getApprovalHome(empId);
//        return ResponseEntity.ok(appVoList);
//
//    }

}