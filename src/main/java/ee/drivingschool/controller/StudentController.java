package ee.drivingschool.controller;

import ee.drivingschool.model.Student;
import ee.drivingschool.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping(path = "/student")
    public List<Student> getStudent() {
        return studentService.getAllStudents();
    }
}
