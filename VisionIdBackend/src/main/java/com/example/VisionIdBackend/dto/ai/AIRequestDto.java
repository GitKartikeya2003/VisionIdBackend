package com.example.VisionIdBackend.dto.ai;


import jakarta.persistence.Column;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Data
public class AIRequestDto {


    @Column(nullable = false)
    private String batchCode; //23A11

    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    private List<String> recognizedStudents;

}


/*
{
  "batchCode": 23A11,
  "date": "2026-02-23",
  "recognizedStudents": [
    "231030286",
    "-----596",
    "-----111"
  ]
}
*/