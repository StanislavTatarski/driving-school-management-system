package ee.drivingschool.controller;

import ee.drivingschool.dto.CourseCreationRequestDto;
import ee.drivingschool.dto.CourseDto;
import ee.drivingschool.dto.CourseResponseDto;
import ee.drivingschool.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "/course")
    public ResponseEntity<List<CourseDto>> getCourse() {
        List<CourseDto> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
//    @PostMapping(path = "/course")
//    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody CourseCreationRequestDto courseCreationRequestDto) {
//        CourseResponseDto savedCourse = courseService.save(courseCreationRequestDto);
//        return ResponseEntity.ok(savedCourse);
//    }
}
