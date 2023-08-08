package ee.drivingschool.service;

import ee.drivingschool.dto.DrivingCardDto;
import ee.drivingschool.model.*;
import ee.drivingschool.repository.DrivingCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverCardService {

    @Autowired
    private DrivingCardRepository drivingCardRepository;

    public List<DrivingCardDto> getAllDrivingCards() {
        List<DrivingCardDto> drivingCards = new ArrayList<>();

        for (DrivingCard drivingCard : drivingCardRepository.findAll()) {
            drivingCards.add(toDrivingCard(drivingCard));
        }
        return drivingCards;
    }

    private DrivingCardDto toDrivingCard(DrivingCard drivingCard) {
        DrivingCardDto drivingCardDto = new DrivingCardDto();
        drivingCardDto.setId(drivingCard.getId());
        return drivingCardDto;
    }

    public List<DrivingCardItem> getDrivingCardItemsList() {

        List<Object[]> drivingCardData = drivingCardRepository.findAllDrivingCardsWithNames();
        List<DrivingCardItem> drivingCardItems = new ArrayList<>();

        for (Object[] data : drivingCardData) {
            DrivingCardItem drivingCardItem = new DrivingCardItem();

            drivingCardItem.setId((Long) data[0]);
            drivingCardItem.setStudentFullName((String) data[1]);
            drivingCardItem.setTeacherFullName((String) data[2]);
            drivingCardItem.setCourseName((String) data[3]);
            drivingCardItem.setStudentId((Long) data[4]);
            drivingCardItem.setTeacherId((Long) data[5]);
            drivingCardItem.setCourseId((Long) data[6]);

            drivingCardItems.add(drivingCardItem);
        }

        return drivingCardItems;
    }

    public DrivingCard createNewDrivingCard(Student student) {

        Course course = student.getCourse();
        DrivingCard drivingCard = new DrivingCard(course, course.getTeacher(), student);
        return drivingCardRepository.save(drivingCard);
    }
}
