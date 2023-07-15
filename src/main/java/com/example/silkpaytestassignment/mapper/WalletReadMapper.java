package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.WalletReadDto;
import com.example.silkpaytestassignment.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletReadMapper {

    WalletReadMapper INSTANCE = Mappers.getMapper(WalletReadMapper.class);

    WalletReadDto toDto(Wallet entity);
}
