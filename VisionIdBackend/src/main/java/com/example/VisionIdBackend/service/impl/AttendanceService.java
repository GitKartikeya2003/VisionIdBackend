package com.example.VisionIdBackend.service.impl;


import com.example.VisionIdBackend.dto.ai.AIRequestDto;
import com.example.VisionIdBackend.dto.attendanceDtos.AttendanceDto;
import com.example.VisionIdBackend.dto.attendanceDtos.StudentAttendanceDto;
import com.example.VisionIdBackend.dto.attendanceDtos.dateAttendanceDto;
import com.example.VisionIdBackend.entity.*;
import com.example.VisionIdBackend.entity.enums.AttendanceStatus;
import com.example.VisionIdBackend.exception.ResourceNotFoundException;
import com.example.VisionIdBackend.exception.TeacherAlreadyExistsException;
import com.example.VisionIdBackend.mapper.AttendanceMapper;
import com.example.VisionIdBackend.repository.*;
import com.example.VisionIdBackend.service.IAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService implements IAttendanceService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final TeacherRepository teacherRepository;

    private final SubjectRepository subjectRepository;


    @Transactional
    public List<StudentEntity> processAIAttendance(AIRequestDto request, String teacherUid) {

        SubjectEntity entity = subjectRepository.findByCode(request.getSubjectCode()).orElseThrow(
                () -> new ResourceNotFoundException("Subject does not exists"));

        int earlier_classes = entity.getTotalClasses();
        earlier_classes += 1;
        entity.setTotalClasses(earlier_classes);

        ClassEntity classEntity = classRepository
                .findByBatchCode(request.getBatchCode())
                .orElseThrow(() ->
                        new RuntimeException("Invalid batch code: Class not found"));


        List<StudentEntity> students =
                studentRepository.findByBatch(classEntity);

        if (students.isEmpty()) {
            throw new ResourceNotFoundException(request.getBatchCode());
        }


        Set<String> recognizedRollSet =
                new HashSet<>(request.getRecognizedStudents());


        if (attendanceRepository
                .existsByStudentEntity_BatchAndDate(classEntity, request.getDate())) {

            throw new RuntimeException("Attendance already marked for this date");
        }


        TeacherEntity teacher = teacherRepository.findByUid(teacherUid);

        if (teacher == null) {
            throw new ResourceNotFoundException(teacherUid);
        }

        List<AttendanceEntity> attendanceList = new ArrayList<>();

        for (StudentEntity student : students) {

            AttendanceEntity attendance = new AttendanceEntity();
            attendance.setSubjectEntity(entity);
            attendance.setStudentEntity(student);
            attendance.setMarkedBy(teacher);
            attendance.setDate(request.getDate());

            if (recognizedRollSet.contains(student.getRollNo())) {
                attendance.setStatus(AttendanceStatus.PRESENT);
            } else {
                attendance.setStatus(AttendanceStatus.ABSENT);
            }

            attendanceList.add(attendance);

        }

        attendanceRepository.saveAll(attendanceList);


        return students;

    }

    @Override
    public List<AttendanceEntity> getAttendance_forDate_Subject(dateAttendanceDto dto, String uid) {
        TeacherEntity teacher = teacherRepository.findByUid(uid);

        if (teacher == null) {
            throw new ResourceNotFoundException("Teacher not found");
        }

        List<AttendanceEntity> attendanceEntities = attendanceRepository.findByDateAndSubjectEntity_SubjectName(dto.getDate(), dto.getSubject()).orElseThrow(
                () -> new ResourceNotFoundException("Attendance for Subject or Date does not exists")
        );


        return attendanceEntities;
    }

    @Override
    public List<StudentAttendanceDto> getAttendance_ForStudents_BatchWise(AttendanceDto dto, String uid) {


        TeacherEntity teacher = teacherRepository.findByUid(uid);

        if (teacher == null) {
            throw new ResourceNotFoundException("Teacher not found");
        }

        List<StudentEntity> students = attendanceRepository.findStudentsByTeacherAndSubjectAndBatch(uid, dto.getSubject(), dto.getBatchCode()).orElseThrow(
                () -> new ResourceNotFoundException("Students not found as per requirements")
        );

        List<StudentAttendanceDto> studentAttendanceDtoList = new ArrayList<>();

        for(StudentEntity student : students) {


            StudentAttendanceDto s = AttendanceMapper.toStudentAttendanceDto(student,0.0);
            studentAttendanceDtoList.add(s);
        }

        return studentAttendanceDtoList;
    }
}
