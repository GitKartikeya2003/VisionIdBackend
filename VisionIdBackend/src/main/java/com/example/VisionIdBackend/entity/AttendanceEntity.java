package com.example.VisionIdBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.core.ObjectReadContext;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity markedBy;
    private String status; // PRESENT / ABSENT

    private LocalDate date;

    private LocalTime time;



}
