package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.martelando.martelandoapp.enums.ProductStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record SaveProductRequest (
    @NotNull
    @JsonProperty("owner_id")
    Long ownerId,

    @NotNull
    @Pattern(regexp = "^https?://.*$", message = "Image URL deve começar com 'http' ou 'https'")
    @JsonProperty("image_url")
    String imageUrl,

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "Oferta inicial deve ser maior que zero")
    @JsonProperty("initial_offer")
    BigDecimal initialOffer,

    @NotNull
    @Size(min = 5, max = 250, message = "Titulo deve ter entre 5 e 250 caracteres")
    @JsonProperty("title")
    String title,

    @NotNull
    @Size(min = 10, max = 1000, message = "Descrição deve ter entre 10 e 1000 caracteres")
    @JsonProperty("description")
    String description,

    @NotNull
    @JsonProperty("status")
    ProductStatus status,

    @NotNull
    @Future(message = "A data de inicio deve estar no futuro")
    @JsonProperty("start_at")
    Timestamp startAt,

    @NotNull
    @Future(message = "A data de fim deve estar no futuro")
    @JsonProperty("end_at")
    Timestamp endAt
) {
    public boolean isValidStartEndDates() {
        return startAt.before(endAt);
    }
}
