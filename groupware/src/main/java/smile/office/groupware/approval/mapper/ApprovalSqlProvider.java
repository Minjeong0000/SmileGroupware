package smile.office.groupware.approval.mapper;

import org.apache.ibatis.jdbc.SQL;

public class ApprovalSqlProvider {
    public String getAppListIngSea(final String empId, final String typeApp, final String status) {
        return new SQL() {{
            SELECT("a.approval_no AS approvalNo");
            SELECT("p.priority_name AS priority");
            SELECT("COALESCE(VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME) AS category");
            SELECT("a.title AS title");
            SELECT("TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate");
            SELECT("e.emp_name AS approver");
            SELECT("LISTAGG(e2.emp_name, '|') WITHIN GROUP (ORDER BY e2.role_no DESC) AS approvalLine");
            SELECT("s.status_name AS status");
            FROM("APPROVALS A");
            JOIN("EMPLOYEE E ON E.EMP_ID = A.EMP_ID");
            JOIN("priorities P ON P.priority_no = A.priority_no");
            JOIN("statuses S ON S.status_no = A.status_no");
            JOIN("approval_lines AL ON AL.approval_no = A.approval_no");
            LEFT_OUTER_JOIN("VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO");
            LEFT_OUTER_JOIN("VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO");
            LEFT_OUTER_JOIN("DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO");
            LEFT_OUTER_JOIN("DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO");
            LEFT_OUTER_JOIN("EMPLOYEE E2 ON E2.emp_id = AL.emp_id");
            WHERE("A.EMP_ID = #{empId}");
            WHERE("S.STATUS_NO in (1, 2, 3)");

            if (!"all".equals(typeApp)) {
                WHERE("A.approval_type = #{typeApp}");
            }

            if (!"all".equals(status)) {
                WHERE("S.STATUS_NO = #{status}");
            }

            GROUP_BY("a.approval_no");
            GROUP_BY("p.priority_name");
            GROUP_BY("s.status_name");
            GROUP_BY("a.title");
            GROUP_BY("a.create_date");
            GROUP_BY("e.emp_name");
            GROUP_BY("VC.VAC_CATE_NAME");
            GROUP_BY("DC.DOCUMENT_CATEGORY_NAME");
        }}.toString();
    }

    public String getAppListIngAllSearch(final String typeApp, final String emp, final String empId) {
        return new SQL() {{
            SELECT("a.approval_no AS approvalNo");
            SELECT("p.priority_name AS priority");
            SELECT("COALESCE(VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME) AS category");
            SELECT("a.title AS title");
            SELECT("TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate");
            SELECT("e.emp_name AS approver");
            SELECT("LISTAGG(e2.emp_name, '|') WITHIN GROUP (ORDER BY e2.role_no DESC) AS approvalLine");
            SELECT("s.status_name AS status");
            FROM("APPROVALS A");
            JOIN("EMPLOYEE E ON E.EMP_ID = A.EMP_ID");
            JOIN("priorities P ON P.priority_no = A.priority_no");
            JOIN("statuses S ON S.status_no = A.status_no");
            JOIN("approval_lines AL ON AL.approval_no = A.approval_no");
            LEFT_OUTER_JOIN("VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO");
            LEFT_OUTER_JOIN("VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO");
            LEFT_OUTER_JOIN("DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO");
            LEFT_OUTER_JOIN("DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO");
            LEFT_OUTER_JOIN("EMPLOYEE E2 ON E2.emp_id = AL.emp_id");
            WHERE("S.STATUS_NO in (2, 3)");

            if (!"all".equals(typeApp)) {
                WHERE("A.approval_type = #{typeApp}");
            }

            if (!"all".equals(emp)) {
                WHERE("A.EMP_ID = #{emp}");
            }

            GROUP_BY("a.approval_no");
            GROUP_BY("p.priority_name");
            GROUP_BY("s.status_name");
            GROUP_BY("a.title");
            GROUP_BY("a.create_date");
            GROUP_BY("e.emp_name");
            GROUP_BY("VC.VAC_CATE_NAME");
            GROUP_BY("DC.DOCUMENT_CATEGORY_NAME");
        }}.toString();
    }

    public String getAppListIngResSearch(final String empId, final String pro) {
        return new SQL() {{
            SELECT("a.approval_no AS approvalNo");
            SELECT("p.priority_name AS priority");
            SELECT("COALESCE(VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME) AS category");
            SELECT("a.title AS title");
            SELECT("a.content AS content");
            SELECT("TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate");
            SELECT("e.emp_name AS approver");
            SELECT("LISTAGG(e2.emp_name, '|') WITHIN GROUP (ORDER BY e2.role_no DESC) AS approvalLine");
            SELECT("s2.status_name AS status");
            FROM("APPROVALS A");
            JOIN("EMPLOYEE E ON E.EMP_ID = A.EMP_ID");
            JOIN("priorities P ON P.priority_no = A.priority_no");
            JOIN("statuses S ON S.status_no = A.status_no");
            JOIN("approval_lines AL ON AL.approval_no = A.approval_no");
            JOIN("statuses S2 ON S2.status_no = AL.status_no");
            LEFT_OUTER_JOIN("VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO");
            LEFT_OUTER_JOIN("VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO");
            LEFT_OUTER_JOIN("DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO");
            LEFT_OUTER_JOIN("DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO");
            LEFT_OUTER_JOIN("EMPLOYEE E2 ON E2.emp_id = AL.emp_id");
            WHERE("AL.EMP_ID = #{empId}");
            WHERE("S.STATUS_NO = 1");
            WHERE("S2.STATUS_NO = 1");

            if (!"all".equals(pro)) {
                WHERE("P.priority_no = #{pro}");
            }

            GROUP_BY("a.approval_no");
            GROUP_BY("p.priority_name");
            GROUP_BY("s2.status_name");
            GROUP_BY("a.title");
            GROUP_BY("a.content");
            GROUP_BY("a.create_date");
            GROUP_BY("e.emp_name");
            GROUP_BY("VC.VAC_CATE_NAME");
            GROUP_BY("DC.DOCUMENT_CATEGORY_NAME");
        }}.toString();
    }
}
