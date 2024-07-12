package smile.office.groupware.approval.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.approval.service.ApprovalService;
import smile.office.groupware.approval.vo.ApprovalHomeVo;
import smile.office.groupware.approval.vo.list.AppAlListVo;
import smile.office.groupware.approval.vo.list.ApprovalDetailsDto;
import smile.office.groupware.approval.vo.list.ListApprovalVo;
import smile.office.groupware.approval.vo.list.SendMessageRequestVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/approval/api")
@CrossOrigin
@RequiredArgsConstructor
public class ApprovalRestController {
    private final ApprovalService service;


    //결재 중 처리현황
    @PostMapping("/ing")
    public ResponseEntity<ListApprovalVo> appListIng(@RequestBody Map<String, Object> payload){
        // approvalNo 값 추출
        int approvalNo = Integer.parseInt(payload.get("approvalNo").toString());

        // 비즈니스 로직 수행 (예: 데이터베이스 조회 등)
        List<AppAlListVo> appAlListVoList = service.getApprovalLines(approvalNo);

        // ListApprovalVo 객체 생성
        ListApprovalVo response = new ListApprovalVo();
        response.setAppAlListVoList(appAlListVoList);

        System.out.println("appAlListVoList = " + appAlListVoList);
        // JSON 응답 반환
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/sendMessage")
    public String getSendMessage(@RequestBody SendMessageRequestVo req, HttpServletRequest request){
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");

        String empNo=req.getEmpNo();
        String message=req.getMessage();

        System.out.println("empNo = " + empNo);
        System.out.println("message = " + message);

        if (loginEmployeeVo != null) {
            String empId = loginEmployeeVo.getEmpId();
            System.out.println("empId = " + empId);
            // 여기서 empNo와 message를 사용하여 원하는 작업 수행 (예: DB에 데이터 삽입 등)
            service.getSendMassage(empId, empNo, message);
            return "success";  // 성공했을 경우 성공 페이지로 리다이렉트 예시
        } else {
            return "error";  // 로그인 정보가 없는 경우 등의 오류 처리
        }

    }

    //결재보고서 디테일
    @PostMapping("/tempDetail")
    public ResponseEntity<?> getApprovalDetails(@RequestBody ApprovalDetailsDto detailsDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");


        // 예시 데이터 생성
        ApprovalDetailsDto approvalDetails = new ApprovalDetailsDto();
        approvalDetails.setApprovalNo(detailsDto.getApprovalNo()); // 결재번호 설정
        approvalDetails.setEmpId(loginEmployeeVo.getEmpId()); // 로그인 직원 ID 설정
        approvalDetails.setApprover1("John Doe");
        approvalDetails.setApprover1ImageUrl("path/to/approver1/image.png");
        approvalDetails.setApprover2("Jane Smith");
        approvalDetails.setApprover2ImageUrl("path/to/approver2/image.png");
        approvalDetails.setApprover3("Michael Brown");
        approvalDetails.setApprover3ImageUrl("path/to/approver3/image.png");
        approvalDetails.setLeaveForm("Vacation Form");
        approvalDetails.setPriority("High");
        approvalDetails.setStartDate("2024-07-15");
        approvalDetails.setEndDate("2024-07-20");
        approvalDetails.setUsageCount(5);
        approvalDetails.setReason("Vacation");
        approvalDetails.setApprovalDate("2024-07-12");
        approvalDetails.setApprovalLine1("Approved (Response content)");
        approvalDetails.setApprovalLine2("Approved (Response content)");
        approvalDetails.setApprovalLine3("Approved (Response content)");

        return ResponseEntity.ok(approvalDetails);
    }

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