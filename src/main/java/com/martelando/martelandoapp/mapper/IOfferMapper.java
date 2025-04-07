package com.martelando.martelandoapp.mapper;

import com.martelando.martelandoapp.controllers.request.SaveOfferRequest;
import com.martelando.martelandoapp.controllers.responses.OfferDetailResponse;
import com.martelando.martelandoapp.controllers.responses.UserDetailResponse;
import com.martelando.martelandoapp.entity.OfferEntity;
import com.martelando.martelandoapp.entity.ProductEntity;
import com.martelando.martelandoapp.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IOfferMapper {

    IOfferMapper INSTANCE = Mappers.getMapper(IOfferMapper.class);


    @Mapping(target = "id", ignore = true) // Ignora o id durante o mapeamento
    @Mapping(target = "bidder", source = "bidder")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "amount", source = "saveOfferRequest.amount")
    @Mapping(target = "status", source = "saveOfferRequest.status")
    OfferEntity toEntity(SaveOfferRequest saveOfferRequest, UserEntity bidder,ProductEntity product);

    OfferDetailResponse toResponse(OfferEntity offerEntity);
}
