package com.example.VisionIdBackend.service.impl;

import com.example.VisionIdBackend.dto.TeacherDto;
import com.example.VisionIdBackend.entity.TeacherEntity;
import com.example.VisionIdBackend.exception.TeacherAlreadyExistsException;
import com.example.VisionIdBackend.mapper.TeacherMapper;
import com.example.VisionIdBackend.repository.TeacherRepository;
import com.example.VisionIdBackend.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TeacherServiceImpl  implements ITeacherService
{
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void register(TeacherDto teacherDto) {

        String uid = teacherDto.getUid();

        Optional<TeacherEntity> teacher = Optional.ofNullable(teacherRepository.findByUid(teacherDto.getUid()));

        if(teacher.isPresent()) {
            throw new TeacherAlreadyExistsException(teacherDto.getUid());
        }

        TeacherEntity teacherEntity = new TeacherEntity();
        TeacherMapper.toEntity(teacherEntity,teacherDto);

        teacherRepository.save(teacherEntity);
    }
}
