package com.example.silkpaytestassignment.controller;

import com.example.silkpaytestassignment.dto.UserCreateDto;
import com.example.silkpaytestassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(UserCreateDto user) {
        userService.create(user);
    }
}
