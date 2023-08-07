package ee.drivingschool.service;

import ee.drivingschool.dto.StudentCreationRequestDto;
import ee.drivingschool.dto.StudentDto;
import ee.drivingschool.exception.CourseNotFoundException;
import ee.drivingschool.model.Course;
import ee.drivingschool.model.Student;
import ee.drivingschool.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllStudents_returnSuccessfully() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(createStudent(1L, "Mark", "Sillan", "384567846354", "+37234758763", "mark@gmail.com", "Gonsiori 12", null));
        students.add(createStudent(2L, "Billy", "Bob", "384561146399", "+37234758743", "billy@gmail.com", "Mustamäe tee 121-15", null));
        when(studentRepository.findAll()).thenReturn(students);

        // When
        List<StudentDto> studentDtoList = studentService.getAllStudentsDto();

        // Then
        assertEquals(2, studentDtoList.size());
        assertEquals(studentDtoList.get(0).getId(), null);

    }

    @Test
    public void testSave() throws CourseNotFoundException {
        // Given
        StudentCreationRequestDto requestDto = new StudentCreationRequestDto();
        requestDto.setFirstName("Mark");
        requestDto.setLastName("Sillan");
        requestDto.setIdCode("384567846354");
        requestDto.setPhone("+37234758763");
        requestDto.setEmail("mark@gmail.com");
        requestDto.setAddress("Gonsiori 12");

        Course course = createCourse(1L, "Test Course");
        when(courseService.findCourseById(1L)).thenReturn(course);

        // When
        StudentDto savedStudentDto = studentService.save(requestDto);

        // Then
        assertEquals(requestDto.getFirstName(), savedStudentDto.getFirstName());
        assertEquals(requestDto.getLastName(), savedStudentDto.getLastName());
        assertEquals(requestDto.getIdCode(), savedStudentDto.getIdCode());

    }

    private Student createStudent(Long id, String firstName, String lastName, String idCode, String phone, String email, String address, Course course) {
        Student student = new Student();
        student.setid(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setIdCode(idCode);
        student.setPhone(phone);
        student.setEmail(email);
        student.setAddress(address);
        student.setCourse(course);
        return student;
    }

    private Course createCourse(Long id, String courseName) {
        Course course = new Course();
        course.setId(id);
        course.setCourseName(courseName);
        return course;
    }
}
