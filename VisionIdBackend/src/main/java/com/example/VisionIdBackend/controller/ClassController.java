package com.example.VisionIdBackend.controller;


import com.example.VisionIdBackend.dto.ClassDto;
import com.example.VisionIdBackend.dto.ResponseDto;
import com.example.VisionIdBackend.dto.StudentDto;
import com.example.VisionIdBackend.repository.ClassRepository;
import com.example.VisionIdBackend.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClassController {

    @Autowired
    private IClassService classService;

    @PostMapping("/createClass")
    private ResponseEntity<ResponseDto> createClass(@RequestBody ClassDto classDto){

        classService.createClass(classDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Class Created Successfully"));
    }


    @GetMapping("/fetchAllClasses")
    private ResponseEntity<List<ClassDto>> getAllClasses(){

        List<ClassDto> classDtoList = classService.getAllClasses();

            return ResponseEntity.status(HttpStatus.OK).body(classDtoList);
    }


    //Heart of VisionId
    @GetMapping("/getStudentsByClass")
    private ResponseEntity<List<StudentDto>> getStudentsByClass(@RequestParam String batchcode){

        List<StudentDto> students;

        return ResponseEntity.status(HttpStatus.OK).body()

    }

    //Example AI response:
    //
    //{
    //  "classId": 1,
    //  "recognizedStudents": ["23A14", "23A18"]
    //}
    //
    //
    //Now your backend must:
    //
    //Fetch ALL students of class 1 ← THIS API logic
    //
    //Compare with recognized list
    //
    //Mark:
    //
    //Recognized → PRESENT
    //
    //Not recognized → ABSENT
    //
    //Without this API logic, your attendance system literally cannot work.


}
