package ee.drivingschool.dto;


public class CourseDeleteRequestDto extends CourseCreationRequestDto {

    CourseDeleteRequestDto(String courseName, String category, String startDate, String endDate, Long teacherId) {
        super(courseName, category, startDate, endDate, teacherId);
    }
}
