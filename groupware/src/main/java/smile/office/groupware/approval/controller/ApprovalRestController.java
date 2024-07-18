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
        approvalDetails.setApprover1("이용진");
        approvalDetails.setApprover1ImageUrl("http://192.168.40.14:5500/choiCeo.png");
        approvalDetails.setApprover2("강아지");
        approvalDetails.setApprover2ImageUrl("");
        approvalDetails.setApprover3("고양이");
        approvalDetails.setApprover3ImageUrl("");
        approvalDetails.setLeaveForm("휴가 관련 문서");
        approvalDetails.setPriority("높음");
        approvalDetails.setStartDate("24년 7월 15일");
        approvalDetails.setEndDate("24년 7월 20일");
        approvalDetails.setUsageCount(5);
        approvalDetails.setReason("Vacation");
        approvalDetails.setApprovalDateDetail("24년 7월 12일");
        approvalDetails.setApprovalLine1("수락했음");
        approvalDetails.setApprovalLine2("");
        approvalDetails.setApprovalLine3("");

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