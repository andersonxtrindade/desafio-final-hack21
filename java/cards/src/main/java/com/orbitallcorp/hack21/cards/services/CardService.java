package com.orbitallcorp.hack21.cards.services;

import com.orbitallcorp.hack21.cards.domains.Card;
import com.orbitallcorp.hack21.cards.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;


    public Card save(Card customers){
        return cardRepository.save((customers));
    }

    public List<Card> findAll(){
        List<Card> customers = new ArrayList<>();
        cardRepository.findAll().forEach(customers :: add);
        return customers;
    }

    public Optional<Card> findById(Long id){
        return cardRepository.findById(id);
    }

    public long count(){
        long coun = cardRepository.count();
        return coun;
    }

    public void deleteById(Long id){
        cardRepository.deleteById(id);
    }

    public List<Card> getAllCards(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Card> pagedResult = cardRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Card>();
        }
    }
}
