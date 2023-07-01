package ee.drivingschool;

import ee.drivingschool.model.Course;
import ee.drivingschool.model.Student;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.repository.CourseRepository;
import ee.drivingschool.repository.StudentRepository;
import ee.drivingschool.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class DrivingschoolApplication implements CommandLineRunner {

	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;

	public DrivingschoolApplication(CourseRepository courseRepository,
									StudentRepository studentRepository,
									TeacherRepository teacherRepository) {
		this.courseRepository = courseRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
	}




	public static void main(String[] args) {
		SpringApplication.run(DrivingschoolApplication.class, args);
	}

//	@Transactional
	@Override
	public void run(String... args) throws Exception {

		Teacher teacher1 = createTeacher("Jason", "Bourne", "+37254348984", "Tuukri 15-17",
				"jason@gmail.com");
		Teacher teacher2 = createTeacher("Mark", "Peterson", "+37254758990", "Laagri 15-17",
				"mark@gmail.com");

		Course course1 = createCourse(teacher1,"50-T-A-23-01", "B-category", LocalDate.now(), LocalDate.now());
		Course course2 = createCourse(teacher2,"50-T-A-23-02", "A-category", LocalDate.now(), LocalDate.now());



		System.out.println("New course created successfully" + course1 + course2);
		System.out.println("New teacher created successfully" + teacher1 + teacher2);

		Teacher teacher = teacherRepository.findTeacherById(21L).get(0);
		System.out.println("Teacher1:" + teacher);
	}



	private Course createCourse(Teacher teacher, String name, String category, LocalDate startDate, LocalDate endDate) {

		Course course = new Course();
		course.setCourseName(name);
		course.setCategory(category);
		course.setStartDate(startDate);
		course.setEndDate(endDate);
		course.setTeacher(teacher);
		return courseRepository.save(course);
	}
	private Teacher createTeacher(String firstName, String lastName, String phone, String address,
										String email) {

		Teacher teacher = new Teacher();
		teacher.setFirstName(firstName);
		teacher.setLastName(lastName);
		teacher.setPhone(phone);
		teacher.setAddress(address);
		teacher.setEmail(email);
		return teacherRepository.save(teacher);
	}

}
