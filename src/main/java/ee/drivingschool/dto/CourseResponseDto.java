package ee.drivingschool.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseResponseDto {

    private Long id;
    private String courseName;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
}
