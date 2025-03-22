package com.martelando.martelandoapp.dto;

import com.martelando.martelandoapp.entity.ProductEntity;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private Set<ProductDTO> products;
}
