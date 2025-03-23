package com.martelando.martelandoapp.controllers;

import com.martelando.martelandoapp.controllers.request.AuthenticationRequest;
import com.martelando.martelandoapp.controllers.responses.AuthenticationResponse;
import com.martelando.martelandoapp.sevice.IPasswordEncoderService;
import com.martelando.martelandoapp.sevice.IUserService;
import com.martelando.martelandoapp.sevice.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthController {
    private final IUserService userService;
    private final IPasswordEncoderService passwordEncoderService;
    private final JwtService jwtService;

    @PostMapping()
    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var user = this.userService.findByEmailWithPassword(authenticationRequest.email());

        if(!passwordEncoderService.matches(authenticationRequest.password(), user.getPassword())){
            return ResponseEntity.status(401).build();
        }

        String token = jwtService.generateToken(user.getId());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
