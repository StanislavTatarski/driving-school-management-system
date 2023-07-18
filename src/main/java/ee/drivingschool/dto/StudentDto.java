package ee.drivingschool.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String idCode;
    private String phone;
    private String email;
    private String address;
    private Long courseId;
    private String courseName;
}
