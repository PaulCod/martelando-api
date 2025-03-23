package com.martelando.martelandoapp.mapper;

import com.martelando.martelandoapp.dto.UserDTO;
import com.martelando.martelandoapp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserDTO userEntityToUserDTO(UserEntity userEntity);

    UserEntity userDTOToUserEntity(UserDTO userDTO);

}
