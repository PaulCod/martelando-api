package com.martelando.martelandoapp.mapper;

import com.martelando.martelandoapp.controllers.request.SaveProductRequest;
import com.martelando.martelandoapp.controllers.request.UpdateProductRequest;
import com.martelando.martelandoapp.controllers.responses.ProductDetailResponse;
import com.martelando.martelandoapp.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "ownerId")
    ProductEntity toEntity(final SaveProductRequest saveProductRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "owner", ignore = true)
    ProductEntity toEntity(final Long id, UpdateProductRequest updateProductRequest);

    ProductDetailResponse toResponse(final ProductEntity productEntity);

}