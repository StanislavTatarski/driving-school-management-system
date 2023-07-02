package ee.drivingschool.controller;

import ee.drivingschool.model.Teacher;
import ee.drivingschool.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {

   private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "/teacher")
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }
}
