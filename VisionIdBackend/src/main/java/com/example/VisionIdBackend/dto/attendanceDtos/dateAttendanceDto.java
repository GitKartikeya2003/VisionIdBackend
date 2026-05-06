package com.example.VisionIdBackend.dto.attendanceDtos;

import lombok.*;

import java.time.LocalDate;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class dateAttendanceDto {

    private LocalDate date;
    private String subject;


}

//Sample
//{
//        "date": "2026-05-06",
//        "subject": "Compiler Design"
// }
