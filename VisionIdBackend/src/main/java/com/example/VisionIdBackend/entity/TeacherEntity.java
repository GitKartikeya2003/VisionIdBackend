package com.example.VisionIdBackend.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity extends BaseEntity {


    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private String role;

    @Column(unique = true, nullable = false)
    private String uid;    //Unique id

    private String password;
}
