package ee.drivingschool.controller.admin;

import ee.drivingschool.dto.CourseDto;
import ee.drivingschool.dto.StudentCreationRequestDto;
import ee.drivingschool.dto.StudentDto;
import ee.drivingschool.dto.StudentEditDto;
import ee.drivingschool.service.CourseService;
import ee.drivingschool.service.StudentService;
import ee.drivingschool.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
        List<StudentDto> studentList = studentService.getAllStudentsDto();
        modelMap.addAttribute("studentsList", studentList);
        return "admin-students";
    }

    // ---------------------- CREATE NEW STUDENT ----------------------
    @GetMapping("/admin/student/create")
    public String showCreateStudentForm(ModelMap modelMap) {
        StudentDto studentDto = new StudentDto();
        List<CourseDto> courses = courseService.getAllCourses();
        modelMap.addAttribute("studentDto", studentDto);
        modelMap.addAttribute("courses", courses);
        return "create-student";
    }
    @PostMapping("admin/student/create")
    public String createStudent(@ModelAttribute("studentDto") StudentCreationRequestDto studentCreationRequestDto) {
        studentService.save(studentCreationRequestDto);
        return "redirect:/admin/students";
    }

    // ---------------------- EDIT STUDENT ----------------------
    @GetMapping("/admin/student/{id}")
    public String showEditStudentForm(@PathVariable("id") Long id, ModelMap modelMap) {
        StudentEditDto studentEditDto = studentService.getStudentDtoById(id);
        modelMap.addAttribute("student", studentEditDto);
        return "edit-student";
    }
    @PostMapping("/admin/student/{id}")
    public String editStudent(@PathVariable("id") Long id, @ModelAttribute("student") StudentEditDto studentEditDto) {
        studentService.edit(id, studentEditDto);
        return "redirect:/admin/students";
    }


}
