package com.example.VisionIdBackend.repository;


import com.example.VisionIdBackend.entity.AttendanceEntity;
import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import com.example.VisionIdBackend.entity.enums.AttendanceStatus;
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

    boolean existsByStudentEntity_BatchAndDateAndSubjectEntity_Code(ClassEntity batch, LocalDate date, String subjectCode);

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
                WHERE a.markedBy.uid = :teacherId
                AND a.subjectEntity.code = :subjectCode
                AND a.studentEntity.batch.batchCode = :batchCode
            """)
    Optional<List<StudentEntity>> findStudentsByTeacherAndSubjectAndBatch(
            @Param("teacherId") String teacherId,
            @Param("subjectCode") String subjectCode,
            @Param("batchCode") String batchCode
    );

    long countByStudentEntityAndMarkedBy_UidAndSubjectEntity_Code(
            StudentEntity studentEntity,
            String teacherUid,
            String subjectCode
    );

    long countByStudentEntityAndMarkedBy_UidAndSubjectEntity_CodeAndStatus(
            StudentEntity studentEntity,
            String teacherUid,
            String subjectCode,
            AttendanceStatus status
    );

    long countByStudentEntityAndSubjectEntity_Code(
            StudentEntity studentEntity,
            String subjectCode
    );

    long countByStudentEntityAndSubjectEntity_CodeAndStatus(
            StudentEntity studentEntity,
            String subjectCode,
            AttendanceStatus status
    );
}
