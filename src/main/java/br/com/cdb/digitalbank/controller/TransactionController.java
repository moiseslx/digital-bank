package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.TransactionCreditDTO;
import br.com.cdb.digitalbank.dto.TransactionDebitDTO;
import br.com.cdb.digitalbank.dto.TransactionPixDTO;
import br.com.cdb.digitalbank.model.DebitCard;
import br.com.cdb.digitalbank.model.Transaction;
import br.com.cdb.digitalbank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    // TODO: Implementar o mecanismo de transferência dos tipos pix, cartão de credito, eletrônico, etc.

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PixService pixService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CreditCardService cardService;

    @Autowired
    private DebitCardService debitCardService;

    @PostMapping("/pix")
    public ResponseEntity<Transaction> pix(@RequestBody TransactionPixDTO transactionDTO) {
        return ResponseEntity.ok(transactionService
                .execute(accountService.findById(transactionDTO.originId()),
                         pixService.findByPixKey(transactionDTO.destinationKey()),
                        transactionDTO.amount()));
    }

    @PostMapping("/debit")
    public ResponseEntity<Transaction> debit(@RequestBody TransactionDebitDTO transactionDTO) {
        return ResponseEntity.ok(transactionService
                .execute(debitCardService.findById(transactionDTO.debitCardId()),
                        accountService.findById(transactionDTO.accountId()),
                        transactionDTO.amount()));
    }
}
