package ee.drivingschool.dto;

import ee.drivingschool.model.Course;
import ee.drivingschool.model.Student;
import ee.drivingschool.model.Teacher;
import lombok.Data;

@Data
public class DrivingCardDto {

    private Long id;
    private Course courseName;
    private Student studentName;
    private Teacher teacherName;

}
