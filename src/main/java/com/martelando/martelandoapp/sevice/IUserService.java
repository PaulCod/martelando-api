package com.martelando.martelandoapp.sevice;

import com.martelando.martelandoapp.controllers.request.SaveUserRequest;
import com.martelando.martelandoapp.controllers.request.UpdateUserRequest;
import com.martelando.martelandoapp.controllers.responses.UserDetailResponse;

public interface IUserService {
    UserDetailResponse create(final SaveUserRequest saveUserRequest);

    UserDetailResponse update(final Long id,final UpdateUserRequest updateUserRequest);

    void delete(final Long id);

    UserDetailResponse findByEmail(final String email);
}
