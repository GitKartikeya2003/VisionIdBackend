package com.example.VisionIdBackend.service;

import com.example.VisionIdBackend.dto.TeacherDto;
import com.example.VisionIdBackend.dto.loginDtos.LoginRequestDto;

public interface ITeacherService {

    public void register(TeacherDto teacherDto);

    public String login(LoginRequestDto dto);
}
