package ee.drivingschool.dto;

public class CourseEditRequestDto extends CourseCreationRequestDto {

    CourseEditRequestDto(String courseName, String category, String startDate, String endDate, Long teacherId) {
        super(courseName, category, startDate, endDate, teacherId);
    }
}
