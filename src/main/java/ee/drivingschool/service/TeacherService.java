package ee.drivingschool.service;

import ee.drivingschool.dto.TeacherCreationRequestDto;
import ee.drivingschool.dto.TeacherResponseDto;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public TeacherResponseDto save(TeacherCreationRequestDto teacherCreationRequestDto) {

        Teacher savedTeacher = toTeacher(teacherCreationRequestDto);
        return toTeacherResponseDto(savedTeacher);

//        return teacherResponseDto;
    }

    private Teacher toTeacher(TeacherCreationRequestDto teacherCreationRequestDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherCreationRequestDto.getFirstName());
        teacher.setLastName(teacherCreationRequestDto.getLastName());
        teacher.setPhone(teacherCreationRequestDto.getPhone());
        teacher.setAddress(teacherCreationRequestDto.getAddress());
        teacher.setEmail(teacherCreationRequestDto.getEmail());
        return teacherRepository.save(teacher);
    }
    private static TeacherResponseDto toTeacherResponseDto(Teacher savedTeacher) {

        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setFirstName(savedTeacher.getFirstName());
        teacherResponseDto.setLastName(savedTeacher.getLastName());
        teacherResponseDto.setPhone(savedTeacher.getPhone());
        teacherResponseDto.setAddress(savedTeacher.getAddress());
        teacherResponseDto.setEmail(savedTeacher.getEmail());
        return teacherResponseDto;
    }
}
