package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martelando.martelandoapp.enums.OfferStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SaveOfferRequest (
    @NotNull
    @JsonProperty("bidder_id")
    Long bidderId,
    @NotNull
    @JsonProperty("product_id")
    Long productId,
    @NotNull
    @JsonProperty("amount")
    BigDecimal amount,
    @NotNull
    @JsonProperty("status")
    OfferStatus status
) {}
