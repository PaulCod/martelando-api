package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martelando.martelandoapp.enums.OfferStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateOfferRequest(
        @NotNull
        @JsonProperty("id")
        Long id,

        @NotNull
        @JsonProperty("status")
        OfferStatus status,

        @NotNull
        @JsonProperty("amount")
        BigDecimal amount
) {}
