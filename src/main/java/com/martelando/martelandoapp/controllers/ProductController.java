package com.martelando.martelandoapp.controllers;

import com.martelando.martelandoapp.controllers.request.FindProductByText;
import com.martelando.martelandoapp.controllers.request.SaveProductRequest;
import com.martelando.martelandoapp.controllers.request.UpdateProductRequest;
import com.martelando.martelandoapp.controllers.responses.ProductDetailResponse;
import com.martelando.martelandoapp.sevice.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
public class ProductController{

    private IProductService productService;

    @PostMapping()
    ResponseEntity<ProductDetailResponse> create(@Valid @RequestBody SaveProductRequest saveProductRequest) {
        var product = this.productService.create(saveProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    ResponseEntity<ProductDetailResponse> update(@RequestBody UpdateProductRequest updateProductRequest) {
        var updateProduct = this.productService.update(updateProductRequest);
        return ResponseEntity.ok().body(updateProduct);
    }

    @GetMapping()
    ResponseEntity<List<ProductDetailResponse>> getAllProducts() {
        var products = this.productService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductDetailResponse> findById(@PathVariable Long id) {
        var product = this.productService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/search")
    ResponseEntity<List<ProductDetailResponse>> findByText(@RequestParam FindProductByText findProductByText) {
        var product = this.productService.findByTitle(findProductByText.title());
        return ResponseEntity.ok().body(product);
    }

}
