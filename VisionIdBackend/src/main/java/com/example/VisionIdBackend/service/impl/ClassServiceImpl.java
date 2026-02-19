package com.example.VisionIdBackend.service.impl;


import com.example.VisionIdBackend.dto.ClassDto;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import com.example.VisionIdBackend.exception.ClassAlreadyExistsException;
import com.example.VisionIdBackend.exception.StudentAlreadyExistsException;
import com.example.VisionIdBackend.mapper.ClassMapper;
import com.example.VisionIdBackend.mapper.StudentMapper;
import com.example.VisionIdBackend.repository.ClassRepository;
import com.example.VisionIdBackend.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements IClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public void createClass(ClassDto classDto) {


        Optional<ClassEntity> dto = classRepository.findByBatchCode(classDto.getBatchCode());

        if(dto.isPresent()) {
            throw new ClassAlreadyExistsException("Class already exists");
        }
        ClassEntity classEntity = ClassMapper.toEntity(classDto);
        classRepository.save(classEntity);

    }

    @Override
    public List<ClassDto> getAllClasses() {

        List<ClassEntity> classEntities = (List<ClassEntity>) classRepository.findAll();

        List<ClassDto> classDtoList = new ArrayList<>();
        for (ClassEntity classEntity : classEntities) {
        ClassDto classDto = ClassMapper.toDto(classEntity);
        classDtoList.add(classDto);
        }
        return classDtoList;

    }
}
