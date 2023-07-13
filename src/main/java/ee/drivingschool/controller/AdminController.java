package ee.drivingschool.controller;

import ee.drivingschool.dto.CourseDto;
import ee.drivingschool.service.CourseService;
import ee.drivingschool.service.StudentService;
import ee.drivingschool.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public AdminController(StudentService studentService, CourseService courseService, TeacherService teacherService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public String getCourses(final ModelMap modelMap) {
        List<CourseDto> courseList = courseService.getAllCourses();
        modelMap.addAttribute("coursesList", courseList);
        return "index";
    }
}
