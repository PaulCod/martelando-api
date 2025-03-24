package com.martelando.martelandoapp.sevice;


import com.martelando.martelandoapp.controllers.request.SaveOfferRequest;
import com.martelando.martelandoapp.controllers.request.UpdateOfferRequest;
import com.martelando.martelandoapp.controllers.responses.OfferDetailResponse;

import java.util.List;

public interface IOfferService {
    OfferDetailResponse create(final Long userId, final SaveOfferRequest saveOfferRequest);

    void delete(final Long userId, final Long id);

    OfferDetailResponse update(final Long userId, final UpdateOfferRequest updateOfferRequest);

    List<OfferDetailResponse> findAllByProductId(final Long productId);
}
