package com.martelando.martelandoapp.controllers;

import com.martelando.martelandoapp.controllers.request.AuthenticationRequest;
import com.martelando.martelandoapp.controllers.request.SaveUserRequest;
import com.martelando.martelandoapp.controllers.request.UpdateUserRequest;
import com.martelando.martelandoapp.controllers.responses.AuthenticationResponse;
import com.martelando.martelandoapp.controllers.responses.UserDetailResponse;
import com.martelando.martelandoapp.sevice.IPasswordEncoderService;
import com.martelando.martelandoapp.sevice.IUserService;
import com.martelando.martelandoapp.sevice.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final IPasswordEncoderService passwordEncoderService;
    private final JwtService jwtService;

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login( @Valid @RequestBody AuthenticationRequest authenticationRequest) {
        var user = this.userService.findByEmailWithPassword(authenticationRequest.email());

        if(!passwordEncoderService.matches(authenticationRequest.password(), user.getPassword())){
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.generateToken(user.getId());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailResponse> register(@RequestBody SaveUserRequest saveUserRequest) {
        UserDetailResponse userDetailResponse = userService.create(saveUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDetailResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                                     @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        Long userId = jwtService.extractUserIdFromToken(authorizationHeader);
        UserDetailResponse userDetailResponse = userService.update(userId, updateUserRequest);
        return ResponseEntity.ok(userDetailResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader) {
        Long userId = jwtService.extractUserIdFromToken(authorizationHeader);
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<UserDetailResponse> getUserById(@RequestHeader("Authorization") String authorizationHeader) {
        Long userId = jwtService.extractUserIdFromToken(authorizationHeader);
        UserDetailResponse userDetailResponse = userService.findByUserId(userId);
        return ResponseEntity.ok(userDetailResponse);
    }
}
