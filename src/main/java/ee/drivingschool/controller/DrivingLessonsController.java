package ee.drivingschool.controller;

import ee.drivingschool.model.DrivingCard;
import ee.drivingschool.model.DrivingLesson;
import ee.drivingschool.service.DrivingLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DrivingLessonsController {

    @Autowired
    private DrivingLessonService drivingLessonService;

    @GetMapping("/lessons")
    public String getAllDrivingLessons(ModelMap modelMap) {

        return "driving-lessons";
    }



}
