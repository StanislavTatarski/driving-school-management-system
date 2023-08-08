package ee.drivingschool.service;

import ee.drivingschool.dto.DrivingLessonsDto;
import ee.drivingschool.model.DrivingCard;
import ee.drivingschool.model.DrivingLesson;
import ee.drivingschool.model.DrivingLessonStatus;
import ee.drivingschool.repository.DrivingLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DrivingLessonService {

    @Autowired
    private DrivingLessonRepository drivingLessonRepository;

    public List<DrivingLessonsDto> getAllDrivingLessonsOnDate(LocalDate date) {
        List<DrivingLesson> drivingLessons = drivingLessonRepository.findAllByStartAt(date);
        return toDrivingLessonDtoList(drivingLessons);
    }

    private List<DrivingLessonsDto> toDrivingLessonDtoList(List<DrivingLesson> drivingLessons) {

        List<DrivingLessonsDto> drivingLessonsDto = new ArrayList<>();

        for (DrivingLesson drivingLesson : drivingLessons) {
            drivingLessonsDto.add(toDrivingLessonsDto(drivingLesson));
        }
        return drivingLessonsDto;
    }

    private DrivingLessonsDto toDrivingLessonsDto(DrivingLesson drivingLesson) {

        DrivingCard drivingCardId = drivingLesson.getDrivingCard();

        DrivingLessonsDto drivingLessonsDto = new DrivingLessonsDto();
        drivingLessonsDto.setId(drivingLesson.getId());
        drivingLessonsDto.setTopic(drivingLesson.getTopic());
        drivingLessonsDto.setStartAt(drivingLesson.getStartAt());
        drivingLessonsDto.setDurationInMinutes(drivingLesson.getDurationInMinutes());
        drivingLessonsDto.setStudentComment(drivingLesson.getStudentComment());

        if (drivingCardId != null) {
            drivingLessonsDto.setDrivingCardId(drivingCardId.getId());
        }

        DrivingLessonStatus status = drivingLesson.getStatus();
        if (status != null) {
            drivingLessonsDto.setStatus(drivingLesson.getStatus());
        }
        return drivingLessonsDto;
    }
}
