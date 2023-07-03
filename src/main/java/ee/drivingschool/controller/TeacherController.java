package ee.drivingschool.controller;

import ee.drivingschool.dto.TeacherCreationRequestDto;
import ee.drivingschool.dto.TeacherResponseDto;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping(path = "/teacher")
    public ResponseEntity<TeacherResponseDto> createTeacher(@RequestBody TeacherCreationRequestDto teacherCreationRequestDto) {

        TeacherResponseDto savedTeacher = teacherService.save(teacherCreationRequestDto);

        return ResponseEntity.ok(savedTeacher);
    }
}
