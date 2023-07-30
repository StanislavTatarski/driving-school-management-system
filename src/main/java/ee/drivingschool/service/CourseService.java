package ee.drivingschool.service;

import ee.drivingschool.dto.CourseCreationRequestDto;
import ee.drivingschool.dto.CourseDto;
import ee.drivingschool.dto.CourseEditDto;
import ee.drivingschool.dto.CourseEditRequestDto;
import ee.drivingschool.exception.CourseNotFoundException;
import ee.drivingschool.exception.Errors;
import ee.drivingschool.model.Course;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.repository.CourseRepository;
import ee.drivingschool.repository.TeacherRepository;
import org.springframework.stereotype.Service;

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

        for (Course course : courses) {
            courseDtoList.add(toCourseDto(course));
        }
        return courseDtoList;
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
        courseDto.setStatus(course.getStatus());
        if (teacher != null) {
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

    public Course findCourseById(Long id) throws CourseNotFoundException {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found", Errors.COURSE_NOT_FOUND));
    }

    private CourseEditDto toCourseEditDto(Course course) {
        Teacher teacher = course.getTeacher();
        CourseEditDto courseEditDto = new CourseEditDto();
        courseEditDto.setId(course.getId());
        courseEditDto.setCourseName(course.getCourseName());
        courseEditDto.setCategory(course.getCategory());
        courseEditDto.setStartDate(course.getStartDate());
        courseEditDto.setEndDate(course.getEndDate());
        courseEditDto.setStatus(course.getStatus());
        if (teacher != null) {
            courseEditDto.setTeacherId(teacher.getId());
            courseEditDto.setTeacherName(teacher.getFullName());
        }
        return courseEditDto;
    }

    public CourseEditDto getCourseEditDtoById(Long id) throws CourseNotFoundException {
        Course course = findCourseById(id);
        return toCourseEditDto(course);
    }

    public void edit(Long id, CourseEditRequestDto courseEditRequestDto) throws CourseNotFoundException {
        Teacher teacher = teacherRepository.getReferenceById(courseEditRequestDto.getTeacherId());
        Course course = findCourseById(id);
        course.setCourseName(courseEditRequestDto.getCourseName());
        course.setCategory(courseEditRequestDto.getCategory());
        course.setStartDate(courseEditRequestDto.getStartDate());
        course.setEndDate(courseEditRequestDto.getEndDate());
        course.setTeacher(teacher);
        course.setStatus(courseEditRequestDto.getStatus());
        courseRepository.save(course);
    }
}
