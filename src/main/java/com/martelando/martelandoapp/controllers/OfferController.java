package com.martelando.martelandoapp.controllers;

import com.martelando.martelandoapp.controllers.request.SaveOfferRequest;
import com.martelando.martelandoapp.controllers.request.UpdateOfferRequest;
import com.martelando.martelandoapp.controllers.responses.OfferDetailResponse;
import com.martelando.martelandoapp.sevice.IOfferService;
import com.martelando.martelandoapp.sevice.JwtService;
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
    private JwtService jwtService;

    @PostMapping()
    ResponseEntity<OfferDetailResponse> create(@RequestHeader("Authorization") String authorizationHeader,
                                               @Valid @RequestBody SaveOfferRequest saveOfferRequest) {
        Long userId = this.jwtService.extractUserIdFromToken(authorizationHeader);
        var offer = this.offerService.create(userId, saveOfferRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long id) {
        Long userId = this.jwtService.extractUserIdFromToken(authorizationHeader);
        this.offerService.delete(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    ResponseEntity<OfferDetailResponse> update(@RequestHeader("Authorization") String authorizationHeader,
                                               @Valid @RequestBody UpdateOfferRequest updateOfferRequest) {
        Long userId = this.jwtService.extractUserIdFromToken(authorizationHeader);
        var offer = this.offerService.update(userId, updateOfferRequest);
        return ResponseEntity.ok().body(offer);
    }

    @GetMapping()
    ResponseEntity<List<OfferDetailResponse>> findByProductId(@RequestParam Long productId){
        var offers = this.offerService.findAllByProductId(productId);
        return ResponseEntity.ok().body(offers);
    }
}
