package com.martelando.martelandoapp.mapper;

import com.martelando.martelandoapp.dto.ProductDTO;
import com.martelando.martelandoapp.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    ProductDTO productEntityToProductDTO(ProductEntity productEntity);

    ProductEntity productDTOToProductEntity(ProductDTO productDTO);

}
