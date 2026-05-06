package com.example.VisionIdBackend.service.impl;


import com.example.VisionIdBackend.dto.ai.AIRequestDto;
import com.example.VisionIdBackend.dto.attendanceDtos.AttendanceDto;
import com.example.VisionIdBackend.dto.attendanceDtos.StudentAttendanceDto;
import com.example.VisionIdBackend.dto.attendanceDtos.dateAttendanceDto;
import com.example.VisionIdBackend.entity.*;
import com.example.VisionIdBackend.entity.enums.AttendanceStatus;
import com.example.VisionIdBackend.exception.ResourceNotFoundException;
import com.example.VisionIdBackend.mapper.AttendanceMapper;
import com.example.VisionIdBackend.repository.*;
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
    private final SubjectRepository subjectRepository;


    @Transactional
    public List<StudentEntity> processAIAttendance(AIRequestDto request, String teacherUid) {

        SubjectEntity entity = subjectRepository.findByCode(request.getSubjectCode()).orElseThrow(
                () -> new ResourceNotFoundException("Subject does not exist"));

        // NOTE: We no longer touch entity.totalClasses here.
        // totalClasses is now computed dynamically per teacher+batch from attendance records.

        ClassEntity classEntity = classRepository
                .findByBatchCode(request.getBatchCode())
                .orElseThrow(() -> new RuntimeException("Invalid batch code: Class not found"));

        List<StudentEntity> students = studentRepository.findByBatch(classEntity);

        if (students.isEmpty()) {
            throw new ResourceNotFoundException(request.getBatchCode());
        }

        Set<String> recognizedRollSet = new HashSet<>(request.getRecognizedStudents());

        if (attendanceRepository.existsByStudentEntity_BatchAndDateAndSubjectEntity_Code(
                classEntity, request.getDate(), request.getSubjectCode())) {
            throw new RuntimeException("Attendance already marked for this date and subject");
        }

        TeacherEntity teacher = teacherRepository.findByUid(teacherUid);
        if (teacher == null) {
            throw new ResourceNotFoundException("Teacher not found: " + teacherUid);
        }

        List<AttendanceEntity> attendanceList = new ArrayList<>();

        for (StudentEntity student : students) {
            AttendanceEntity attendance = new AttendanceEntity();
            attendance.setSubjectEntity(entity);
            attendance.setStudentEntity(student);
            attendance.setMarkedBy(teacher);
            attendance.setDate(request.getDate());
            attendance.setStatus(
                    recognizedRollSet.contains(student.getRollNo())
                            ? AttendanceStatus.PRESENT
                            : AttendanceStatus.ABSENT
            );
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

        // ✅ FIXED: was findByDateAndSubjectEntity_SubjectName (no uid filter — any teacher could see all records)
        // Now uid is passed → only rows where markedBy.uid = this teacher are returned
        List<AttendanceEntity> attendanceEntities = attendanceRepository
                .findByDateAndSubjectEntity_SubjectNameAndMarkedBy_Uid(dto.getDate(), dto.getSubject(), uid)
                .orElseThrow(() -> new ResourceNotFoundException("No attendance found for this subject or date"));

        return attendanceEntities;
    }

    @Override
    public List<StudentAttendanceDto> getAttendance_ForStudents_BatchWise(AttendanceDto dto, String uid) {

        TeacherEntity teacher = teacherRepository.findByUid(uid);
        if (teacher == null) {
            throw new ResourceNotFoundException("Teacher not found");
        }

        ClassEntity classEntity = classRepository.findByBatchCode(dto.getBatchCode()).orElseThrow(
                () -> new ResourceNotFoundException("Batch not found")
        );

        subjectRepository.findByCode(dto.getSubject()).orElseThrow(
                () -> new ResourceNotFoundException("Subject not found")
        );

        // ✅ FIXED: totalClasses is now the count of DISTINCT dates this teacher
        // conducted class for this specific subject + this specific batch.
        //
        // Old bug: subjectEntity.getTotalClasses() was a single global integer.
        // It was incremented once per upload regardless of teacher or batch,
        // so Teacher A + Teacher B both uploading = totalClasses 2 for everyone.
        // Also: it was 1 per upload but there are N students → ratio was
        // classesAttended(0 or 1) / 1 = always 0% or 100%.
        //
        // Now: if Teacher A takes Batch CS-A for subject MATH on 3 dates,
        // totalClasses = 3 for that teacher+batch combo only.
        // Teacher B taking the same subject for the same batch independently
        // gets their own count of 0 until they upload.
        long totalClassesForSubject = attendanceRepository
                .countDistinctDatesByTeacherAndSubjectAndBatch(uid, dto.getSubject(), dto.getBatchCode());

        List<StudentEntity> students = studentRepository.findByBatch(classEntity);
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No students found for this batch");
        }

        return students.stream()
                .map(student -> {
                    // ✅ FIXED: was countByStudentEntityAndSubjectEntity_CodeAndStatus (no uid filter)
                    // Now scoped to this teacher's uid → Teacher B cannot see Teacher A's present count
                    long classesAttended = attendanceRepository
                            .countByStudentEntityAndMarkedBy_UidAndSubjectEntity_CodeAndStatus(
                                    student,
                                    uid,
                                    dto.getSubject(),
                                    AttendanceStatus.PRESENT
                            );

                    double attendanceRatio = totalClassesForSubject == 0
                            ? 0.0
                            : (double) classesAttended / totalClassesForSubject;

                    return AttendanceMapper.toStudentAttendanceDto(student, attendanceRatio);
                })
                .collect(Collectors.toList());
    }
}