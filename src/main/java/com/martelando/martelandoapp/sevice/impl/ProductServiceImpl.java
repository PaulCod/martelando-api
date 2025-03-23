package com.martelando.martelandoapp.sevice.impl;

import com.martelando.martelandoapp.controllers.request.SaveProductRequest;
import com.martelando.martelandoapp.controllers.request.UpdateProductRequest;
import com.martelando.martelandoapp.controllers.responses.ProductDetailResponse;
import com.martelando.martelandoapp.exception.NotFoundException;
import com.martelando.martelandoapp.mapper.IProductMapper;
import com.martelando.martelandoapp.repository.IProductRepository;
import com.martelando.martelandoapp.repository.IUserRepository;
import com.martelando.martelandoapp.sevice.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private IProductRepository productRepository;
    private IUserRepository userRepository;
    private IProductMapper productMapper;

    @Override
    public ProductDetailResponse create(SaveProductRequest saveProductRequest) {
        var owner = this.userRepository.findById(saveProductRequest.ownerId())
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));

        var product = this.productMapper.toEntity(saveProductRequest);

        product.setOwner(owner);

        var productCreate = this.productRepository.save(product);

        return this.productMapper.toResponse(productCreate);
    }

    @Override
    public ProductDetailResponse update(UpdateProductRequest updateProductRequest) {
        var product = this.productRepository.findById(updateProductRequest.id())
                .orElseThrow(() -> new NotFoundException("Produto não existe"));

        product.setDescription(updateProductRequest.description());
        product.setTitle(updateProductRequest.title());
        product.setEndAt(updateProductRequest.endAt());

        var productSaved = this.productRepository.save(product);

        return this.productMapper.toResponse(productSaved);
    }

    @Override
    public void delete(Long id) {
        var product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não existe"));

        this.productRepository.delete(product);
    }

    @Override
    public List<ProductDetailResponse> getAllProducts() {
        var products = this.productRepository.findAll();

        return products.stream().
                map(productMapper::toResponse)
                .toList();
    }

    @Override
    public ProductDetailResponse findById(Long id) {
        var product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        return this.productMapper.toResponse(product);
    }

    @Override
    public List<ProductDetailResponse> findByTitle(String title) {
        var products = this.productRepository.findByTitleContaining(title);

        return products.stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
