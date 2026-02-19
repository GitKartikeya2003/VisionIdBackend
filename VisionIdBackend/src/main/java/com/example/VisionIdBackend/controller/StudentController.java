package com.example.VisionIdBackend.controller;

import com.example.VisionIdBackend.dto.ResponseDto;
import com.example.VisionIdBackend.dto.StudentDto;
import com.example.VisionIdBackend.entity.StudentEntity;
import com.example.VisionIdBackend.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<ResponseDto> createStudent(@RequestBody StudentDto studentDto) {

        studentService.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Student Created Successfully"));
    }


    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentDto>> getAllStudents() {

        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());

    }


    @GetMapping("/fetchStudent")
    public ResponseEntity<StudentDto> fetchStudent(@RequestParam String rollNo) {


        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(rollNo));
    }


}
