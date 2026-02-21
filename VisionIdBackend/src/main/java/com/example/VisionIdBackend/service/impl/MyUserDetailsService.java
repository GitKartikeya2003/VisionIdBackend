package com.example.VisionIdBackend.service.impl;


import com.example.VisionIdBackend.entity.TeacherEntity;
import com.example.VisionIdBackend.entity.principle.TeacherPrincipal;
import com.example.VisionIdBackend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TeacherEntity user = repo.findByUid(username);
        if (user == null) {
            throw new UsernameNotFoundException("User 404");

        }

        return new TeacherPrincipal(user);
    }
}
