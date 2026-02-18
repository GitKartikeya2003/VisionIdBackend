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

}
