package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationRequest(
        @JsonProperty("email")
        String email,
        @JsonProperty("password")
        String password
) {
}
