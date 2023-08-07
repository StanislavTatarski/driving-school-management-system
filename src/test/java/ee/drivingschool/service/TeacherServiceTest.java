package ee.drivingschool.service;

import ee.drivingschool.dto.TeacherCreationRequestDto;
import ee.drivingschool.dto.TeacherDto;
import ee.drivingschool.dto.TeacherResponseDto;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TeacherServiceTest {



    private TeacherService teacherService;
    private TeacherRepository teacherRepository;


    @BeforeEach
    public void setUp() {
        teacherRepository = mock(TeacherRepository.class);
        teacherService = new TeacherService(teacherRepository);
    }

    @Test
    public void GetAllTeachers_returnSuccessfully() {
        // GIVEN
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(createTeacher(1L, "Jason", "Bourne", "+372", "", "Jason@gmail.com"));
        teachers.add(createTeacher(2L, "Mark", "Peterson", "+372", "", "Mark@gmail.com"));

        // WHEN
        when(teacherRepository.findAll()).thenReturn(teachers);
        List<TeacherDto> teacherDtoList = teacherService.getAllTeachers();

        // THEN
        assertEquals(2, teacherDtoList.size());
    }

    @Test
    public void testSaveTeacher() {
        // GIVEN
        TeacherCreationRequestDto requestDto = new TeacherCreationRequestDto();
        requestDto.setFirstName("Jason");
        requestDto.setLastName("Bourne");
        requestDto.setPhone("+372");
        requestDto.setAddress("");
        requestDto.setEmail("jason@gmail.com");


        Teacher teacherToSave = createTeacher(1L, "Peeter", "Pakiraam", "+372123456", "", "Peeter@gmail.com");

        // WHEN
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacherToSave);
        TeacherResponseDto responseDto = teacherService.save(requestDto);

        // THEN
        assertEquals("Peeter", responseDto.getFirstName());
        assertEquals("Pakiraam", responseDto.getLastName());
        assertEquals("+372123456", responseDto.getPhone());
        assertEquals("", responseDto.getAddress());
        assertEquals("Peeter@gmail.com", responseDto.getEmail());
    }

    private Teacher createTeacher(Long id, String firstName, String lastName, String phone, String address, String email) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setPhone(phone);
        teacher.setAddress(address);
        teacher.setEmail(email);
        return teacher;
    }
}
