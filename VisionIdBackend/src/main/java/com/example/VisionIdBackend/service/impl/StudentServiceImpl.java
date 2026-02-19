package com.example.VisionIdBackend.service.impl;

import com.example.VisionIdBackend.dto.StudentDto;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import com.example.VisionIdBackend.exception.ResourceNotFoundException;
import com.example.VisionIdBackend.exception.StudentAlreadyExistsException;
import com.example.VisionIdBackend.mapper.StudentMapper;
import com.example.VisionIdBackend.repository.ClassRepository;
import com.example.VisionIdBackend.repository.StudentRepository;
import com.example.VisionIdBackend.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;


    @Override
    public void createStudent(StudentDto studentDto) {

        StudentEntity studentEntity = StudentMapper.mapToStudentEntity(studentDto);

        Optional<StudentEntity> studentEntityOptional = studentRepository.findByRollNo(studentEntity.getRollNo());

        if (studentEntityOptional.isPresent()) {
            throw new StudentAlreadyExistsException(studentEntity.getRollNo());
        }

        ClassEntity batch = classRepository
                .findByBatchCode(studentDto.getBatchCode())
                .orElseThrow(() -> new RuntimeException("Batch not found"));


        studentEntity.setBatch(batch);

        studentRepository.save(studentEntity);

    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();

        List<StudentDto> DtoList = new ArrayList<>();

        for (StudentEntity student : students) {

            DtoList.add(StudentMapper.toDto(student));
        }

        return DtoList;
    }

    @Override
    public StudentDto getStudentById(String rollNo) {
        StudentDto studentDto = new StudentDto();
//        Optional<StudentEntity> s = studentRepository.findByRollNo(rollNo);


        StudentEntity student = studentRepository.findByRollNo(rollNo).orElseThrow(() -> new RuntimeException("Student not found"));
        studentDto = StudentMapper.toDto(student);
        return studentDto;
    }


}
