package com.example.VisionIdBackend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceLogEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private AttendanceEntity attendance;

    private String oldStatus;
    private String newStatus;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity modifiedBy;
}

