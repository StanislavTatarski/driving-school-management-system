package ee.drivingschool.repository;

import ee.drivingschool.model.DrivingLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingLessonRepository extends JpaRepository<DrivingLesson, Long> {
}
