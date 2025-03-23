package com.martelando.martelandoapp.dto;

import com.martelando.martelandoapp.enums.OfferStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OfferDTO {
    private Long id;
    private UserDTO bidder;
    private ProductDTO product;
    private BigDecimal amount;
    private OfferStatus status;
}
