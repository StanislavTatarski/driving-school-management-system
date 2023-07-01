package ee.drivingschool.repository;

import ee.drivingschool.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findTeacherById(Long id);
}
