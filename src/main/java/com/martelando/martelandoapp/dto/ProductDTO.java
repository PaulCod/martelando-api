package com.martelando.martelandoapp.dto;

import com.martelando.martelandoapp.enums.ProductStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ProductDTO {
    private Long id;
    private UserDTO owner;
    private String imageUrl;
    private BigDecimal initialOffer;
    private String title;
    private String description;
    private ProductStatus status;
    private Timestamp startAt;
    private Timestamp endAt;
}
