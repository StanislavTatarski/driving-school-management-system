package ee.drivingschool.dto;

import ee.drivingschool.model.Status;
import ee.drivingschool.utils.DateUtils;
import lombok.*;

import java.time.LocalDate;

@Data
public class CourseDto {

    private Long id;
    private String courseName;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
    private String teacherName;
    private Status status;

//    public String getStartDate() {
//        return DateUtils.convertLocalDateToString(startDate);
//    }
//
//    public String getEndDate() {
//        return DateUtils.convertLocalDateToString(endDate);
//    }
    // TODO: fix this code later
}
