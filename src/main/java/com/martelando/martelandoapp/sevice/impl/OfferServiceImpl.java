package com.martelando.martelandoapp.sevice.impl;

import com.martelando.martelandoapp.dto.OfferDTO;
import com.martelando.martelandoapp.mapper.IOfferMapper;
import com.martelando.martelandoapp.repository.IOfferRepository;
import com.martelando.martelandoapp.sevice.IOfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements IOfferService {

    private final IOfferRepository offerRepository;
    private final IOfferMapper offerMapper;

    @Override
    public OfferDTO create(OfferDTO offerDTO) {
        var offer = this.offerRepository.findByBidderIdAndProductId(offerDTO.getBidder().getId(), offerDTO.getProduct().getId());

        if(offer.isPresent()) {
            throw new IllegalArgumentException("Usuario já fez uma oferta para esse produto");
        }

        var offerSaved = this.offerRepository.save(
                this.offerMapper.offerDTOToOfferEntity(offerDTO)
        );

        return this.offerMapper.offerEntityToOfferDTO(offerSaved);
    }

    @Override
    public void delete(Long id) {
        var offer = this.offerRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException("Oferta não existe"));

        this.offerRepository.delete(offer);
    }

    @Override
    public OfferDTO update(OfferDTO offerDTO) {
        var offer = this.offerRepository.findById(offerDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Oferta não existe"));

        offer.setStatus(offerDTO.getStatus());
        offer.setAmount(offerDTO.getAmount());

        var offerUpdated = this.offerRepository.save(offer);

        return this.offerMapper.offerEntityToOfferDTO(offerUpdated);
    }

    @Override
    public List<OfferDTO> findAllByProductId(Long productId) {
        var offers = this.offerRepository.findByProductId(productId);

        return offers.stream().map(offerMapper::offerEntityToOfferDTO).toList();
    }
}
