package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.CardDTO;
import br.com.cdb.digitalbank.model.Card;
import br.com.cdb.digitalbank.model.CreditCard;
import br.com.cdb.digitalbank.model.enums.CardType;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/save")
    public ResponseEntity<Card> save(@RequestBody CardDTO CardDTO) {
        return ResponseEntity
                .ok(cardService.save(accountService
                .findById(CardDTO.accountId()), CardDTO.cardType(), CardDTO.password()));
    }
}
