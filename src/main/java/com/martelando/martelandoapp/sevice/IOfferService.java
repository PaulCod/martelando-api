package com.martelando.martelandoapp.sevice;


import com.martelando.martelandoapp.dto.OfferDTO;

import java.util.List;

public interface IOfferService {
    OfferDTO create(final OfferDTO offerDTO);

    void delete(final Long id);

    OfferDTO update(final OfferDTO offerDTO);

    List<OfferDTO> findAllByProductId(final Long productId);
}
