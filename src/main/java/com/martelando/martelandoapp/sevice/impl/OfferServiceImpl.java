package com.martelando.martelandoapp.sevice.impl;

import com.martelando.martelandoapp.controllers.request.SaveOfferRequest;
import com.martelando.martelandoapp.controllers.request.UpdateOfferRequest;
import com.martelando.martelandoapp.controllers.responses.OfferDetailResponse;
import com.martelando.martelandoapp.exception.NotFoundException;
import com.martelando.martelandoapp.exception.UnauthorizedOfferUpdate;
import com.martelando.martelandoapp.exception.UserAlreadyMakeOfferException;
import com.martelando.martelandoapp.mapper.IOfferMapper;
import com.martelando.martelandoapp.repository.IOfferRepository;
import com.martelando.martelandoapp.repository.IUserRepository;
import com.martelando.martelandoapp.sevice.IOfferService;
import com.martelando.martelandoapp.sevice.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements IOfferService {

    private final IOfferRepository offerRepository;
    private final IOfferMapper offerMapper;
    private final IUserRepository userRepository;


    @Override
    public OfferDetailResponse create(Long userId, SaveOfferRequest saveOfferRequest) {
        var offer = this.offerRepository.findByBidderIdAndProductId(saveOfferRequest.bidderId(), saveOfferRequest.productId());

        if(!userRepository.existsById(userId)) {
            throw new NotFoundException("Usuario não existe");
        }

        if(offer.isPresent()) {
            throw new UserAlreadyMakeOfferException("Usuario já fez uma oferta para esse produto");
        }


        var offerSaved = this.offerRepository.save(
                this.offerMapper.toEntity(saveOfferRequest)
        );

        return this.offerMapper.toResponse(offerSaved);
    }

    @Override
    public void delete(Long userId, Long id) {
        var offer = this.offerRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Oferta não existe"));

        if(!offer.getBidder().getId().equals(userId)) {
            throw new UnauthorizedOfferUpdate("Usuario não autorizado a deletar oferta");
        }

        this.offerRepository.delete(offer);
    }

    @Override
    public OfferDetailResponse update(Long userId,UpdateOfferRequest updateOfferRequest) {
        var offer = this.offerRepository.findById(updateOfferRequest.id())
                .orElseThrow(() -> new NotFoundException("Oferta não existe"));

        if(!offer.getBidder().getId().equals(userId)) {
            throw new UnauthorizedOfferUpdate("Usuario não autorizado a deletar oferta");
        }

        offer.setStatus(updateOfferRequest.status());
        offer.setAmount(updateOfferRequest.amount());

        var offerUpdated = this.offerRepository.save(offer);

        return this.offerMapper.toResponse(offerUpdated);
    }

    @Override
    public List<OfferDetailResponse> findAllByProductId(Long productId) {
        var offers = this.offerRepository.findByProductId(productId);

        return offers.stream().map(offerMapper::toResponse).toList();
    }
}
