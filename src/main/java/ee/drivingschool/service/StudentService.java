package ee.drivingschool.service;

import ee.drivingschool.dto.StudentCreationRequestDto;
import ee.drivingschool.dto.StudentDto;
import ee.drivingschool.model.Student;
import ee.drivingschool.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentDto> getAllStudents() {
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
        studentDto.setCourseName(student.getCourse().getCourseName());
        return studentDto;
    }

    public StudentDto save(StudentCreationRequestDto studentCreationRequestDto) {

        Student student = toStudent(studentCreationRequestDto);

        Student savedStudent = studentRepository.save(student);

        return toStudentDto(savedStudent);
    }

    private Student toStudent(StudentCreationRequestDto studentCreationRequestDto) {

        Student student = new Student();
        student.setFirstName(studentCreationRequestDto.getFirstName());
        student.setLastName(studentCreationRequestDto.getLastName());
        student.setIdCode(studentCreationRequestDto.getIdCode());
        student.setPhone(studentCreationRequestDto.getPhone());
        student.setEmail(studentCreationRequestDto.getEmail());
        student.setAddress(studentCreationRequestDto.getAddress());
        return student;
    }
}
