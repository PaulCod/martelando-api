package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record SaveUserRequest (
        @NotNull(message = "O nome não pode ser nulo.")
        @Size(max = 150, message = "O nome não pode ter mais de 150 caracteres.")
        @JsonProperty("name")
        String name,

        @NotNull(message = "O e-mail não pode ser nulo.")
        @Email(message = "O e-mail fornecido é inválido.")
        @Size(max = 150, message = "O e-mail não pode ter mais de 150 caracteres.")
        @JsonProperty("email")
        String email,

        @NotNull(message = "O telefone não pode ser nulo.")
        @Size(min = 11, max = 11, message = "O telefone deve ter exatamente 11 caracteres.")
        @JsonProperty("phone")
        String phone,

        @NotNull(message = "A senha não pode ser nula.")
        @Size(max = 100, message = "A senha não pode ter mais de 100 caracteres.")
        @JsonProperty("password")
        String password
) {}


