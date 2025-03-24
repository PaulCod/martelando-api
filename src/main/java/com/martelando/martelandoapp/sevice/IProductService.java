package com.martelando.martelandoapp.sevice;

import com.martelando.martelandoapp.controllers.request.SaveProductRequest;
import com.martelando.martelandoapp.controllers.request.UpdateProductRequest;
import com.martelando.martelandoapp.controllers.responses.ProductDetailResponse;

import java.util.List;

public interface IProductService {
    ProductDetailResponse create(final Long ownerID, final SaveProductRequest saveProductRequest);

    ProductDetailResponse update(final Long ownerId, final UpdateProductRequest updateProductRequest);

    void delete(final Long ownerId, final Long id);

    List<ProductDetailResponse> getAllProducts();

    ProductDetailResponse findById(final Long id);

    List<ProductDetailResponse> findByTitle(final String title);
}
