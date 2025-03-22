package com.martelando.martelandoapp.sevice.impl;

import com.martelando.martelandoapp.dto.UserDTO;
import com.martelando.martelandoapp.entity.UserEntity;
import com.martelando.martelandoapp.mapper.IUserMapper;
import com.martelando.martelandoapp.repository.IUserRepository;
import com.martelando.martelandoapp.sevice.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;


    @Override
    public UserDTO create(UserDTO userDTO) {
        UserEntity entity = userMapper.userDTOToUserEntity(userDTO);
        var userExists = this.userRepository.existsByEmailOrPhone(entity.getEmail(), entity.getPhone());

        if(userExists) {
            throw new IllegalArgumentException("Usuario já existe");
        }

        UserEntity savedUser = this.userRepository.save(entity);

        return userMapper.userEntityToUserDTO(savedUser);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        UserEntity entity = userMapper.userDTOToUserEntity(userDTO);

        var user = this.userRepository.findById(entity.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario não existe"));

        user.setName(entity.getName());
        user.setPassword(entity.getPassword());
        user.setPhone(entity.getPhone());

        var updatedUser = this.userRepository.save(user);
        return userMapper.userEntityToUserDTO(updatedUser);
    }

    @Override
    public void delete(Long id) {
        var user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não existe"));

        this.userRepository.delete(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        var user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não existe"));

        return userMapper.userEntityToUserDTO(user);
    }
}
