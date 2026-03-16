package com.example.VisionIdBackend.repository;

import com.example.VisionIdBackend.entity.ClassEntity;
import com.example.VisionIdBackend.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClassRepository extends CrudRepository<ClassEntity, Long> {

    Optional<ClassEntity> findByBatchCode(String batchCode);
}
