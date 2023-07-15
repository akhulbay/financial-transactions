package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.WalletBalanceReadDto;
import com.example.silkpaytestassignment.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletBalanceReadMapper {

    WalletBalanceReadMapper INSTANCE = Mappers.getMapper(WalletBalanceReadMapper.class);

    WalletBalanceReadDto toDto(Wallet entity);
}
