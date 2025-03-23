package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FindUserByEmailRequest (
    @JsonProperty("email")
    String email
) {}
