package com.example.silkpaytestassignment.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
