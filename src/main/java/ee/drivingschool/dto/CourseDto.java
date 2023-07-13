package ee.drivingschool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ee.drivingschool.model.Student;
import ee.drivingschool.model.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Data
public class CourseDto {

    private Long id;
    private String courseName;
    private String category;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long teacherId;
    private String teacherName;

}
