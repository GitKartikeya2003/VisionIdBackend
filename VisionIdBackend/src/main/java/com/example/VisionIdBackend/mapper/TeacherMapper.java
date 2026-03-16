package com.example.VisionIdBackend.mapper;

import com.example.VisionIdBackend.dto.TeacherDto;
import com.example.VisionIdBackend.entity.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TeacherMapper {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static TeacherEntity toEntity(TeacherEntity teacherEntity,TeacherDto dto) {

        teacherEntity.setEmail(dto.getEmail());
        teacherEntity.setUid(dto.getUid());
        teacherEntity.setPassword(encoder.encode(dto.getPassword()));
        return teacherEntity;
    }
}
