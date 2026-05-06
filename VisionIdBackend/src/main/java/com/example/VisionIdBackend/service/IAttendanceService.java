package com.example.VisionIdBackend.service;

import com.example.VisionIdBackend.dto.ai.AIRequestDto;
import com.example.VisionIdBackend.dto.attendanceDtos.dateAttendanceDto;
import com.example.VisionIdBackend.entity.AttendanceEntity;
import com.example.VisionIdBackend.entity.StudentEntity;

import java.util.List;

public interface IAttendanceService {


    List<StudentEntity> processAIAttendance(AIRequestDto request, String teacherEmail);

    List<AttendanceEntity> getAttendance_forDate_Subject(dateAttendanceDto dto,String uid);

}
