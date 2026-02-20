package com.example.VisionIdBackend.service;


import com.example.VisionIdBackend.dto.StudentDto;
import com.example.VisionIdBackend.entity.StudentEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IStudentService {

    public void createStudent(StudentDto studentDto);


    public List<StudentDto> getAllStudents();

    public StudentDto getStudentById(String rollNo);

    public void deleteStudentByRoll(String rollNo);

     public void updateStudent(StudentDto studentDto);


}
