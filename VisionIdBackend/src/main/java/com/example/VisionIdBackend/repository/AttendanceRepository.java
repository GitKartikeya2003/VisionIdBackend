package com.example.VisionIdBackend.repository;


import com.example.VisionIdBackend.entity.AttendanceEntity;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends CrudRepository<AttendanceEntity, Long> {

    Optional<AttendanceEntity> findByStudentEntityAndDate(StudentEntity student, LocalDate date);

    boolean existsByStudentEntity_BatchAndDate(ClassEntity batch, LocalDate date);

}
