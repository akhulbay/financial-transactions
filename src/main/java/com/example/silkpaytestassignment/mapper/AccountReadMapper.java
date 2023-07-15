package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.AccountReadDto;
import com.example.silkpaytestassignment.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountReadMapper {

    AccountReadMapper INSTANCE = Mappers.getMapper(AccountReadMapper.class);

    AccountReadDto toDto(Account entity);
}
