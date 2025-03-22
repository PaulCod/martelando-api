package com.martelando.martelandoapp.sevice;

import com.martelando.martelandoapp.dto.UserDTO;

public interface IUserService {
    UserDTO create(final UserDTO userDTO);

    UserDTO update(final UserDTO userDTO);

    void delete(final Long id);

    UserDTO findByEmail(final String email);
}
