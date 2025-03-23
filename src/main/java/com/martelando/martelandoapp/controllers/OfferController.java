package com.martelando.martelandoapp.controllers;

import com.martelando.martelandoapp.controllers.request.SaveOfferRequest;
import com.martelando.martelandoapp.controllers.request.UpdateOfferRequest;
import com.martelando.martelandoapp.controllers.responses.OfferDetailResponse;
import com.martelando.martelandoapp.sevice.IOfferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
@AllArgsConstructor
public class OfferController {

    private IOfferService offerService;

    @PostMapping()
    ResponseEntity<OfferDetailResponse> create(@Valid @RequestBody SaveOfferRequest saveOfferRequest) {
        var offer = this.offerService.create(saveOfferRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        this.offerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    ResponseEntity<OfferDetailResponse> update(@RequestBody UpdateOfferRequest updateOfferRequest) {
        var offer = this.offerService.update(updateOfferRequest);
        return ResponseEntity.ok().body(offer);
    }

    @GetMapping()
    ResponseEntity<List<OfferDetailResponse>> findByProductId(@RequestParam Long productId){
        var offers = this.offerService.findAllByProductId(productId);
        return ResponseEntity.ok().body(offers);
    }


}
