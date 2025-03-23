package com.martelando.martelandoapp.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponse(
        @JsonProperty("token")
        String token
){}
