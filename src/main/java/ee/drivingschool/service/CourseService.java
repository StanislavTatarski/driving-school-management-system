package ee.drivingschool.service;

import ee.drivingschool.dto.CourseCreationRequestDto;
import ee.drivingschool.dto.CourseDto;
import ee.drivingschool.dto.CourseResponseDto;
import ee.drivingschool.model.Course;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.repository.CourseRepository;
import ee.drivingschool.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return toCourseDto(courses);
    }

    private List<CourseDto> toCourseDto(List<Course> courses) {

        List<CourseDto> courseDtoList = new ArrayList<>();

        for(Course course : courses) {
            courseDtoList.add(toCourseDto(course));
        }
        return courseDtoList;
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public CourseDto save(CourseCreationRequestDto courseCreationRequestDto) {

        Course course = toCourse2(courseCreationRequestDto);

        Course sevedCourse = courseRepository.save(course);

        return toCourseDto(sevedCourse);
    }

    private CourseDto toCourseDto(Course course) {

        Teacher teacher = course.getTeacher();

        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setCategory(course.getCategory());
        courseDto.setStartDate(course.getStartDate());
        courseDto.setEndDate(course.getEndDate());
        if(teacher != null) {
            courseDto.setTeacherName(teacher.getFullName());
        }
        return courseDto;
    }

    private Course toCourse2(CourseCreationRequestDto courseCreationRequestDto) {
        Teacher teacher = teacherRepository.getReferenceById(courseCreationRequestDto.getTeacherId());
        Course course = Course.builder()
                .courseName(courseCreationRequestDto.getCourseName())
                .category(courseCreationRequestDto.getCategory())
                .startDate(courseCreationRequestDto.getStartDate())
                .endDate(courseCreationRequestDto.getEndDate())
                .teacher(teacher)
                .build();
        return course;
    }
    private CourseResponseDto toCourseResponseDto(Course savedCourse) {

        CourseResponseDto courseResponseDto = new CourseResponseDto();
        courseResponseDto.setId(savedCourse.getId());
        courseResponseDto.setCourseName(savedCourse.getCourseName());
        courseResponseDto.setCategory(savedCourse.getCategory());
        courseResponseDto.setStartDate(savedCourse.getStartDate());
        courseResponseDto.setEndDate(savedCourse.getEndDate());
        return courseResponseDto;

    }


}
