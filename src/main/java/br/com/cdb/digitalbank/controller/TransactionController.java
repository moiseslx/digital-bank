package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.TransactionPixDTO;
import br.com.cdb.digitalbank.model.Transaction;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.PixService;
import br.com.cdb.digitalbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/pix")
    public ResponseEntity<Transaction> pix(TransactionPixDTO transactionDTO) {
        return ResponseEntity.ok(transactionService
                .execute(accountService.findById(transactionDTO.originId()),
                         pixService.findByPixKey(transactionDTO.destinationKey()), transactionDTO.amount()));
    }
}
