package com.example.silkpaytestassignment.service.impl;

import com.example.silkpaytestassignment.dto.AccountCreateDto;
import com.example.silkpaytestassignment.dto.AccountReadDto;
import com.example.silkpaytestassignment.entity.Account;
import com.example.silkpaytestassignment.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    private static final Long ACCOUNT_ID = 1L;
    private static final String ACCOUNT_FIRST_NAME = "Shyngys";
    private static final String ACCOUNT_LAST_NAME = "Akhulbay";
    private static final String ACCOUNT_EMAIL = "akhulbai8@gmail.com";
    private static final String ACCOUNT_PHONE_NUMBER = "87472877882";

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkFindById() {
        Account account = new Account();
        account.setId(ACCOUNT_ID);
        account.setFirstName(ACCOUNT_FIRST_NAME);
        account.setLastName(ACCOUNT_LAST_NAME);
        account.setEmail(ACCOUNT_EMAIL);
        account.setPhoneNumber(ACCOUNT_PHONE_NUMBER);

        when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(account));

        AccountReadDto result = accountService.findById(ACCOUNT_ID);

        assertNotNull(result);
        assertEquals(ACCOUNT_ID, result.getId());
        assertEquals(ACCOUNT_FIRST_NAME, result.getFirstName());
        assertEquals(ACCOUNT_LAST_NAME, result.getLastName());
        assertEquals(ACCOUNT_EMAIL, result.getEmail());
        assertEquals(ACCOUNT_PHONE_NUMBER, result.getPhoneNumber());
    }


    @Test
    void checkCreate() {
        AccountCreateDto accountCreateDto = new AccountCreateDto();
        accountCreateDto.setFirstName(ACCOUNT_FIRST_NAME);
        accountCreateDto.setLastName(ACCOUNT_LAST_NAME);
        accountCreateDto.setEmail(ACCOUNT_EMAIL);
        accountCreateDto.setPhoneNumber(ACCOUNT_PHONE_NUMBER);

        Account account = new Account();
        account.setId(ACCOUNT_ID);
        account.setFirstName(ACCOUNT_FIRST_NAME);
        account.setLastName(ACCOUNT_LAST_NAME);
        account.setEmail(ACCOUNT_EMAIL);
        account.setPhoneNumber(ACCOUNT_PHONE_NUMBER);
        account.setCreationDate(LocalDate.now());

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountReadDto result = accountService.create(accountCreateDto);

        assertNotNull(result);
        assertEquals(account.getId(), result.getId());
        assertEquals(account.getFirstName(), result.getFirstName());
        assertEquals(account.getLastName(), result.getLastName());
        assertEquals(account.getEmail(), result.getEmail());
        assertEquals(account.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(account.getCreationDate(), result.getCreationDate());
    }

}