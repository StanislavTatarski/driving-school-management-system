package ee.drivingschool;

import ee.drivingschool.model.User;
import ee.drivingschool.repository.CourseRepository;
import ee.drivingschool.repository.StudentRepository;
import ee.drivingschool.repository.TeacherRepository;
import ee.drivingschool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DrivingschoolApplication implements CommandLineRunner {

	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;

	@Autowired
	private UserRepository userRepository;

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

		User User1 = new User (1L, "student@example.com","$2a$12$87QIQUw8bH5nTBhCRvt0qOzXfKHhzUPObp1ZDsBmBpA/i5KOrybW2", false);
		User User2 = new User (2L, "teacher@example.com","$2a$12$87QIQUw8bH5nTBhCRvt0qOzXfKHhzUPObp1ZDsBmBpA/i5KOrybW2", false);
		User admin1 = new User(3L, "admin@example.com","$2a$12$87QIQUw8bH5nTBhCRvt0qOzXfKHhzUPObp1ZDsBmBpA/i5KOrybW2", true);

		userRepository.save(User1);
		userRepository.save(User2);
		userRepository.save(admin1);

/*		Teacher teacher1 = createTeacher("Jason", "Bourne", "+37254348984", "Tuukri 15-17",
				"jason@gmail.com");
		Teacher teacher2 = createTeacher("Mark", "Peterson", "+37254758990", "Laagri 15-17",
				"mark@gmail.com");

		Course course1 = createCourse(teacher1,"50-T-A-23-01", "B-category", LocalDate.now(), LocalDate.now());
		Course course2 = createCourse(teacher2,"50-T-A-23-02", "A-category", LocalDate.now(), LocalDate.now());


		Student student1 = createStudent(course1, "Mark", "Sillan", "384567846354",
				"+37234758763", "Gonsiori 12", "mark@gmail.com");
		Student student2 = createStudent(course1, "Billy", "Bob", "384561146399",
				"+37234758743", "Mustamäe tee 121-15", "billy@gmail.com");


		System.out.println("New course created successfully" + course1 + course2);
		System.out.println("New teacher created successfully" + teacher1 + teacher2);
		System.out.println("New student created successfully" + student1 + student2);

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

	private Student createStudent(Course course, String firstName, String lastName, String idCode,
								  String phone, String address, String email) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setIdCode(idCode);
		student.setPhone(phone);
		student.setAddress(address);
		student.setEmail(email);
		student.setCourse(course);
		return studentRepository.save(student);*/

	}
}
