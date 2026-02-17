package com.example.VisionIdBackend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity extends BaseEntity {


    private String name;


    @Column(unique = true, nullable = false)
    private String EnrollmentNo;  //roll

    private String email;

    private String faceId;

//    @OneToMany(mappedBy = "classEntity")
//    private List<StudentEntity> students;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;







}
