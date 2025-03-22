package com.martelando.martelandoapp.sevice.impl;

import com.martelando.martelandoapp.dto.ProductDTO;
import com.martelando.martelandoapp.mapper.IProductMapper;
import com.martelando.martelandoapp.repository.IProductRepository;
import com.martelando.martelandoapp.sevice.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private IProductRepository productRepository;
    private IProductMapper productMapper;

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        var product = this.productMapper.productDTOToProductEntity(productDTO);

        var productCreate = this.productRepository.save(product);

        return this.productMapper.productEntityToProductDTO(productCreate);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        var product = this.productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não existe"));

        product.setImageUrl(productDTO.getImageUrl());
        product.setDescription(productDTO.getDescription());
        product.setInitialOffer(productDTO.getInitialOffer());
        product.setTitle(productDTO.getTitle());
        product.setEndAt(productDTO.getEndAt());

        var productSaved = this.productRepository.save(product);

        return this.productMapper.productEntityToProductDTO(productSaved);
    }

    @Override
    public void delete(Long id) {
        var product = this.productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não existe"));

        this.productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        var products = this.productRepository.findAll();

        return products.stream().
                map(productMapper::productEntityToProductDTO)
                .toList();
    }

    @Override
    public ProductDTO findById(Long id) {
        var product = this.productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        return this.productMapper.productEntityToProductDTO(product);
    }

    @Override
    public List<ProductDTO> findByTitle(String title) {
        var products = this.productRepository.findByTitleContaining(title);

        return products.stream()
                .map(productMapper::productEntityToProductDTO)
                .toList();
    }
}
