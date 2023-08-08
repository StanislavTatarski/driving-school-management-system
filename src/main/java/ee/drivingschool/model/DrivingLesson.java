package ee.drivingschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class DrivingLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "driving_card_id")
    private DrivingCard drivingCard;

    private String topic;

    private LocalDate startAt;

    private int durationInMinutes;

    private DrivingLessonStatus status;

    private String studentComment;
}
