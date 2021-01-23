package com.orbitallcorp.hack21.cards.controllers;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card customers){
        Card savedCard = cardService.save((customers));
        return new ResponseEntity(savedCard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Card>> findAll(){
        List<Card> cards = cardService.findAll();
        return new ResponseEntity(cards, HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return cardService.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return cardService.findById(id)
                .map(record -> {
                    cardService.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Card card) {
        return cardService.findById(id)
                .map(record -> {
                    record.setAddress(card.getAddress());
                    record.setCardNumber(card.getCardNumber());
                    record.setCity(card.getCity());
                    record.setCustomerName(card.getCustomerName());
                    record.setDocumentNumber(card.getDocumentNumber());
                    record.setEmbossName(card.getEmbossName());
                    record.setMotherName(card.getMotherName());
                    Card updated = cardService.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"/count"})
    public ResponseEntity count(){
        long count = cardService.count();
        return new ResponseEntity(count, HttpStatus.OK);
    }

    @GetMapping(path = {"/paginationAndSorting"})
    public ResponseEntity<List<Card>> getAllCards(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam(defaultValue = "customerName") String sortBy)
    {
        List<Card> list = cardService.getAllCards(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Card>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
