package com.example.VisionIdBackend.service;

import com.example.VisionIdBackend.dto.ClassDto;
import com.example.VisionIdBackend.dto.StudentDto;
import com.example.VisionIdBackend.entity.StudentEntity;

import java.util.List;

public interface IClassService {

    public void createClass(ClassDto classDto);

    public List<ClassDto> getAllClasses();

    public List<StudentDto> fetchAllStudentsByClass(ClassDto classDto);
}
