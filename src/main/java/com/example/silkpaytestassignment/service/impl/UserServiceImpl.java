package com.example.silkpaytestassignment.service.impl;

import com.example.silkpaytestassignment.dto.UserCreateDto;
import com.example.silkpaytestassignment.entity.Role;
import com.example.silkpaytestassignment.mapper.UserCreateMapper;
import com.example.silkpaytestassignment.repository.UserRepository;
import com.example.silkpaytestassignment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void create(UserCreateDto user) {
        Optional.of(user)
                .map(UserCreateMapper.INSTANCE::toEntity)
                .map(entity -> {
                    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
                    entity.setRole(Role.USER);
                    return entity;
                })
                .map(userRepository::save)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found!"));
    }
}
