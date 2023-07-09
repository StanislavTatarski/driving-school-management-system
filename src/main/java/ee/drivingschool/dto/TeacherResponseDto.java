package ee.drivingschool.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeacherResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
}
