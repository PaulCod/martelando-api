package com.martelando.martelandoapp.repository;

import com.martelando.martelandoapp.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfferRepository extends JpaRepository<OfferEntity, Long> {
}
