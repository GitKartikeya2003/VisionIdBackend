package com.example.VisionIdBackend.dto.attendanceDtos;


import com.example.VisionIdBackend.entity.StudentEntity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDto extends StudentEntity {

    String subject;
    String batchCode;



}
