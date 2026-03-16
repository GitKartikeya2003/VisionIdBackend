package com.example.VisionIdBackend.entity;

import com.example.VisionIdBackend.entity.BaseEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import com.example.VisionIdBackend.entity.TeacherEntity;
import com.example.VisionIdBackend.entity.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"student_id", "date"})
        },
        indexes = {
                @Index(name = "idx_attendance_date", columnList = "date")
        }
)
public class AttendanceEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity studentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private TeacherEntity markedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    @Column(nullable = false)
    private LocalDate date;
}


//What is FetchType.LAZY?
//
//When you have:
//@ManyToOne(fetch = FetchType.LAZY)
//private StudentEntity studentEntity;
//It means:
//🔹 “Do NOT load the Student from database immediately.”
//🔹 “Load it ONLY when I actually use it.”
// This prevents N+1 query problem