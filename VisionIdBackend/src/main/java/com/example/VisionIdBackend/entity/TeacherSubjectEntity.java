package com.example.VisionIdBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "teacher_subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(TeacherSubjectEntity.TeacherSubjectId.class)
public class TeacherSubjectEntity {

    @Id
    @Column(name = "teacher_uid")
    private Long teacherUid;

    @Id
    @Column(name = "subject_code")
    private String subjectCode;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeacherSubjectId implements Serializable {
        private Long teacherUid;
        private String subjectCode;
    }
}