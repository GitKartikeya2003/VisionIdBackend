package com.example.VisionIdBackend.controller;


import com.example.VisionIdBackend.dto.SubjectDTO;
import com.example.VisionIdBackend.dto.attendanceDtos.AttendanceDto;
import com.example.VisionIdBackend.dto.attendanceDtos.StudentAttendanceDto;
import com.example.VisionIdBackend.dto.attendanceDtos.dateAttendanceDto;
import com.example.VisionIdBackend.entity.AttendanceEntity;
import com.example.VisionIdBackend.entity.SubjectEntity;
import com.example.VisionIdBackend.service.IAttendanceService;
import com.example.VisionIdBackend.service.IJwtService;
import com.example.VisionIdBackend.service.ISubjectService;
import com.example.VisionIdBackend.service.impl.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AttendanceController {


    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private IJwtService jwtService;


    @Autowired
    private IAttendanceService attendanceService;

    @GetMapping("/getall-subjects")
    public ResponseEntity<List<SubjectEntity>> getAllSubjects() {


        List<SubjectEntity> subjects = subjectService.getAllSubjects();
        return ResponseEntity.status(HttpStatus.OK).body(subjects);

    }

    @GetMapping("/get-attendance-for-date-and-subject/")
    public ResponseEntity<List<AttendanceEntity>> getAttendanceForDate(@RequestBody dateAttendanceDto dto
            , @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // removes "Bearer "
        String uid = jwtService.extractUid(token);

        List<AttendanceEntity> students = attendanceService.getAttendance_forDate_Subject(dto, uid);

        return ResponseEntity.status(HttpStatus.OK).body(students);

    }


    @GetMapping("/get-percentage-attendanceOfAllStudents/")
    public ResponseEntity<List<AttendanceEntity>> getPercentageAttendance(@RequestBody AttendanceDto dto
            , @RequestHeader("Authorization") String authHeader) {


        String token = authHeader.substring(7);
        String uid = jwtService.extractUid(token);

        List<StudentAttendanceDto> students =;


        return ResponseEntity.status(HttpStatus.OK).body();
    }


}
