package com.example.VisionIdBackend.controller;


import com.example.VisionIdBackend.dto.ResponseDto;
import com.example.VisionIdBackend.dto.TeacherDto;
import com.example.VisionIdBackend.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TeacherController {

    @Autowired
    private ITeacherService service;

    @PostMapping("/registerTeacher")
    public ResponseEntity<ResponseDto> registerTeacher(@RequestBody TeacherDto dto) {

        service.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Faculty registered successfully"));

    }

}
