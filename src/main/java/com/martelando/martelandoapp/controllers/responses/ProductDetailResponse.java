package com.martelando.martelandoapp.controllers.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martelando.martelandoapp.enums.ProductStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ProductDetailResponse(
    @JsonProperty("id")
    Long id,
    @JsonProperty("image_url")
    String imageUrl,
    @JsonProperty("initial_offer")
    BigDecimal initialOffer,
    @JsonProperty("title")
    String title,
    @JsonProperty("description")
    String description,
    @JsonProperty("status")
    ProductStatus status,
    @JsonProperty("start_at")
    Timestamp startAt,
    @JsonProperty("end_at")
    Timestamp endAt,
    @JsonProperty("owner")
    UserDetailResponse owner
) {}
