package ee.drivingschool.dto;

import lombok.Data;

@Data
public class StudentCreationRequestDto {

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
