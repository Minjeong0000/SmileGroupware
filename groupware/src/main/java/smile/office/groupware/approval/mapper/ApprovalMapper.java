package smile.office.groupware.approval.mapper;

import org.apache.ibatis.annotations.*;
import smile.office.groupware.approval.vo.*;
import smile.office.groupware.approval.vo.list.AlLVo;
import smile.office.groupware.approval.vo.list.AlListResVo;
import smile.office.groupware.approval.vo.list.AppAlListVo;
import smile.office.groupware.approval.vo.write.AppVacVo;
import smile.office.groupware.approvalLine.vo.ApprovalLineVo;
import smile.office.groupware.approvalResponse.vo.ApprovalResponseVo;
import smile.office.groupware.documentTemplate.vo.DocumentTemplateVo;
import smile.office.groupware.employee.vo.EmployeeVo;
import smile.office.groupware.prioritie.vo.PrioritieVo;
import smile.office.groupware.vacCate.vo.VacCateVo;
import smile.office.groupware.vacationTemplate.vo.VacationTemplateVo;

import java.net.URL;
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
            "    FROM APPROVAL_LINES AL\n" +
            "    JOIN STATUSES S ON S.STATUS_NO = AL.STATUS_NO\n" +
            "    JOIN APPROVALS A ON A.APPROVAL_NO = AL.APPROVAL_NO\n" +
            "    JOIN STATUSES S2 ON S2.STATUS_NO = A.STATUS_NO\n" +
            "    WHERE AL.EMP_ID = #{empId} AND S.STATUS_NO = 1 AND S2.STATUS_NO != 4\n" +
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
            "GROUP BY a.approval_no, p.priority_name, s.status_name, a.title, a.create_date, e.emp_name, VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME\n"+
            "ORDER BY a.approval_no DESC")
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
            "AND S.STATUS_NO != 4\n"+
            "ORDER BY a.approval_no DESC")
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
            "    s2.status_name AS status\n" +
            "FROM APPROVALS A\n" +
            "JOIN EMPLOYEE E ON E.EMP_ID = A.EMP_ID\n" +
            "JOIN priorities P ON P.priority_no = A.priority_no\n" +
            "JOIN statuses S ON S.status_no = A.status_no\n" +
            "JOIN approval_lines AL ON AL.approval_no = A.approval_no\n" +
            "JOIN statuses S2 ON S2.status_no=AL.status_no\n"+
            "LEFT JOIN VACATION_TEMPLATES VT ON VT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN VAC_CATE VC ON VC.VAC_CATE_NO = VT.VAC_CATE_NO\n" +
            "LEFT JOIN DOCUMENT_TEMPLATES DT ON DT.APPROVAL_NO = A.APPROVAL_NO\n" +
            "LEFT JOIN DOCUMENT_CATEGORY DC ON DC.DOCUMENT_CATEGORY_NO = DT.DOCUMENT_CATEGORY_NO\n" +
            "LEFT JOIN EMPLOYEE E2 ON E2.emp_id = AL.emp_id\n" +
            "WHERE Al.EMP_ID = #{empId}\n" +
            "AND S.STATUS_NO = 1\n" +
            "AND S2.STATUS_NO = 1\n"+
            "GROUP BY a.approval_no, p.priority_name, s2.status_name, a.title, a.content,a.create_date, e.emp_name, VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME")
    List<ApprovalListVo> getAppListIngRes(String empId);

    @Select("SELECT APPROVAL_LINE_NO\n" +
            "FROM APPROVAL_LINES \n" +
            "WHERE APPROVAL_NO =#{appNo}\n" +
            "AND EMP_ID =#{empId}")
    String getLineNo(@Param("appNo") String appNo, @Param("empId") String empId);

    @Insert("INSERT INTO APPROVAL_RESPONSES(RESPONSE_NO,APPROVAL_LINE_NO,RESPONSE_TEXT,SIGN)\n" +
            "VALUES(SEQ_APPROVAL_RESPONSES_NO.NEXTVAL,#{lineNo},#{responseText},#{signUrl})")
    void insertAppAl(@Param("lineNo") String lineNo, @Param("signUrl") String signUrl, @Param("responseText") String responseText, @Param("empId") String empId);

    @Update("UPDATE APPROVAL_LINES \n" +
            "SET STATUS_NO=2\n" +
            "WHERE APPROVAL_LINE_NO=#{lineNo}")
    void updateAl(@Param("lineNo") String lineNo);

    @Select("SELECT AL.APPROVAL_LINE_NO AS APPROVAL_LINE_NO \n" +
            "\t,AL.EMP_ID AS EMP_ID \n" +
            "\t,E.EMP_NAME AS EMP_NAME\n" +
            "\t,AL.SEQ AS SEQ\n"+
            "FROM APPROVAL_LINES AL\n" +
            "JOIN EMPLOYEE E ON E.EMP_ID =AL.EMP_ID \n" +
            "WHERE APPROVAL_NO =#{approvalNo} \n"+
            "ORDER BY al.APPROVAL_LINE_NO asc")
    List<AlListResVo> getApprovalLine(@Param("approvalNo") String approvalNo);


    @Select("<script>" +
            "SELECT RESPONSE_TEXT, SIGN " +
            "FROM APPROVAL_RESPONSES AR " +
            "WHERE AR.APPROVAL_LINE_NO IN " +
            "<foreach item='item' collection='approvalLineNos' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "ORDER BY RESPONSE_NO ASC" +
            "</script>")
    List<ApprovalResponseVo> getResponseAl(@Param("approvalLineNos") List<String> approvalLineNos);

    @Select("SELECT a.APPROVAL_NO , p.PRIORITY_NAME ,e.EMP_NAME ,a.TITLE ,a.CONTENT ,TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate ,a.APPROVAL_RANGE,a.APPROVAL_TYPE \n" +
            "FROM APPROVALS a\n" +
            "JOIN STATUSES s ON s.STATUS_NO =a.STATUS_NO\n" +
            "JOIN PRIORITIES p ON p.PRIORITY_NO =a.PRIORITY_NO\n" +
            "JOIN EMPLOYEE e ON e.EMP_ID =a.EMP_ID \n" +
            "WHERE APPROVAL_NO =#{approvalNo}")
    ApprovalVo getApprovalVo(@Param("approvalNo") String approvalNo);

    @Select("SELECT VACATION_TEMPLATES_NO ,APPROVAL_NO ,VAC_CATE_NAME ,\n" +
            "\tTEMPLATE_CONTENT ,TO_CHAR(START_DATE,'YY\"년\"MM\"월\"DD\"일\"') as START_DATE,TO_CHAR(END_DATE,'YY\"년\"MM\"월\"DD\"일\"') as END_DATE,USE_CNT ,\n" +
            "\tTO_CHAR(CREATE_DATE,'YY\"년\"MM\"월\"DD\"일\"') as CREATE_DATE,TO_CHAR(UPDATE_DATE,'YY\"년\"MM\"월\"DD\"일\"') AS UPDATE_DATE ,TO_CHAR(DELETE_DATE,'YY\"년\"MM\"월\"DD\"일\"') as DELETE_DATE,NOTE \n" +
            "FROM VACATION_TEMPLATES vt \n" +
            "JOIN VAC_CATE vc ON vc.VAC_CATE_NO =vt.VAC_CATE_NO \n" +
            "WHERE vt.APPROVAL_NO =#{approvalNo}")
    VacationTemplateVo getVacTempVo(@Param("approvalNo") String approvalNo);

    @Select("SELECT TEMPLATE_NO ,APPROVAL_NO,DOCUMENT_CATEGORY_NAME ,TEMPLATE_CONTENT,\n" +
            "\tTO_CHAR(CREATE_DATE,'YY\"년\"MM\"월\"DD\"일\"') as CREATE_DATE,TO_CHAR(UPDATE_DATE,'YY\"년\"MM\"월\"DD\"일\"') AS UPDATE_DATE ,TO_CHAR(DELETE_DATE,'YY\"년\"MM\"월\"DD\"일\"') as DELETE_DATE,NOTE,\n" +
            "\tTO_CHAR(START_DATE,'YY\"년\"MM\"월\"DD\"일\"') as START_DATE,TO_CHAR(END_DATE,'YY\"년\"MM\"월\"DD\"일\"') as END_DATE\n" +
            "FROM DOCUMENT_TEMPLATES dt \n" +
            "JOIN DOCUMENT_CATEGORY dc ON dc.DOCUMENT_CATEGORY_NO =dt.DOCUMENT_CATEGORY_NO \n" +
            "WHERE dt.APPROVAL_NO =#{approvalNo}")
    DocumentTemplateVo getDocTempVo(@Param("approvalNo") String approvalNo);

    @Select("SELECT *\n" +
            "FROM APPROVAL_LINES al \n" +
            "WHERE APPROVAL_NO =#{approvalNo}\n" +
            "AND EMP_ID =#{empId}")
    String al(@Param("empId") String empId,@Param("approvalNo") String approvalNo);

    @Update("UPDATE APPROVAL_LINES \n" +
            "SET STATUS_NO =3\n" +
            "WHERE EMP_ID =#{empId}\n" +
            "AND APPROVAL_NO =#{approvalNo}")
    void alDes(@Param("empId") String empId,@Param("approvalNo") String approvalNo);

    @Insert("INSERT INTO APPROVAL_RESPONSES(RESPONSE_NO,APPROVAL_LINE_NO,RESPONSE_TEXT)\n" +
            "VALUES(SEQ_APPROVAL_RESPONSES_NO.NEXTVAL,#{al},#{response})")
    void setAlRes(@Param("al") String al,@Param("response") String response);

    @Update("UPDATE APPROVALS \n" +
            "SET STATUS_NO =3\n" +
            "WHERE APPROVAL_NO =#{approvalNo}")
    void aDes(@Param("approvalNo") String approvalNo);

    @Select("SELECT seq\n" +
            "FROM APPROVAL_LINES\n" +
            "WHERE EMP_ID =#{empId}\n" +
            "AND APPROVAL_NO =#{appNo}")
    String getSeq(@Param("appNo") String appNo,@Param("empId") String empId);

    @Update("UPDATE APPROVALS \n" +
            "SET STATUS_NO =2\n" +
            "WHERE APPROVAL_NO =#{appNo}")
    void updateA(@Param("appNo") String appNo);

    @Select("SELECT " +
            "    a.approval_no AS approvalNo, " +
            "    p.priority_name AS priority, " +
            "    COALESCE(vc.vac_cate_name, dc.document_category_name) AS category, " +
            "    a.title AS title, " +
            "    a.content AS content, " +
            "    TO_CHAR(a.create_date, 'YY\"년\"MM\"월\"DD\"일\"') AS createDate, " +
            "    e.emp_name AS approver, " +
            "    LISTAGG(e2.emp_name, '|') WITHIN GROUP (ORDER BY e2.role_no DESC) AS approvalLine, " +
            "    s2.status_name AS status " +
            "FROM approvals a " +
            "JOIN employee e ON e.emp_id = a.emp_id " +
            "JOIN priorities p ON p.priority_no = a.priority_no " +
            "JOIN statuses s ON s.status_no = a.status_no " +
            "JOIN approval_lines al ON al.approval_no = a.approval_no " +
            "JOIN statuses s2 ON s2.status_no = al.status_no " +
            "LEFT JOIN vacation_templates vt ON vt.approval_no = a.approval_no " +
            "LEFT JOIN vac_cate vc ON vc.vac_cate_no = vt.vac_cate_no " +
            "LEFT JOIN document_templates dt ON dt.approval_no = a.approval_no " +
            "LEFT JOIN document_category dc ON dc.document_category_no = dt.document_category_no " +
            "LEFT JOIN employee e2 ON e2.emp_id = al.emp_id " +
            "WHERE al.emp_id = #{empId} " +
            "AND s.status_no = 4 " +
            "AND s2.status_no = 1 " +
            "GROUP BY a.approval_no, p.priority_name, COALESCE(vc.vac_cate_name, dc.document_category_name), a.title, a.content, a.create_date, e.emp_name, s2.status_name")
    List<ApprovalListVo> getAppListIngResIng(@Param("empId") String empId);

    @Select("SELECT *\n" +
            "FROM APPROVALS\n" +
            "WHERE APPROVAL_NO =#{approvalNo}")
    ApprovalVo getVoApp(@Param("approvalNo") String approvalNo);

    @Delete("BEGIN\n" +
            "    -- 자식 테이블에서 레코드 삭제\n" +
            "    DELETE FROM APPROVAL_LINES\n" +
            "    WHERE approval_no = #{approvalNo};\n" +
            "\n" +
            "    DELETE FROM VACATION_TEMPLATES\n" +
            "    WHERE approval_no = #{approvalNo};\n" +
            "\n" +
            "    -- 부모 테이블에서 레코드 삭제\n" +
            "    DELETE FROM APPROVALS\n" +
            "    WHERE approval_no = #{approvalNo};\n" +
            "END" +
            ";")
    void getDelApp(@Param("approvalNo") String approvalNo);

    @Delete("BEGIN\n" +
            "    -- 자식 테이블에서 레코드 삭제\n" +
            "    DELETE FROM APPROVAL_LINES\n" +
            "    WHERE approval_no = #{approvalNo};\n" +
            "\n" +
            "    DELETE FROM DOCUMENT_TEMPLATES\n" +
            "    WHERE approval_no = #{approvalNo};\n" +
            "\n" +
            "    -- 부모 테이블에서 레코드 삭제\n" +
            "    DELETE FROM APPROVALS\n" +
            "    WHERE approval_no = #{approvalNo};\n" +
            "END" +
            ";")
    void getDelApp2(@Param("approvalNo") String approvalNo);

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
            "AND S.STATUS_NO in(1,2,3)\n" +
            "GROUP BY a.approval_no, p.priority_name, s.status_name, a.title, a.create_date, e.emp_name, VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME")
    List<ApprovalListVo> getAppListIng2(String empId);

    @SelectProvider(type = ApprovalSqlProvider.class, method = "getAppListIngSea")
    List<ApprovalListVo> getAppListIngSea(@Param("empId") String empId,@Param("typeApp") String typeApp,@Param("status") String status);

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
            "WHERE S.STATUS_NO in(2,3)\n" +
            "GROUP BY a.approval_no, p.priority_name, s.status_name, a.title, a.create_date, e.emp_name, VC.VAC_CATE_NAME, DC.DOCUMENT_CATEGORY_NAME")
    List<ApprovalListVo> getAppListIngAll(String empId);

    @Select("SELECT *\n" +
            "FROM EMPLOYEE")
    List<EmployeeVo> getEmpVo();

    @SelectProvider(type = ApprovalSqlProvider.class, method = "getAppListIngAllSearch")
    List<ApprovalListVo> getAppListIngAllSearch(@Param("typeApp") String typeApp,@Param("emp") String emp,@Param("empId") String empId);

    @Select("WITH RECURSIVE_DEPARTMENTS (DEPARTMENT_NO, DEPARTMENT_NAME, SUPERIOR_DEPARTMENT_NO) AS (\n" +
            "    SELECT \n" +
            "        DEPARTMENT_NO,\n" +
            "        DEPARTMENT_NAME,\n" +
            "        SUPERIOR_DEPARTMENT_NO\n" +
            "    FROM \n" +
            "        DEPARTMENTS\n" +
            "    WHERE \n" +
            "        DEPARTMENT_NO = #{empDept}\n" +
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
            "    E.EMP_NAME AS empName\n" +
            "FROM \n" +
            "    EMPLOYEE E\n" +
            "JOIN \n" +
            "    RECURSIVE_DEPARTMENTS D ON E.DEPARTMENT_NO = D.DEPARTMENT_NO\n" +
            "LEFT JOIN \n" +
            "    DEPARTMENTS SD ON D.SUPERIOR_DEPARTMENT_NO = SD.DEPARTMENT_NO\n" +
            "JOIN \n" +
            "    ROLES R ON E.ROLE_NO = R.ROLE_NO\n" +
            "JOIN \n" +
            "    POSITION P ON E.POSITION_NO = P.POSITION_NO")
    List<EmployeeVo> getHighEmpVo(@Param("empId") String empId,@Param("empDept") String empDept);

    @SelectProvider(type = ApprovalSqlProvider.class, method = "getAppListIngResSearch")
    List<ApprovalListVo> getAppListIngResSearch(@Param("empId") String empId,@Param("pro") String pro);

    @Select("SELECT APPROVAL_TYPE \n" +
            "FROM APPROVALS\n" +
            "WHERE APPROVAL_NO = #{appNo}")
    String getVacType(@Param("appNo") String appNo);

    @Select("SELECT USE_CNT \n" +
            "FROM VACATION_TEMPLATES\n" +
            "WHERE APPROVAL_NO = #{appNo}")
    String getUseCnt(@Param("appNo") String appNo);

    @Update("UPDATE EMPLOYEE \n" +
            "SET REMAIN_VAC_CNT = REMAIN_VAC_CNT - #{use}\n" +
            "WHERE EMP_ID = #{apEmp}")
    void updateAppUse(@Param("apEmp") String apEmp,@Param("use") String use);

    @Select("SELECT EMP_ID \n" +
            "FROM APPROVALS\n" +
            "WHERE APPROVAL_NO =#{appNo}")
    String getApEmp(@Param("appNo") String appNo);
}