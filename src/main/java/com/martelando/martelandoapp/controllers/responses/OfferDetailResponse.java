package com.martelando.martelandoapp.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martelando.martelandoapp.enums.OfferStatus;

import java.math.BigDecimal;

public record OfferDetailResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("product")
        ProductDetailResponse product,
        @JsonProperty("bidder")
        UserDetailResponse bidder,
        @JsonProperty("amount")
        BigDecimal amount,
        @JsonProperty("status")
        OfferStatus status
) {}
