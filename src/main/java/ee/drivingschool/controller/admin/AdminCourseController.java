package ee.drivingschool.controller.admin;

import ee.drivingschool.dto.*;
import ee.drivingschool.model.Course;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.service.CourseService;
import ee.drivingschool.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminCourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;

    public AdminCourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }
    @GetMapping("/")
    public String getCourses(final ModelMap modelMap) {
        List<CourseDto> courseList = courseService.getAllCourses();
        modelMap.addAttribute("coursesList", courseList);
        return "index";
    }

    // ---------------------- CREATE NEW COURSE ----------------------
    @GetMapping("/admin/course/create")
    public String showCreateCourseForm(ModelMap modelMap) {
        CourseDto courseDto = new CourseDto();
        List<TeacherDto> teachers = teacherService.getAllTeachersDto();
        modelMap.addAttribute("teachers", teachers);
        modelMap.addAttribute("course", courseDto);
        return "create-course";
    }
    @PostMapping("/admin/course")
    public String createCourse(@ModelAttribute("course") CourseCreationRequestDto courseCreationRequestDto) {
        courseService.save(courseCreationRequestDto);
        return "redirect:/";
    }

    //---------------------- EDIT COURSE ----------------------
    @GetMapping("/admin/course/{id}")
    public String showEditCourseForm(@PathVariable("id") Long id, ModelMap modelMap) {

        CourseEditDto courseEditDto = courseService.getCourseEditDtoById(id);
        List<TeacherDto> teachers = teacherService.getAllTeachersDto();
        modelMap.addAttribute("teachers", teachers);
        modelMap.addAttribute("course", courseEditDto);
        return "edit-course";
    }
    @PostMapping("/admin/course/{id}")
    public String editCourse(@PathVariable("id") Long id, @ModelAttribute("course") CourseEditRequestDto courseEditRequestDto) {
        courseService.edit(id, courseEditRequestDto);
        return "redirect:/";
    }

}
