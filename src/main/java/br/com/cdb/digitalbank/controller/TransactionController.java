package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.TransactionCreditDTO;
import br.com.cdb.digitalbank.dto.TransactionDebitDTO;
import br.com.cdb.digitalbank.dto.TransactionPixDTO;
import br.com.cdb.digitalbank.model.Transaction;
import br.com.cdb.digitalbank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

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
                .execute(accountService.findById(transactionDTO.originId()), // Origem retornada por conta
                         pixService.findByPixKey(transactionDTO.destinationKey()), // Destino retornada por chave pix
                        transactionDTO.amount())); // Valor da transação
    }

    @PostMapping("/debit")
    public ResponseEntity<Transaction> debit(@RequestBody TransactionDebitDTO transactionDTO) {
        return ResponseEntity.ok(transactionService
                .execute(debitCardService.findById(transactionDTO.debitCardId()), // Origem retornada por cartão de débito
                        accountService.findById(transactionDTO.accountId()), // Destino retornada por conta
                        transactionDTO.amount(), // Valor da transação + taxa inclusa pelo serviço
                        transactionDTO.password())); // Senha do cartão
    }

    @PostMapping("/credit")
    public ResponseEntity<Transaction> credit(@RequestBody TransactionCreditDTO transactionDTO) {
        return ResponseEntity.ok(transactionService
                .execute(cardService.findById(transactionDTO.creditCardId()), // Origem retornada por cartão de crédito
                        accountService.findById(transactionDTO.accountId()), // Destino retornada por conta
                        transactionDTO.amount(), // Valor da transação
                        transactionDTO.password())); // Senha do cartão
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Transaction>> all(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.findAllByAccountId(accountService.findById(id)));
    }
}
