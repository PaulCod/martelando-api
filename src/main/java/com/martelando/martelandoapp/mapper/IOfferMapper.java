package com.martelando.martelandoapp.mapper;

import com.martelando.martelandoapp.controllers.request.SaveOfferRequest;
import com.martelando.martelandoapp.controllers.responses.OfferDetailResponse;
import com.martelando.martelandoapp.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IOfferMapper {

    IOfferMapper INSTANCE = Mappers.getMapper(IOfferMapper.class);

    OfferEntity toEntity(SaveOfferRequest saveOfferRequest);

    OfferDetailResponse toResponse(OfferEntity offerEntity);
}
