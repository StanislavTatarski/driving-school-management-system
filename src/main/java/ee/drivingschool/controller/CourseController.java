package ee.drivingschool.controller;

import ee.drivingschool.model.Course;
import ee.drivingschool.service.CourseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping(path = "/course")
    public List<Course> getCourse() {
        return courseService.getAllCourses();
    }
}
