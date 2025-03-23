package com.martelando.martelandoapp.mapper;

import com.martelando.martelandoapp.controllers.request.SaveUserRequest;
import com.martelando.martelandoapp.controllers.request.UpdateUserRequest;
import com.martelando.martelandoapp.controllers.responses.UserDetailResponse;
import com.martelando.martelandoapp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    UserEntity toEntity(final SaveUserRequest saveUserRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "products", ignore = true)
    UserEntity toEntity(final Long id, final UpdateUserRequest updateUserRequest);

    @Mapping(target = "password", ignore = true)
    UserDetailResponse toResponse(final UserEntity userEntity);
}
