package ee.drivingschool.controller;

import ee.drivingschool.dto.*;
import ee.drivingschool.exception.StudentNotFoundException;
import ee.drivingschool.model.Status;
import ee.drivingschool.service.CourseService;
import ee.drivingschool.service.StudentService;
import ee.drivingschool.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    public String getAllStudents(@RequestParam(value = "courseId", required = false) Long courseId, final ModelMap modelMap) {
        List<StudentDto> studentList;
        if (courseId != null) {
            studentList = studentService.findCourseStudents(courseId);
        } else {
            studentList = studentService.getAllStudentsDto();
        }
        modelMap.addAttribute("studentsList", studentList);
        return "admin-students";
    }

    // ---------------------- CREATE NEW STUDENT ----------------------
    @GetMapping("/admin/student/create")
    public String showCreateStudentForm(StudentCreationRequestDto studentDto, ModelMap modelMap) {
        List<CourseDto> courses = courseService.getAllCoursesDto();
        modelMap.addAttribute("studentDto", studentDto);
        modelMap.addAttribute("courses", courses);
        modelMap.addAttribute("statuses", Status.values());
        return "create-student";
    }
    @PostMapping("admin/student/create")
    public String createStudent(@Valid @ModelAttribute("studentDto") StudentCreationRequestDto studentCreationRequestDto,
                                BindingResult result, ModelMap modelMap) {
        List<CourseDto> courses = courseService.getAllCoursesDto();
        if (result.hasErrors()) {
            modelMap.addAttribute("courses", courses);
            return "create-student";
        }
        studentService.save(studentCreationRequestDto);
        return "redirect:/admin/students";
    }

    // ---------------------- EDIT STUDENT ----------------------
    @GetMapping("/admin/student/{id}")
    public String showEditStudentForm(@PathVariable("id") Long id,  ModelMap modelMap) throws StudentNotFoundException {
        StudentEditDto studentEditDto = studentService.getStudentDtoById(id);
        List<CourseDto> courses = courseService.getAllCoursesDto();
        modelMap.addAttribute("student", studentEditDto);
        modelMap.addAttribute("courses", courses);
        modelMap.addAttribute("statuses", Status.values());
        return "edit-student";
    }
    @PostMapping("/admin/student/{id}")
    public String editStudent(@PathVariable("id") Long id,
                              @ModelAttribute("student")
                              StudentEditRequestDto studentEditRequestDto) throws StudentNotFoundException {
        studentService.edit(id, studentEditRequestDto);
        return "redirect:/admin/students";
    }


}
