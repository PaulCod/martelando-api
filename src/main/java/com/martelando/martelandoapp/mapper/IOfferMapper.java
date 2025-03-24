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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "productId")
    @Mapping(target = "bidder", source = "bidderId")
    OfferEntity toEntity(SaveOfferRequest saveOfferRequest);

    @Mapping(target = "bidder", source = "bidder.id")
    OfferDetailResponse toResponse(OfferEntity offerEntity);

    default UserEntity mapUser(Long userId) {
        if (userId == null) {
            return null;
        }
        var user = new UserEntity();
        user.setId(userId);
        return user;
    }


    default ProductEntity mapProduct(Long productId) {
        if (productId == null) {
            return null;
        }
        var product = new ProductEntity();
        product.setId(productId);
        return product;
    }

    default UserDetailResponse mapUserDetailResponse(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDetailResponse(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getPhone()
        );
    }
}
