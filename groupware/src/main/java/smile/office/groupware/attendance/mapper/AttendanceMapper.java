package smile.office.groupware.attendance.mapper;

import org.apache.ibatis.annotations.*;
import smile.office.groupware.attendance.vo.AttendanceVo;
import smile.office.groupware.employee.vo.EmployeeVo;

import java.util.List;
@Mapper
public interface AttendanceMapper {
    //나의 출퇴근 근무시간 기록 불러오기
    @Select("SELECT * FROM ATTENDANCE WHERE EMP_ID = #{empId}")
    List<AttendanceVo> getAttendanceList(String empId);

    @Insert("INSERT INTO ATTENDANCE (ATT_NO, EMP_ID, W_DATE) VALUES (SEQ_ATTENDANCE.NEXTVAL, #{empId}, TRUNC(SYSDATE))")
    int insertStartTime(String empId);
    //퇴근시간없을때 퇴근시간기록
    @Update("UPDATE ATTENDANCE SET END_TIME = SYSDATE WHERE EMP_ID = #{empId} AND W_DATE = TRUNC(SYSDATE) AND END_TIME IS NULL")
    int updateEndTime(String empId);
    //퇴근시간 있을때 퇴근시간만 업데이트
    @Update("UPDATE ATTENDANCE SET END_TIME = SYSDATE WHERE EMP_ID = #{empId} AND W_DATE = TRUNC(SYSDATE)")
    int updateEndTimeAgain(String empId);
    //퇴근시간있는지 확인
    @Select("SELECT COUNT(*) FROM ATTENDANCE WHERE EMP_ID = #{empId} AND W_DATE = TRUNC(SYSDATE) AND END_TIME IS NOT NULL")
    int getCheckOutCountForToday(String empId);

    //출근시간 있는지 확인
    @Select("SELECT COUNT(*) FROM ATTENDANCE WHERE EMP_ID = #{empId} AND W_DATE = TRUNC(SYSDATE) AND START_TIME IS NOT NULL")
    int getCheckInCountForToday(String empId);


    //오늘의 출퇴근 기록 가져오기
    @Select("SELECT ATT_NO, E.EMP_ID, A.START_TIME, A.END_TIME,A.W_DATE,A.DAY_WORK_TIME FROM ATTENDANCE A JOIN EMPLOYEE E ON A.EMP_ID = E.EMP_ID WHERE E.EMP_ID = #{empId} AND A.W_DATE = TRUNC(SYSDATE)")
    AttendanceVo getTodayAttRecord (String empId);


    //내 근태 기간별조회
    @Select("""
            SELECT ATT_NO, EMP_ID, START_TIME, END_TIME, W_DATE, STATE, DAY_WORK_TIME
            FROM ATTENDANCE
            WHERE W_DATE >= TO_DATE(#{startDate}, 'YYYY-MM-DD')
            AND W_DATE <= TO_DATE(#{endDate}, 'YYYY-MM-DD') AND EMP_ID = #{empId}
            
            """)
    List<AttendanceVo> getAttendanceHistory(@Param("startDate")String startDate, @Param("endDate")String endDate,@Param("empId") String empId);
}
