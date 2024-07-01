package smile.office.groupware.approval.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smile.office.groupware.approval.service.ApprovalService;

@RestController
@RequestMapping("approval")
@RequiredArgsConstructor
public class ApprovalController {
    private final ApprovalService service;
    //결재 홈
    //결재 작성
    //내 결재 목록
    //전체 결재 목록
}
