package com.martelando.martelandoapp.sevice;


import com.martelando.martelandoapp.controllers.request.SaveOfferRequest;
import com.martelando.martelandoapp.controllers.request.UpdateOfferRequest;
import com.martelando.martelandoapp.controllers.responses.OfferDetailResponse;

import java.util.List;

public interface IOfferService {
    OfferDetailResponse create(final SaveOfferRequest saveOfferRequest);

    void delete(final Long id);

    OfferDetailResponse update(final UpdateOfferRequest updateOfferRequest);

    List<OfferDetailResponse> findAllByProductId(final Long productId);
}
