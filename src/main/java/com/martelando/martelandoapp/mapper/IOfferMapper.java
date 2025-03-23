package com.martelando.martelandoapp.mapper;

import com.martelando.martelandoapp.dto.OfferDTO;
import com.martelando.martelandoapp.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IOfferMapper {

    IOfferMapper INSTANCE = Mappers.getMapper(IOfferMapper.class);

    OfferDTO offerEntityToOfferDTO(OfferEntity offerEntity);

    OfferEntity offerDTOToOfferEntity(OfferDTO offerDTO);
}
