package com.example.silkpaytestassignment.mapper;

import com.example.silkpaytestassignment.dto.UserCreateDto;
import com.example.silkpaytestassignment.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {

    UserCreateMapper INSTANCE = Mappers.getMapper(UserCreateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserCreateDto dto);
}
