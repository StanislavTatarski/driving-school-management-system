package ee.drivingschool.service;

import ee.drivingschool.dto.CourseDto;
import ee.drivingschool.model.Course;
import ee.drivingschool.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return toCourseDto(courses);
    }
    public Course course(Course course) {
        return courseRepository.save(course);
    }

    private List<CourseDto> toCourseDto(List<Course> courses) {

        List<CourseDto> courseDtoList = new ArrayList<>();

        for(Course course : courses) {
            courseDtoList.add(toCourseDto(course));
        }
        return courseDtoList;
    }
    private CourseDto toCourseDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCategory(course.getCategory());
        courseDto.setStartDate(course.getStartDate());
        courseDto.setEndDate(course.getEndDate());
        courseDto.setTeacherName(course.getTeacher().getFullName());
        return courseDto;
    }
}
