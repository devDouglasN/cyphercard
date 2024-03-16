package com.douglas.mscards.application;

import com.douglas.mscards.CardRepository;
import com.douglas.mscards.application.representation.CardSaveRequest;
import com.douglas.mscards.domain.Card;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
public class CardResource {

    @Autowired
    private CardService service;

    @Transactional
    public Card save(Card card){
        return service.save(card);
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request){
        Card card = request.toModel();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> listCardsbyIncome (@RequestParam("income") Long income){
        List<Card> list = service.getEqualMinorIncomeCards(income);
        return ResponseEntity.ok(list);
    }
}
