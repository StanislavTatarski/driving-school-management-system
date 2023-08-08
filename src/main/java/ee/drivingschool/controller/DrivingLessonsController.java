package ee.drivingschool.controller;

import ee.drivingschool.dto.DrivingLessonsDto;
import ee.drivingschool.service.DrivingLessonService;
import ee.drivingschool.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@Controller
public class DrivingLessonsController {

    @Autowired
    private DrivingLessonService drivingLessonService;

    @GetMapping("/lessons")
    public String getAllDrivingLessons(@RequestParam(value = "date", required = false) String date, ModelMap modelMap) {
        LocalDate selectedDate = LocalDate.now();
        if (date != null) {
            selectedDate = DateUtils.convertDate(date);
        }
        List<DrivingLessonsDto> drivingLessonsList = drivingLessonService.getAllDrivingLessonsOnDate(selectedDate);
        modelMap.addAttribute("drivingLessonsList", drivingLessonsList);
        modelMap.addAttribute("selectedDate", DateUtils.convertLocalDateToString(selectedDate));
        return "driving-lessons";
    }

    // ---------------------- CREATE NEW DRIVING LESSON ----------------------
//    @GetMapping("/lesson/create")
//    public String showCreateDrivingLessonForm(ModelMap modelMap) {
//        List<DrivingLessonsDto> drivingLessonsDtos = drivingLessonService.getAllDrivingLessonsOnDate();
//        modelMap.addAttribute("drivingLessonsDto", drivingLessonsDtos);
//        return "driving-lesson-create";
//    }

}
