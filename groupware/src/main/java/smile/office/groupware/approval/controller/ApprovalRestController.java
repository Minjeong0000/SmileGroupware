package smile.office.groupware.approval.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import smile.office.groupware.approval.service.ApprovalService;
import smile.office.groupware.approval.vo.ApprovalHomeVo;
import smile.office.groupware.approval.vo.ApprovalVo;
import smile.office.groupware.approval.vo.UploadRequestVo;
import smile.office.groupware.approval.vo.list.*;
import smile.office.groupware.approvalResponse.vo.ApprovalResponseVo;
import smile.office.groupware.documentTemplate.vo.DocumentTemplateVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.vacationTemplate.vo.VacationTemplateVo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/approval/api")
@CrossOrigin
@RequiredArgsConstructor
public class ApprovalRestController {
    private final ApprovalService service;

    private final AmazonS3 s3;
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

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
    public ResponseEntity<?> getApprovalDetails(@RequestBody ApprovalDetailsDto detailsDto, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        String approvalNo=detailsDto.getApprovalNo();

        List<AlListResVo> alListResVos=service.getAlLisetResVos(approvalNo);
        List<ApprovalResponseVo> approvalResponseVos=service.getResponseAl(alListResVos);
        ApprovalVo appVo=service.getApprovalVo(approvalNo);
        String appType=appVo.getApprovalType();
        model.addAttribute("appType", appType);

        VacationTemplateVo vacationTemplateVo=new VacationTemplateVo();
        DocumentTemplateVo documentTemplateVo=new DocumentTemplateVo();

        if(appType.equals("휴가")){
            vacationTemplateVo=service.getVacTempVo(approvalNo);
        }else {
            documentTemplateVo=service.getDocTempVo(approvalNo);
        }

        String basicUrl="https://smilegroup.s3.ap-southeast-2.amazonaws.com/74958d4d-351c-4893-8294-17dccb7f1ace-image.png";
        String basicText="";

        

        ApprovalDetailsDto approvalDetails = new ApprovalDetailsDto();
        approvalDetails.setApprovalNo(approvalNo); // 결재번호 설정
        approvalDetails.setEmpId(appVo.getEmpName()); // 로그인 직원 ID 설정
        
        if(approvalResponseVos.size()>=3){
            approvalDetails.setApprover1(alListResVos.get(0).getEmpName());
            approvalDetails.setApprover1ImageUrl(approvalResponseVos.get(0).getSign());
            approvalDetails.setApprover2(alListResVos.get(1).getEmpName());
            approvalDetails.setApprover2ImageUrl(approvalResponseVos.get(1).getSign());
            approvalDetails.setApprover3(alListResVos.get(2).getEmpName());
            approvalDetails.setApprover3ImageUrl(approvalResponseVos.get(2).getSign());
            approvalDetails.setApprovalLine1(approvalResponseVos.get(0).getResponseText());
            approvalDetails.setApprovalLine2(approvalResponseVos.get(1).getResponseText());
            approvalDetails.setApprovalLine3(approvalResponseVos.get(2).getResponseText());
        } else if (approvalResponseVos.size()==2) {
            approvalDetails.setApprover1(alListResVos.get(0).getEmpName());
            approvalDetails.setApprover1ImageUrl(approvalResponseVos.get(0).getSign());
            approvalDetails.setApprover2(alListResVos.get(1).getEmpName());
            approvalDetails.setApprover2ImageUrl(approvalResponseVos.get(1).getSign());
            approvalDetails.setApprover3(alListResVos.get(2).getEmpName());
            approvalDetails.setApprover3ImageUrl(basicUrl);
            approvalDetails.setApprovalLine1(approvalResponseVos.get(0).getResponseText());
            approvalDetails.setApprovalLine2(approvalResponseVos.get(1).getResponseText());
            approvalDetails.setApprovalLine3(basicText);
        } else if (approvalResponseVos.size()==1) {
            approvalDetails.setApprover1(alListResVos.get(0).getEmpName());
            approvalDetails.setApprover1ImageUrl(approvalResponseVos.get(0).getSign());
            approvalDetails.setApprover2(alListResVos.get(1).getEmpName());
            approvalDetails.setApprover2ImageUrl(basicUrl);
            approvalDetails.setApprover3(alListResVos.get(2).getEmpName());
            approvalDetails.setApprover3ImageUrl(basicUrl);
            approvalDetails.setApprovalLine1(approvalResponseVos.get(0).getResponseText());
            approvalDetails.setApprovalLine2(basicText);
            approvalDetails.setApprovalLine3(basicText);
        } else{
            approvalDetails.setApprover1(alListResVos.get(0).getEmpName());
            approvalDetails.setApprover1ImageUrl(basicUrl);
            approvalDetails.setApprover2(alListResVos.get(1).getEmpName());
            approvalDetails.setApprover2ImageUrl(basicUrl);
            approvalDetails.setApprover3(alListResVos.get(2).getEmpName());
            approvalDetails.setApprover3ImageUrl(basicUrl);
            approvalDetails.setApprovalLine1(basicText);
            approvalDetails.setApprovalLine2(basicText);
            approvalDetails.setApprovalLine3(basicText);
        }
        approvalDetails.setPriority(appVo.getPriorityName());
        approvalDetails.setApprovalDateDetail(appVo.getCreateDate());
        approvalDetails.setLeaveForm(appVo.getApprovalType());
        if(appType.equals("휴가")){
            approvalDetails.setReason(vacationTemplateVo.getVacCateName());
            approvalDetails.setStartDate(vacationTemplateVo.getStartDate());
            approvalDetails.setEndDate(vacationTemplateVo.getEndDate());
            approvalDetails.setUsageCount(vacationTemplateVo.getUseCnt());
            approvalDetails.setTempContent(vacationTemplateVo.getTemplateContent());
            System.out.println("vacationTemplateVo = " + vacationTemplateVo);
        }else{
            approvalDetails.setReason(documentTemplateVo.getDocumentCategoryName());
            approvalDetails.setStartDate(documentTemplateVo.getStartDate());
            approvalDetails.setEndDate(documentTemplateVo.getEndDate());
            approvalDetails.setTempContent(documentTemplateVo.getTemplateContent());
            System.out.println("documentTemplateVo = " + documentTemplateVo);
        }




        return ResponseEntity.ok(approvalDetails);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFiles(@RequestBody UploadRequestVo requestVo,HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
        String empId = loginEmployeeVo.getEmpId();
        // Base64 이미지 데이터를 디코딩
        byte[] imageBytes = Base64.getDecoder().decode(requestVo.getImage().split(",")[1]);

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/png");
        metadata.setContentLength(imageBytes.length);

        // 파일 이름을 UUID로 변경
        String randomFileName = UUID.randomUUID().toString() + ".png";

        // S3에 업로드
        s3.putObject(bucketName, randomFileName, new ByteArrayInputStream(imageBytes), metadata);

        // 업로드된 파일의 URL 가져오기
        URL url = s3.getUrl(bucketName, randomFileName);
        String signUrl=url.toString();
        System.out.println("url = " + url);

        System.out.println("empId = " + empId);

        // 텍스트 응답 처리
        String responseText = requestVo.getResponse();
        String appNo=requestVo.getApprovalNo();
        System.out.println("응답 텍스트 = " + responseText);
        
        service.insertAppAl(responseText,appNo,signUrl,empId);

        return url.toString();
    }
    @PostMapping("/reject")
    @ResponseBody
    public ResponseEntity<String> rejectApproval(@RequestBody UploadRequestVo requestVo,HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            EmployeeVo loginEmployeeVo = (EmployeeVo) session.getAttribute("loginEmployeeVo");
            String empId = loginEmployeeVo.getEmpId();

            System.out.println("ApprovalRestController.rejectApproval");
            System.out.println("requestVo = " + requestVo);
            // 서비스 메서드를 호출하여 승인 요청 반려 처리
            service.rejectApproval(empId,requestVo.getApprovalNo(), requestVo.getResponse());

            // 성공 시 응답 반환
            return ResponseEntity.ok("반려되었습니다.");
        } catch (Exception e) {
            // 에러 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("에러 발생: " + e.getMessage());
        }
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