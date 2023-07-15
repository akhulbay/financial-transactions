package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.TransactionReadDto;
import com.example.silkpaytestassignment.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionReadMapper {

    TransactionReadMapper INSTANCE = Mappers.getMapper(TransactionReadMapper.class);

    TransactionReadDto toDto(Transaction entity);
}
