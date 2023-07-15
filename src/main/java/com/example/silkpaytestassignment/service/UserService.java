package com.example.silkpaytestassignment.service;

import com.example.silkpaytestassignment.dto.UserCreateDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void create(UserCreateDto user);
}
