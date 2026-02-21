package com.example.VisionIdBackend.repository;


import com.example.VisionIdBackend.entity.StudentEntity;
import com.example.VisionIdBackend.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,Long>{

    TeacherEntity findByUid(String username);
}
