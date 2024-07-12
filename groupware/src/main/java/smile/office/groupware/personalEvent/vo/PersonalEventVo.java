package smile.office.groupware.personalEvent.vo;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
//@Builder
public class PersonalEventVo {

    private String personalNo;
    private String typeNo;
    private String reminderNo;
    private String empId;
    private String title;
    private String content;
    private String location;
    private String delYn;
    private LocalDateTime enrollDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;




}
