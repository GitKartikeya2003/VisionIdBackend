package com.example.VisionIdBackend.mapper;

import com.example.VisionIdBackend.dto.ClassDto;
import com.example.VisionIdBackend.dto.attendanceDtos.AttendanceDto;
import com.example.VisionIdBackend.dto.attendanceDtos.StudentAttendanceDto;
import com.example.VisionIdBackend.entity.AttendanceEntity;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import java.util.ArrayList;
import java.util.List;

public class AttendanceMapper {

    public static StudentAttendanceDto toStudentAttendanceDto(StudentEntity student,double percentage) {

        StudentAttendanceDto attendanceDto = new StudentAttendanceDto();

        attendanceDto.setName(student.getName());
        attendanceDto.setRollNo(student.getRollNo());
        attendanceDto.setAttendance_percentage(percentage);

        return attendanceDto;
    }



}
