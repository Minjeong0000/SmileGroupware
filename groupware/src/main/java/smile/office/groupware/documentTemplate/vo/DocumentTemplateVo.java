package smile.office.groupware.documentTemplate.vo;

import lombok.Data;

@Data
public class DocumentTemplateVo {
    private String templateNo;
    private String approvalNo;
    private String documentCategoryNo;
    private String templateContent;
    private String createDate;
    private String updateDate;
    private String deleteDate;
    private String startDate;
    private String endDate;
    private String note;
}
