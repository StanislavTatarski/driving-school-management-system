package ee.drivingschool.dto;

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

}
