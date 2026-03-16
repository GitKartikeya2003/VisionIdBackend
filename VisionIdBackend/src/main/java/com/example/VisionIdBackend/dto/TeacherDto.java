package com.example.VisionIdBackend.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {

    private String uid;
    private String email;
    private String password;
}
