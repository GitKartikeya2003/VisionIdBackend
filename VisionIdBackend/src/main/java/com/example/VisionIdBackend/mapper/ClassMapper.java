package com.example.VisionIdBackend.mapper;

import com.example.VisionIdBackend.dto.ClassDto;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class ClassMapper {


    public static ClassEntity toEntity(ClassDto dto) {

        ClassEntity entity = new ClassEntity();
        entity.setBatchCode(dto.getBatchCode());

        List<StudentEntity> studentEntities =new ArrayList<>();

        entity.setStudents(studentEntities);

        return entity;
    }


    public static ClassDto toDto(ClassEntity entity) {
        ClassDto dto = new ClassDto();
        dto.setBatchCode(entity.getBatchCode());
        return dto;
    }
}
