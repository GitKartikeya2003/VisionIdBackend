package com.example.VisionIdBackend.service;

import com.example.VisionIdBackend.dto.ClassDto;

import java.util.List;

public interface IClassService {

    public void createClass(ClassDto classDto);

    public List<ClassDto> getAllClasses();
}
