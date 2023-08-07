package ee.drivingschool.service;

import ee.drivingschool.dto.StudentDto;
import ee.drivingschool.dto.TeacherCreationRequestDto;
import ee.drivingschool.dto.TeacherDto;
import ee.drivingschool.dto.TeacherResponseDto;
import ee.drivingschool.model.Course;
import ee.drivingschool.model.Student;
import ee.drivingschool.model.Teacher;
import ee.drivingschool.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return toTeacherDtoList(teachers);
    }

    private List<TeacherDto> toTeacherDtoList(List<Teacher> teachers) {
        List<TeacherDto> teacherDtoList = new ArrayList<>();

        for (Teacher teacher : teachers) {
            teacherDtoList.add(toTeacherDto2(teacher));
        }
        return teacherDtoList;
    }

    public TeacherResponseDto save(TeacherCreationRequestDto teacherCreationRequestDto) {

        Teacher savedTeacher = toTeacher(teacherCreationRequestDto);
        return toTeacherResponseDto(savedTeacher);

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

    private TeacherResponseDto toTeacherResponseDto(Teacher savedTeacher) {

        TeacherResponseDto teacherResponseDto = new TeacherResponseDto();
        teacherResponseDto.setFirstName(savedTeacher.getFirstName());
        teacherResponseDto.setLastName(savedTeacher.getLastName());
        teacherResponseDto.setPhone(savedTeacher.getPhone());
        teacherResponseDto.setAddress(savedTeacher.getAddress());
        teacherResponseDto.setEmail(savedTeacher.getEmail());
        return teacherResponseDto;
    }

    public List<TeacherDto> getAllTeachersDto() {
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (Teacher teacher : teacherRepository.findAll()) {
            teacherDtos.add(toTeacherDto(teacher));
        }
        return teacherDtos;
    }

    private TeacherDto toTeacherDto2(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setPhone(teacher.getPhone());
        teacherDto.setAddress(teacher.getAddress());
        teacherDto.setEmail(teacher.getEmail());
        return teacherDto;
    }

    private TeacherDto toTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        return teacherDto;
    }

    public Page<TeacherDto> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<Teacher> page = teacherRepository.findAll(pageable);
        List<TeacherDto> teacherDtoList = convertTeacherListToDto(page.getContent());
        return new PageImpl<>(teacherDtoList, pageable, page.getTotalElements());
    }

    private List<TeacherDto> convertTeacherListToDto(List<Teacher> teacherList) {
        List<TeacherDto> teacherDtoList = new ArrayList<>();
        for (Teacher teacher : teacherList) {
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.setId(teacher.getId());
            teacherDto.setFirstName(teacher.getFirstName());
            teacherDto.setAddress(teacher.getAddress());
            teacherDto.setPhone(teacher.getPhone());
            teacherDto.setEmail(teacher.getEmail());
            teacherDto.setLastName(teacher.getLastName());
            teacherDtoList.add(teacherDto);
        }
        return teacherDtoList;
    }


}
