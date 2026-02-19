package com.example.VisionIdBackend.service;


import com.example.VisionIdBackend.dto.StudentDto;
import com.example.VisionIdBackend.entity.StudentEntity;

import java.util.List;

public interface IStudentService {

    void createStudent(StudentDto studentDto);


    public List<StudentDto> getAllStudents();

}
