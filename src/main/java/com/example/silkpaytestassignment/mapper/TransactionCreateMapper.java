package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.TransactionCreateDto;
import com.example.silkpaytestassignment.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionCreateMapper {

    TransactionCreateMapper INSTANCE = Mappers.getMapper(TransactionCreateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "senderWallet", ignore = true)
    @Mapping(target = "recipientWallet", ignore = true)
    Transaction toEntity(TransactionCreateDto dto);
}
