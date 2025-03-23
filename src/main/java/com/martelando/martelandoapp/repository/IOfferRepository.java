package com.martelando.martelandoapp.repository;

import com.martelando.martelandoapp.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOfferRepository extends JpaRepository<OfferEntity, Long> {
    Optional<OfferEntity> findByBidderIdAndProductId(final Long bidderId, final Long productId);

    List<OfferEntity> findByProductId(final Long productId);
}
