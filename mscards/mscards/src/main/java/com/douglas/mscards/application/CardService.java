package com.douglas.mscards.application;

import com.douglas.mscards.CardRepository;
import com.douglas.mscards.domain.Card;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    @Autowired
    private CardRepository repository;

    @Transactional
    public Card save(Card card){
        return repository.save(card);
    }
}
