package com.douglas.mscards.application;

import com.douglas.mscards.CardRepository;
import com.douglas.mscards.domain.Card;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class CardResource {

    @Autowired
    private CardService service;

    @Transactional
    public Card save(Card card){
        return service.save(card);
    }
}
