package com.example.VisionIdBackend.entity;


import com.example.VisionIdBackend.repository.TeacherRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class SubjectEntity {

    @Id
    @Column(unique = true, nullable = false)
    private String code;

    private String subjectName;


}
