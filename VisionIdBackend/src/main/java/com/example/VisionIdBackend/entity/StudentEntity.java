package com.example.VisionIdBackend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @Column(unique = true, nullable = false,updatable = false)
    private String rollNo;  //roll

    private String email;

    private String faceId;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private ClassEntity batch;

}
