package ee.drivingschool.controller.admin;

import ee.drivingschool.dto.StudentCreationRequestDto;
import ee.drivingschool.dto.StudentDto;
import ee.drivingschool.service.CourseService;
import ee.drivingschool.service.StudentService;
import ee.drivingschool.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RequestMapping(value = "/admin")
public class AdminStudentsController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public AdminStudentsController(StudentService studentService, CourseService courseService, TeacherService teacherService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping("/admin/students")
    public String getAllStudents(final ModelMap modelMap) {
        List<StudentDto> studentList = studentService.getAllStudents();
        modelMap.addAttribute("studentsList", studentList);
        return "admin-students";
    }

    // ---------------------- CREATE NEW STUDENT ----------------------
    @GetMapping("/admin/student/create")
    public String showCreateStudentForm(ModelMap modelMap) {
        StudentDto studentDto = new StudentDto();
        modelMap.addAttribute("studentDto", studentDto);
        return "create-student";
    }
    @PostMapping("admin/student")
    public String createStudent(@ModelAttribute("studentDto") StudentCreationRequestDto studentCreationRequestDto) {
        studentService.save(studentCreationRequestDto);
        return "redirect:/admin/students";
    }


}
