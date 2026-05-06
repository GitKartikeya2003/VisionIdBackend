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

    boolean existsByDateAndSubjectEntity_SubjectName(LocalDate date, String subjectName);

    // ✅ FIXED: added MarkedBy_Uid so only the calling teacher's records are returned
    Optional<List<AttendanceEntity>> findByDateAndSubjectEntity_SubjectNameAndMarkedBy_Uid(
            LocalDate date,
            String subjectName,
            String teacherUid
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

    // ✅ NEW: counts how many distinct dates THIS teacher took class
    // for THIS subject in THIS batch specifically.
    // This gives the correct per-teacher, per-batch total class count
    // as the denominator for attendance percentage.
    @Query("""
                SELECT COUNT(DISTINCT a.date)
                FROM AttendanceEntity a
                WHERE a.markedBy.uid = :teacherUid
                AND a.subjectEntity.code = :subjectCode
                AND a.studentEntity.batch.batchCode = :batchCode
            """)
    long countDistinctDatesByTeacherAndSubjectAndBatch(
            @Param("teacherUid") String teacherUid,
            @Param("subjectCode") String subjectCode,
            @Param("batchCode") String batchCode
    );

    long countByStudentEntityAndMarkedBy_UidAndSubjectEntity_Code(
            StudentEntity studentEntity,
            String teacherUid,
            String subjectCode
    );

    // ✅ This one was already correct — uid scoped
    long countByStudentEntityAndMarkedBy_UidAndSubjectEntity_CodeAndStatus(
            StudentEntity studentEntity,
            String teacherUid,
            String subjectCode,
            AttendanceStatus status
    );

    // These two below have no uid filter — kept for other uses but
    // NOT used in percentage calculation anymore
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