package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
        @NotNull
        @Size(max = 150, message = "O nome não pode ter mais de 150 caracteres")
        @JsonProperty("name")
        String name,

        @NotNull
        @Size(max = 100, message = "A senha não pode ter mais de 100 caracteres")
        @JsonProperty("password")
        String password,

        @NotNull
        @Size(min = 11, max = 11, message = "O telefone deve ter 11 caracteres")
        @JsonProperty("phone")
        String phone
) {}