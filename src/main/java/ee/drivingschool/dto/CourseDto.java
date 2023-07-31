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

    public String getStatusCssClass() {
        if (status == null) {
            return "";
        }
        switch (status) {
            case ACTIVE -> {
                return "text-bg-success";
            }
            case INACTIVE -> {
                return "text-bg-danger";
            }
            case DELETED -> {
                return "text-bg-secondary";
            }
            case PENDING -> {
                return "text-bg-warning";
            }
            default -> {
                return "text-bg-info";
            }
        }
    }

//    public String getStartDate() {
//        return DateUtils.convertLocalDateToString(startDate);
//    }
//
//    public String getEndDate() {
//        return DateUtils.convertLocalDateToString(endDate);
//    }
    // TODO: fix this code later
}
