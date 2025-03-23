package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martelando.martelandoapp.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public record UpdateProductRequest(
        @NotNull
        @JsonProperty("id")
        Long id,

        @Size(min = 5, max = 250)
        @JsonProperty("title")
        String title,

        @Size(min = 10, max = 500)
        @JsonProperty("description")
        String description,

        @NotNull
        @JsonProperty("status")
        ProductStatus status,

        @NotNull
        @JsonProperty("end_at")
        Timestamp endAt
) {}
