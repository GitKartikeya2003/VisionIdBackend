package com.example.VisionIdBackend.repository;


import com.example.VisionIdBackend.entity.AttendanceEntity;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends CrudRepository<AttendanceEntity, Long> {

    Optional<AttendanceEntity> findByStudentEntityAndDate(StudentEntity student, LocalDate date);

    boolean existsByStudentEntity_BatchAndDate(ClassEntity batch, LocalDate date);

    boolean existsByDateAndSubjectEntity_SubjectName(
            LocalDate date,
            String subjectName
    );

    Optional<List<AttendanceEntity>> findByDateAndSubjectEntity_SubjectName(
            LocalDate date,
            String subjectName
    );


    @Query("""
                SELECT DISTINCT a.studentEntity
                FROM AttendanceEntity a
                WHERE a.markedBy.id = :teacherId
                AND a.subjectEntity.code = :subjectCode
                AND a.studentEntity.classEntity.batchCode = :batchCode
            """)
    Optional<List<StudentEntity>> findStudentsByTeacherAndSubjectAndBatch(
            @Param("teacherId") String teacherId,
            @Param("subjectCode") String subjectCode,
            @Param("batchCode") String batchCode
    );
}
