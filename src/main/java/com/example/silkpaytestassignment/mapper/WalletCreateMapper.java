package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.WalletCreateDto;
import com.example.silkpaytestassignment.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WalletCreateMapper {

    WalletCreateMapper INSTANCE = Mappers.getMapper(WalletCreateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "account", ignore = true)
    Wallet toEntity(WalletCreateDto dto);
}
