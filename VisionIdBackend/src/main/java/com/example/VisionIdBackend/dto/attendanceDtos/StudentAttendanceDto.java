package com.example.VisionIdBackend.dto.attendanceDtos;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendanceDto {


    private String name;
    private String rollNo;
    private double attendance_percentage;

}
