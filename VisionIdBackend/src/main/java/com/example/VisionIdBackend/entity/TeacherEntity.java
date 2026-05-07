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
public class TeacherEntity extends BaseEntity {


    @Column(unique = true, nullable = false)
    private String email;

    private String name;
   // private String role;

    @Column(unique = true, nullable = false)
    private String uid;    //Unique id

    private String password;

    @ManyToMany
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_uid"),
            inverseJoinColumns = @JoinColumn(name = "subject_code")
    )
    private List<SubjectEntity> subjects;
}
