package com.martelando.martelandoapp.sevice;

import com.martelando.martelandoapp.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    ProductDTO create(final ProductDTO productDTO);

    ProductDTO update(final ProductDTO productDTO);

    void delete(final Long id);

    List<ProductDTO> getAllProducts();

    ProductDTO findById(final Long id);

    List<ProductDTO> findByTitle(final String title);
}
