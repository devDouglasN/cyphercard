package com.douglas.mscards.application;

import com.douglas.mscards.application.representation.CardSaveRequest;
import com.douglas.mscards.application.representation.CardsPerCustomerResponse;
import com.douglas.mscards.application.representation.CustomerCardService;
import com.douglas.mscards.domain.Card;
import com.douglas.mscards.domain.CustomerCard;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
public class CardResource {

    @Autowired
    private CardService service;

    @Autowired
    private CustomerCardService customerCardService;

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

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsPerCustomerResponse>> getCardsByCustomer(@RequestParam("cpf") String cpf){
        List<CustomerCard> list = customerCardService.listCardsByCpf(cpf);
        List<CardsPerCustomerResponse> resultList = list.stream()
                .map(CardsPerCustomerResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
