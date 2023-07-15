package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.AccountCreateDto;
import com.example.silkpaytestassignment.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountCreateMapper {

    AccountCreateMapper INSTANCE = Mappers.getMapper(AccountCreateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Account toEntity(AccountCreateDto dto);
}
