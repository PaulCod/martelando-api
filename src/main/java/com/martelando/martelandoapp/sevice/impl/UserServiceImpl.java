package com.martelando.martelandoapp.sevice.impl;

import com.martelando.martelandoapp.controllers.request.SaveUserRequest;
import com.martelando.martelandoapp.controllers.request.UpdateUserRequest;
import com.martelando.martelandoapp.controllers.responses.UserDetailResponse;
import com.martelando.martelandoapp.entity.UserEntity;
import com.martelando.martelandoapp.exception.NotFoundException;
import com.martelando.martelandoapp.mapper.IUserMapper;
import com.martelando.martelandoapp.repository.IUserRepository;
import com.martelando.martelandoapp.sevice.IPasswordEncoderService;
import com.martelando.martelandoapp.sevice.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IPasswordEncoderService passwordEncoderService;


    @Override
    public UserDetailResponse create(SaveUserRequest saveUserRequest) {
        UserEntity entity = userMapper.toEntity(saveUserRequest);
        var userExists = this.userRepository.existsByEmailOrPhone(entity.getEmail(), entity.getPhone());

        if(userExists) {
            throw new NotFoundException("Usuario já existe");
        }

        entity.setPassword(passwordEncoderService.encode(entity.getPassword()));
        UserEntity savedUser = this.userRepository.save(entity);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserDetailResponse update(Long id, UpdateUserRequest updateUserRequest) {
        UserEntity entity = userMapper.toEntity(id, updateUserRequest);

        var user = this.userRepository.findById(entity.getId())
                .orElseThrow(() -> new NotFoundException("Usuario não existe"));

        if(!passwordEncoderService.matches(entity.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoderService.encode(entity.getPassword()));
        }

        user.setName(entity.getName());
        user.setPhone(entity.getPhone());

        var updatedUser = this.userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void delete(Long id) {
        var user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não existe"));

        this.userRepository.delete(user);
    }

    @Override
    public UserDetailResponse findByEmail(String email) {
        var user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario não existe"));

        return userMapper.toResponse(user);
    }

    @Override
    public UserEntity findByEmailWithPassword(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario não existe"));
    }

    @Override
    public UserDetailResponse findByUserId(Long id) {
        var user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario não existe"));

        return userMapper.toResponse(user);
    }

}
