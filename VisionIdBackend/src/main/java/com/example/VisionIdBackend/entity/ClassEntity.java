package com.example.VisionIdBackend.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class ClassEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String batchCode;

    @OneToMany(mappedBy = "batch")
    @JsonManagedReference
    private List<StudentEntity> students;


}
