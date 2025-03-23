package com.martelando.martelandoapp.controllers;

import com.martelando.martelandoapp.controllers.request.FindUserByEmailRequest;
import com.martelando.martelandoapp.controllers.request.SaveUserRequest;
import com.martelando.martelandoapp.controllers.request.UpdateUserRequest;
import com.martelando.martelandoapp.controllers.responses.UserDetailResponse;
import com.martelando.martelandoapp.sevice.IUserService;
import jakarta.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @PostMapping()
    ResponseEntity<UserDetailResponse> create(@RequestBody SaveUserRequest saveUserRequest) {
        var user = this.userService.create(saveUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping()
    ResponseEntity<Void> delete() {
        long id = 1;
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    ResponseEntity<UserDetailResponse> update(@RequestBody UpdateUserRequest updateUserRequest) {
        long id = 1;
        var user = this.userService.update(id, updateUserRequest);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping()
    ResponseEntity<UserDetailResponse> findByEmail(@RequestBody FindUserByEmailRequest findUserByEmailRequest) {
        var user = this.userService.findByEmail(findUserByEmailRequest.email());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
