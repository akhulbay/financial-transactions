package com.example.silkpaytestassignment.integration;

import com.example.silkpaytestassignment.dto.AccountCreateDto;
import com.example.silkpaytestassignment.dto.AccountReadDto;
import com.example.silkpaytestassignment.entity.Account;
import com.example.silkpaytestassignment.repository.AccountRepository;
import com.example.silkpaytestassignment.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountServiceImplIT {

    private static final String FIRST_NAME = "Shyngys";
    private static final String LAST_NAME = "Akhulbay";
    private static final String EMAIL = "akhulbai8@gmail.com";
    private static final String PHONE_NUMBER = "87472877882";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    void checkFindById() {
        Account account = Account.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .creationDate(LocalDate.now())
                .build();

        Account expectedResult = accountRepository.save(account);

        AccountReadDto actualResult = accountService.findById(expectedResult.getId());

        assertNotNull(actualResult);
        assertEquals(expectedResult.getFirstName(), actualResult.getFirstName());
        assertEquals(expectedResult.getLastName(), actualResult.getLastName());
        assertEquals(expectedResult.getEmail(), actualResult.getEmail());
        assertEquals(expectedResult.getPhoneNumber(), actualResult.getPhoneNumber());
        assertEquals(expectedResult.getCreationDate(), actualResult.getCreationDate());
        accountRepository.deleteAll();
    }

    @Test
    void checkCreate() {
        AccountCreateDto dto = new AccountCreateDto();
        dto.setFirstName(FIRST_NAME);
        dto.setLastName(LAST_NAME);
        dto.setEmail(EMAIL);
        dto.setPhoneNumber(PHONE_NUMBER);

        AccountReadDto actualResult = accountService.create(dto);

        assertNotNull(actualResult);
        assertNotNull(actualResult.getId());
        assertNotNull(actualResult.getCreationDate());
        assertEquals(dto.getFirstName(), actualResult.getFirstName());
        assertEquals(dto.getLastName(), actualResult.getLastName());
        assertEquals(dto.getEmail(), actualResult.getEmail());
        assertEquals(dto.getPhoneNumber(), actualResult.getPhoneNumber());
        accountRepository.deleteAll();
    }


}
