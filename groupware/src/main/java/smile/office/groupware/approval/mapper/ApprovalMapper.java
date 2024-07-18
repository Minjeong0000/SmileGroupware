package smile.office.groupware.approval.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import smile.office.groupware.approval.vo.*;
import smile.office.groupware.approval.vo.list.AppAlListVo;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.prioritie.vo.PrioritieVo;
import smile.office.groupware.vacCate.vo.VacCateVo;

import java.util.List;

@Mapper
public interface ApprovalMapper {

    @Select("SELECT\n" +
            "    MAX(cntApprovalIng) AS cntApprovalIng,\n" +
            "    MAX(cntApprovalSave) AS cntApprovalSave,\n" +
            "    MAX(cntApprovalAll) AS cntApprovalAll\n" +
            "FROM (\n" +
            "    SELECT COUNT(*) AS cntApprovalIng, 0 AS cntApprovalSave, 0 AS cntApprovalAll\n" +
            "    FROM APPROVALS A\n" +
            "    JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "    JOIN STATUSES S ON A.STATUS_NO = S.STATUS_NO\n" +
            "    WHERE A.EMP_ID = #{empId} AND S.STATUS_NO = 1\n" +
            "    \n" +
            "    UNION ALL\n" +
            "    \n" +
            "    SELECT 0 AS cntApprovalIng, COUNT(*) AS cntApprovalSave, 0 AS cntApprovalAll\n" +
            "    FROM APPROVALS A\n" +
            "    JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "    JOIN STATUSES S ON A.STATUS_NO = S.STATUS_NO\n" +
            "    WHERE A.EMP_ID = #{empId} AND S.STATUS_NO IN(2,3)\n" +
            "    \n" +
            "    UNION ALL\n" +
            "    \n" +
            "    SELECT 0 AS cntApprovalIng, 0 AS cntApprovalSave, COUNT(*) AS cntApprovalAll\n" +
            "    FROM APPROVALS A\n" +
            "    JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "    JOIN STATUSES S ON A.STATUS_NO = S.STATUS_NO\n" +
            "    WHERE A.EMP_ID = #{empId} AND S.STATUS_NO = 4\n" +
            ") subquery")
    CntAppVo getCntApp(String empId);

    @Select("SELECT\n" +
            "            SUM(CASE WHEN VT.APPROVAL_NO IS NOT NULL THEN 1 ELSE 0 END) AS vacationCnt,\n" +
            "            SUM(CASE WHEN DT.APPROVAL_NO IS NOT NULL THEN 1 ELSE 0 END) AS projectCnt,\n" +
            "            SUM(CASE WHEN P.PRIORITY_NO = 1 THEN 1 ELSE 0 END) AS emergencyCnt,\n" +
            "            SUM(CASE WHEN P.PRIORITY_NO = 2 THEN 1 ELSE 0 END) AS normalCnt,\n" +
            "            SUM(CASE WHEN P.PRIORITY_NO = 3 THEN 1 ELSE 0 END) AS lowCnt\n" +
            "        FROM APPROVALS A\n" +
            "        JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "        JOIN priorities P ON P.priority_no = A.priority_no\n" +
            "        LEFT JOIN VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "        LEFT JOIN DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "        WHERE A.EMP_ID = #{empId}")
    TemCntVo getTemCnt(String empId);

    @Select("SELECT \n" +
            "    SUM(CASE WHEN S.STATUS_NO = 2 THEN 1 ELSE 0 END) AS approvalOkCnt,\n" +
            "    SUM(CASE WHEN S.STATUS_NO = 3 THEN 1 ELSE 0 END) AS approvalNoCnt,\n" +
            "    SUM(CASE WHEN S.STATUS_NO = 1 THEN 1 ELSE 0 END) AS approvalAIngCnt,\n" +
            "    SUM(CASE WHEN S.STATUS_NO = 4 THEN 1 ELSE 0 END) AS approvalWIngCnt,\n" +
            "    COUNT(*) AS approvalCount\n" +
            "FROM APPROVALS A\n" +
            "JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "JOIN STATUSES S ON A.STATUS_NO = S.STATUS_NO\n" +
            "WHERE A.EMP_ID = #{empId}")
    AppCntVo getAppCnt(String empId);

    @Select("SELECT\n" +
            "    a.approval_no AS approvalNo,\n" +
            "    p.priority_name AS priority,\n" +
            "    COALESCE(VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME) AS category,\n" +
            "    a.title AS title,\n" +
            "    TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate,\n" +
            "    e.emp_name AS approver,\n" +
            "    LISTAGG(e2.emp_name, '|') WITHIN GROUP (ORDER BY e2.role_no DESC) AS approvalLine,\n" +
            "    s.status_name AS status\n" +
            "FROM APPROVALS A\n" +
            "JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "JOIN priorities P ON P.priority_no = A.priority_no\n" +
            "JOIN statuses S ON S.status_no = A.status_no\n" +
            "JOIN approval_lines AL ON AL.approval_no = A.approval_no\n" +
            "LEFT JOIN VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO\n" +
            "LEFT JOIN DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO\n" +
            "LEFT JOIN EMPLOYEE E2 ON E2.emp_id = AL.emp_id\n" +
            "WHERE A.EMP_ID = #{empId}\n" +
            "AND S.STATUS_NO != 4\n" +
            "GROUP BY a.approval_no, p.priority_name, s.status_name, a.title, a.create_date, e.emp_name, VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME")
    List<ApprovalListVo> getAppList(String empId);

    @Select("SELECT \n" +
            "    a.approval_no AS approvalNo,\n" +
            "    E.EMP_NAME AS approver,\n" +
            "    AL.SEQ AS approvalOrder,\n" +
            "    E2.EMP_NAME AS approvalLine,\n" +
            "\tS2.STATUS_NAME AS processingStatus,\n" +
            "\tAR.RESPONSE_TEXT AS responseText,\n" +
            "\tTO_CHAR(AR.RESPONDED_DATE,'YY\"년\"MM\"월\"DD\"일\"') AS respondedDate\n" +
            "FROM APPROVALS A\n" +
            "JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "JOIN priorities P ON P.priority_no = A.priority_no\n" +
            "JOIN statuses S ON S.status_no = A.status_no\n" +
            "JOIN approval_lines AL ON AL.approval_no = A.approval_no\n" +
            "LEFT JOIN APPROVAL_RESPONSES AR ON AR.APPROVAL_LINE_NO = AL.APPROVAL_LINE_NO\n" +
            "JOIN STATUSES S2 ON S2.STATUS_NO = AL.STATUS_NO \n" +
            "LEFT JOIN VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO\n" +
            "LEFT JOIN DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO\n" +
            "LEFT JOIN EMPLOYEE E2 ON E2.EMP_ID = AL.EMP_ID\n" +
            "WHERE AL.EMP_ID = #{empId}\n" +
            "AND S.STATUS_NO != 4")
    List<ApprovalProcessVo> getAppProList(String empId);

    @Select("SELECT \n" +
            "\tVAC_CATE_NO as vacCateNo,\n" +
            "\tVAC_CATE_NAME AS vacCateName\n" +
            "FROM VAC_CATE")
    List<VacCateVo> getVacCateVoList();

    @Select("SELECT *\n" +
            "FROM PRIORITIES")
    List<PrioritieVo> getPriVoList();

    @Select("WITH RECURSIVE_DEPARTMENTS (DEPARTMENT_NO, DEPARTMENT_NAME, SUPERIOR_DEPARTMENT_NO) AS (\n" +
            "    SELECT \n" +
            "        DEPARTMENT_NO,\n" +
            "        DEPARTMENT_NAME,\n" +
            "        SUPERIOR_DEPARTMENT_NO\n" +
            "    FROM \n" +
            "        DEPARTMENTS\n" +
            "    WHERE \n" +
            "        DEPARTMENT_NO = #{departmentNo}\n" +
            "    UNION ALL\n" +
            "    SELECT \n" +
            "        D.DEPARTMENT_NO,\n" +
            "        D.DEPARTMENT_NAME,\n" +
            "        D.SUPERIOR_DEPARTMENT_NO\n" +
            "    FROM \n" +
            "        DEPARTMENTS D\n" +
            "    INNER JOIN \n" +
            "        RECURSIVE_DEPARTMENTS RD ON D.DEPARTMENT_NO = RD.SUPERIOR_DEPARTMENT_NO\n" +
            ")\n" +
            "SELECT \n" +
            "    E.EMP_ID AS empId,\n" +
            "    E.EMP_NAME || ' : ' || D.DEPARTMENT_NAME || '-' || P.POSITION_NAME AS empName\n" +
            "FROM \n" +
            "    EMPLOYEE E\n" +
            "JOIN \n" +
            "    RECURSIVE_DEPARTMENTS D ON E.DEPARTMENT_NO = D.DEPARTMENT_NO\n" +
            "LEFT JOIN \n" +
            "    DEPARTMENTS SD ON D.SUPERIOR_DEPARTMENT_NO = SD.DEPARTMENT_NO\n" +
            "JOIN \n" +
            "    ROLES R ON E.ROLE_NO = R.ROLE_NO\n" +
            "JOIN \n" +
            "    POSITION P ON E.POSITION_NO = P.POSITION_NO\n" +
            "WHERE E.EMP_ID != #{empId}\n" +
            "AND P.POSITION_NO < #{positionNo}\n" +
            "ORDER BY P.POSITION_NO DESC ")
    List<EmployeeVo> getEmpVoList2(EmployeeVo loginEmployeeVo);

    @Select("WITH RECURSIVE_DEPARTMENTS (DEPARTMENT_NO, DEPARTMENT_NAME, SUPERIOR_DEPARTMENT_NO) AS (\n" +
            "    SELECT \n" +
            "        DEPARTMENT_NO,\n" +
            "        DEPARTMENT_NAME,\n" +
            "        SUPERIOR_DEPARTMENT_NO\n" +
            "    FROM \n" +
            "        DEPARTMENTS\n" +
            "    WHERE \n" +
            "        DEPARTMENT_NO = #{departmentNo}\n" +
            "    UNION ALL\n" +
            "    SELECT \n" +
            "        D.DEPARTMENT_NO,\n" +
            "        D.DEPARTMENT_NAME,\n" +
            "        D.SUPERIOR_DEPARTMENT_NO\n" +
            "    FROM \n" +
            "        DEPARTMENTS D\n" +
            "    INNER JOIN \n" +
            "        RECURSIVE_DEPARTMENTS RD ON D.DEPARTMENT_NO = RD.SUPERIOR_DEPARTMENT_NO\n" +
            ")\n" +
            "SELECT \n" +
            "    E.EMP_ID AS empId,\n" +
            "    E.EMP_NAME || ' : ' || D.DEPARTMENT_NAME || '-' || P.POSITION_NAME AS empName\n" +
            "FROM \n" +
            "    EMPLOYEE E\n" +
            "JOIN \n" +
            "    RECURSIVE_DEPARTMENTS D ON E.DEPARTMENT_NO = D.DEPARTMENT_NO\n" +
            "LEFT JOIN \n" +
            "    DEPARTMENTS SD ON D.SUPERIOR_DEPARTMENT_NO = SD.DEPARTMENT_NO\n" +
            "JOIN \n" +
            "    ROLES R ON E.ROLE_NO = R.ROLE_NO\n" +
            "JOIN \n" +
            "    POSITION P ON E.POSITION_NO = P.POSITION_NO\n" +
            "WHERE E.EMP_ID != #{empId}\n" +
            "AND P.POSITION_NO < #{positionNo}-1\n" +
            "ORDER BY P.POSITION_NO DESC")
    List<EmployeeVo> getEmpVoList3(EmployeeVo loginEmployeeVo);

    @Select("WITH RECURSIVE_DEPARTMENTS (DEPARTMENT_NO, DEPARTMENT_NAME, SUPERIOR_DEPARTMENT_NO) AS (\n" +
            "    SELECT \n" +
            "        DEPARTMENT_NO,\n" +
            "        DEPARTMENT_NAME,\n" +
            "        SUPERIOR_DEPARTMENT_NO\n" +
            "    FROM \n" +
            "        DEPARTMENTS\n" +
            "    WHERE \n" +
            "        DEPARTMENT_NO = #{departmentNo}\n" +
            "    UNION ALL\n" +
            "    SELECT \n" +
            "        D.DEPARTMENT_NO,\n" +
            "        D.DEPARTMENT_NAME,\n" +
            "        D.SUPERIOR_DEPARTMENT_NO\n" +
            "    FROM \n" +
            "        DEPARTMENTS D\n" +
            "    INNER JOIN \n" +
            "        RECURSIVE_DEPARTMENTS RD ON D.DEPARTMENT_NO = RD.SUPERIOR_DEPARTMENT_NO\n" +
            ")\n" +
            "SELECT \n" +
            "    E.EMP_ID AS empId,\n" +
            "    E.EMP_NAME || ' : ' || '본인' AS empName\n" +
            "FROM \n" +
            "    EMPLOYEE E\n" +
            "JOIN \n" +
            "    RECURSIVE_DEPARTMENTS D ON E.DEPARTMENT_NO = D.DEPARTMENT_NO\n" +
            "LEFT JOIN \n" +
            "    DEPARTMENTS SD ON D.SUPERIOR_DEPARTMENT_NO = SD.DEPARTMENT_NO\n" +
            "JOIN \n" +
            "    ROLES R ON E.ROLE_NO = R.ROLE_NO\n" +
            "JOIN \n" +
            "    POSITION P ON E.POSITION_NO = P.POSITION_NO\n" +
            "WHERE E.EMP_ID = #{empId}\n" +
            "ORDER BY P.POSITION_NO DESC ")
    List<EmployeeVo> getEmpVoList1(EmployeeVo loginEmployeeVo);

    @Insert({
            "DECLARE",
            "  v_approval_no NUMBER;",
            "BEGIN",
            "  INSERT INTO APPROVALS(APPROVAL_NO, PRIORITY_NO, EMP_ID, STATUS_NO, TITLE, CONTENT, CREATE_DATE, APPROVAL_RANGE, TEMPORARY_STORAGE_YN)",
            "  VALUES(SEQ_APPROVALS_NO.NEXTVAL, #{prioritieNo}, #{appLine1}, #{statusNo}, #{appTitle}, #{appContent}, SYSDATE, #{range}, 'N')",
            "  RETURNING APPROVAL_NO INTO v_approval_no;",

            "  INSERT INTO APPROVAL_LINES(APPROVAL_LINE_NO, APPROVAL_NO, EMP_ID, STATUS_NO, SEQ)",
            "  VALUES(SEQ_APPROVAL_LINES_NO.NEXTVAL, v_approval_no, #{appLine1}, 1, 1);",

            "  INSERT INTO APPROVAL_LINES(APPROVAL_LINE_NO, APPROVAL_NO, EMP_ID, STATUS_NO, SEQ)",
            "  VALUES(SEQ_APPROVAL_LINES_NO.NEXTVAL, v_approval_no, #{appLine2}, 1, 2);",

            "  INSERT INTO APPROVAL_LINES(APPROVAL_LINE_NO, APPROVAL_NO, EMP_ID, STATUS_NO, SEQ)",
            "  VALUES(SEQ_APPROVAL_LINES_NO.NEXTVAL, v_approval_no, #{appLine3}, 1, 3);",

            "  INSERT INTO VACATION_TEMPLATES(VACATION_TEMPLATES_NO, APPROVAL_NO, VAC_CATE_NO, TEMPLATE_CONTENT, START_DATE, END_DATE, USE_CNT, CREATE_DATE, NOTE)",
            "  VALUES(SEQ_VACATION_TEMPLATES_NO.NEXTVAL, v_approval_no, #{vacNo}, #{vacContent}, TO_DATE(#{start}, 'YYYY-MM-DD'), TO_DATE(#{end}, 'YYYY-MM-DD'), #{use}, SYSDATE, #{note});",

            "  COMMIT;",
            "END;"
    })
    void saveApproval(AppVacVo appVacVo);

    @Insert({
            "DECLARE",
            "  v_approval_no NUMBER;",
            "BEGIN",
            "  INSERT INTO APPROVALS(APPROVAL_NO, PRIORITY_NO, EMP_ID, STATUS_NO, TITLE, CONTENT, CREATE_DATE, APPROVAL_RANGE, TEMPORARY_STORAGE_YN)",
            "  VALUES(SEQ_APPROVALS_NO.NEXTVAL, #{prioritieNo}, #{appLine1}, #{statusNo}, #{appTitle}, #{appContent}, SYSDATE, #{range}, 'N')",
            "  RETURNING APPROVAL_NO INTO v_approval_no;",

            "  INSERT INTO APPROVAL_LINES(APPROVAL_LINE_NO, APPROVAL_NO, EMP_ID, STATUS_NO, SEQ)",
            "  VALUES(SEQ_APPROVAL_LINES_NO.NEXTVAL, v_approval_no, #{appLine1}, 1, 1);",

            "  INSERT INTO APPROVAL_LINES(APPROVAL_LINE_NO, APPROVAL_NO, EMP_ID, STATUS_NO, SEQ)",
            "  VALUES(SEQ_APPROVAL_LINES_NO.NEXTVAL, v_approval_no, #{appLine2}, 1, 2);",

            "  INSERT INTO APPROVAL_LINES(APPROVAL_LINE_NO, APPROVAL_NO, EMP_ID, STATUS_NO, SEQ)",
            "  VALUES(SEQ_APPROVAL_LINES_NO.NEXTVAL, v_approval_no, #{appLine3}, 1, 3);",

            "  INSERT INTO VACATION_TEMPLATES(VACATION_TEMPLATES_NO, APPROVAL_NO, VAC_CATE_NO, TEMPLATE_CONTENT, START_DATE, END_DATE, USE_CNT, CREATE_DATE, NOTE)",
            "  VALUES(SEQ_VACATION_TEMPLATES_NO.NEXTVAL, v_approval_no, #{vacNo}, #{vacContent}, TO_DATE(#{start}, 'YYYY-MM-DD'), TO_DATE(#{end}, 'YYYY-MM-DD'), #{use}, SYSDATE, #{note});",

            "  COMMIT;",
            "END;"
    })
    void submitApprobal(AppVacVo appVacVo);

    @Select("SELECT\n" +
            "    a.approval_no AS approvalNo,\n" +
            "    p.priority_name AS priority,\n" +
            "    COALESCE(VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME) AS category,\n" +
            "    a.title AS title,\n" +
            "    TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate,\n" +
            "    e.emp_name AS approver,\n" +
            "    LISTAGG(e2.emp_name, '|') WITHIN GROUP (ORDER BY e2.role_no DESC) AS approvalLine,\n" +
            "    s.status_name AS status\n" +
            "FROM APPROVALS A\n" +
            "JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "JOIN priorities P ON P.priority_no = A.priority_no\n" +
            "JOIN statuses S ON S.status_no = A.status_no\n" +
            "JOIN approval_lines AL ON AL.approval_no = A.approval_no\n" +
            "LEFT JOIN VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO\n" +
            "LEFT JOIN DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO\n" +
            "LEFT JOIN EMPLOYEE E2 ON E2.emp_id = AL.emp_id\n" +
            "WHERE A.EMP_ID = #{empId}\n" +
            "AND S.STATUS_NO = 1\n" +
            "GROUP BY a.approval_no, p.priority_name, s.status_name, a.title, a.create_date, e.emp_name, VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME")
    List<ApprovalListVo> getAppListIng(String empId);

    @Select("SELECT E2.EMP_ID as empNo,E2.EMP_NAME as empName ,S.STATUS_NO as stNo,S.STATUS_NAME as stName  \n" +
            "FROM APPROVALS A\n" +
            "JOIN EMPLOYEE E ON A.EMP_ID =E.EMP_ID \n" +
            "JOIN APPROVAL_LINES AL ON AL.APPROVAL_NO =A.APPROVAL_NO \n" +
            "JOIN STATUSES S ON S.STATUS_NO =AL.STATUS_NO\n" +
            "JOIN EMPLOYEE E2 ON E2.EMP_ID =AL.EMP_ID \n" +
            "WHERE A.APPROVAL_NO =#{approvalNo}\n" +
            "ORDER BY AL.SEQ ")
    List<AppAlListVo> getApprovalLines(int approvalNo);

    @Insert("DECLARE\n" +
            "    P_MESSAGE_NO NUMBER;\n" +
            "    P_MESSAGE_USER_NO1 NUMBER;\n" +
            "    P_MESSAGE_USER_NO2 NUMBER;\n" +
            "BEGIN\n" +
            "    INSERT INTO MESSAGE (MESSAGE_NO, SENDER_NO, RECEIVER_NO, CONTENT)\n" +
            "    VALUES (SEQ_MESSAGE.NEXTVAL, #{empId}, #{empNo}, #{message})\n" +
            "    RETURNING MESSAGE_NO INTO P_MESSAGE_NO;\n" +
            "\n" +
            "    INSERT INTO MESSAGE_USER (MESSAGE_USER_NO, MESSAGE_NO, EMP_ID)\n" +
            "    VALUES (SEQ_MESSAGE_USER.NEXTVAL, P_MESSAGE_NO, #{empId})\n" +
            "    RETURNING MESSAGE_USER_NO INTO P_MESSAGE_USER_NO1;\n" +
            "\n" +
            "    INSERT INTO MESSAGE_USER (MESSAGE_USER_NO, MESSAGE_NO, EMP_ID)\n" +
            "    VALUES (SEQ_MESSAGE_USER.NEXTVAL, P_MESSAGE_NO, #{empNo})\n" +
            "    RETURNING MESSAGE_USER_NO INTO P_MESSAGE_USER_NO2;\n" +
            "END;\n")
    void getSendMassage(@Param("empId") String empId,
                        @Param("empNo") String empNo,
                        @Param("message") String message);

    @Select("SELECT\n" +
            "    a.approval_no AS approvalNo,\n" +
            "    p.priority_name AS priority,\n" +
            "    COALESCE(VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME) AS category,\n" +
            "    a.title AS title,\n" +
            "    a.content AS content,\n"+
            "    TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate,\n" +
            "    e.emp_name AS approver,\n" +
            "    LISTAGG(e2.emp_name, '|') WITHIN GROUP (ORDER BY e2.role_no DESC) AS approvalLine,\n" +
            "    s.status_name AS status\n" +
            "FROM APPROVALS A\n" +
            "JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "JOIN priorities P ON P.priority_no = A.priority_no\n" +
            "JOIN statuses S ON S.status_no = A.status_no\n" +
            "JOIN approval_lines AL ON AL.approval_no = A.approval_no\n" +
            "LEFT JOIN VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO\n" +
            "LEFT JOIN DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO\n" +
            "LEFT JOIN EMPLOYEE E2 ON E2.emp_id = AL.emp_id\n" +
            "WHERE Al.EMP_ID = #{empId}\n" +
            "AND S.STATUS_NO = 1\n" +
            "GROUP BY a.approval_no, p.priority_name, s.status_name, a.title, a.content,a.create_date, e.emp_name, VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME")
    List<ApprovalListVo> getAppListIngRes(String empId);
}