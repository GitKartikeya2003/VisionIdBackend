package com.example.VisionIdBackend.service.impl;


import com.example.VisionIdBackend.dto.ai.AIRequestDto;
import com.example.VisionIdBackend.entity.AttendanceEntity;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import com.example.VisionIdBackend.entity.TeacherEntity;
import com.example.VisionIdBackend.entity.enums.AttendanceStatus;
import com.example.VisionIdBackend.exception.ResourceNotFoundException;
import com.example.VisionIdBackend.repository.AttendanceRepository;
import com.example.VisionIdBackend.repository.ClassRepository;
import com.example.VisionIdBackend.repository.StudentRepository;
import com.example.VisionIdBackend.repository.TeacherRepository;
import com.example.VisionIdBackend.service.IAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService implements IAttendanceService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final TeacherRepository teacherRepository;


    @Transactional
    public void processAIAttendance(AIRequestDto request, String teacherEmail) {


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

//        for(StudentEntity student : students) {
//
//            if(attendanceRepository.findByStudentEntityAndDate(student,request.getDate()).isPresent()) {
//                throw new RuntimeException("Attendance already marked for this date");
//
//            }
//        }

        if (attendanceRepository
                .existsByStudentEntity_BatchAndDate(classEntity, request.getDate())) {

            throw new RuntimeException("Attendance already marked for this date");
        }



        TeacherEntity teacher = teacherRepository.findByEmail(teacherEmail).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        List<AttendanceEntity> attendanceList = new ArrayList<>();

        for(StudentEntity student : students) {

            AttendanceEntity attendance = new AttendanceEntity();

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

    }
}
