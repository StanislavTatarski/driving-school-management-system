package ee.drivingschool.service;

import ee.drivingschool.dto.StudentCreationRequestDto;
import ee.drivingschool.dto.StudentDto;
import ee.drivingschool.dto.StudentEditDto;
import ee.drivingschool.exception.CourseNotFoundException;
import ee.drivingschool.model.Course;
import ee.drivingschool.model.Student;
import ee.drivingschool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseService courseService;

    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public List<StudentDto> getAllStudentsDto() {
        List<Student> students = studentRepository.findAll();
        return toStudentDtoList(students);
    }

    private List<StudentDto> toStudentDtoList(List<Student> students) {

        List<StudentDto> studentDtoList = new ArrayList<>();

        for (Student student : students) {
            studentDtoList.add(toStudentDto(student));
        }
        return studentDtoList;
    }

    private StudentDto toStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setIdCode(student.getIdCode());
        studentDto.setPhone(student.getPhone());
        studentDto.setEmail(student.getEmail());
        studentDto.setAddress(student.getAddress());
        Course course = student.getCourse();
        if (course != null) {
            studentDto.setCourseName(course.getCourseName());
        }
        return studentDto;
    }

    public StudentDto save(StudentCreationRequestDto studentCreationRequestDto) {

        Student student = toStudent(studentCreationRequestDto);

        Student savedStudent = studentRepository.save(student);

        return toStudentDto(savedStudent);
    }

    private Student toStudent(StudentCreationRequestDto studentCreationRequestDto) {

        Course course;
        try {
            course = courseService.findCourseById(studentCreationRequestDto.getCourseId());
        } catch (CourseNotFoundException e) {
            course = null;
        }

        Student student = new Student();
        student.setFirstName(studentCreationRequestDto.getFirstName());
        student.setLastName(studentCreationRequestDto.getLastName());
        student.setIdCode(studentCreationRequestDto.getIdCode());
        student.setPhone(studentCreationRequestDto.getPhone());
        student.setEmail(studentCreationRequestDto.getEmail());
        student.setAddress(studentCreationRequestDto.getAddress());

        if (course != null) {
            student.setCourse(course);
        }
        return student;
    }

    public StudentEditDto getStudentDtoById(Long id) {

        Student student = findStudentById(id);

        return toStudentEditDto(student);
    }

    private StudentEditDto toStudentEditDto(Student student) {

        StudentEditDto studentEditDto = new StudentEditDto();
        studentEditDto.setFirstName(student.getFirstName());
        studentEditDto.setLastName(student.getLastName());
        studentEditDto.setIdCode(student.getIdCode());
        studentEditDto.setPhone(student.getPhone());
        studentEditDto.setEmail(student.getEmail());
        studentEditDto.setAddress(student.getAddress());
        return studentEditDto;
    }

    private Student findStudentById(Long id) throws RuntimeException {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void edit(Long id, StudentEditDto studentEditDto) {

        Student student = findStudentById(id);
        student.setFirstName(studentEditDto.getFirstName());
        student.setLastName(studentEditDto.getLastName());
        student.setIdCode(studentEditDto.getIdCode());
        student.setPhone(studentEditDto.getPhone());
        student.setEmail(studentEditDto.getEmail());
        student.setAddress(studentEditDto.getAddress());
    }
}
