    package com.martelando.martelandoapp.controllers;

    import com.martelando.martelandoapp.controllers.request.SaveUserRequest;
    import com.martelando.martelandoapp.controllers.responses.UserDetailResponse;
    import com.martelando.martelandoapp.sevice.IUserService;
    import lombok.AllArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("users")
    @AllArgsConstructor
    public class UserController {

        private IUserService userService;

        ResponseEntity<UserDetailResponse> create(final SaveUserRequest saveUserRequest) {
            var user = this.userService.create(saveUserRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }
