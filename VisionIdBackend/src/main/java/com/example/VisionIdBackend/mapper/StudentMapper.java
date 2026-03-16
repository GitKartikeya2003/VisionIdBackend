package com.example.VisionIdBackend.mapper;

import com.example.VisionIdBackend.dto.StudentDto;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;

public class StudentMapper {


    public static StudentEntity mapToStudentEntity(StudentDto studentDto) {
        StudentEntity student = new StudentEntity();
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setRollNo(studentDto.getRollNo());
        return student;
    }


    // Entity → DTO
    public static StudentDto toDto(StudentEntity student) {
        if (student == null) return null;

        StudentDto dto = new StudentDto();
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setRollNo(student.getRollNo());

        if (student.getBatch() != null) {
            dto.setBatchCode(student.getBatch().getBatchCode());
        }

        return dto;
    }

}
