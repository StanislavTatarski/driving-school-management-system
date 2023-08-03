package ee.drivingschool.service;

import ee.drivingschool.repository.DrivingLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrivingLessonService {

    @Autowired
    private DrivingLessonRepository drivingLessonRepository;

}
