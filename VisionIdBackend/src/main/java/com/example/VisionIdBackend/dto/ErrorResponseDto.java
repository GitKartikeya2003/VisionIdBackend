package com.example.VisionIdBackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private HttpStatusCode errorCode;
    private String errorMsg;
    private LocalDateTime errorTime;
}
