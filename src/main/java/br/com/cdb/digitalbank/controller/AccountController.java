package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.AccountDTO;
import br.com.cdb.digitalbank.dto.CustomerDTO;
import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Account> create(@RequestBody AccountDTO account) {
        Account acc = account.toAccount(customerService.findById(account.customerId()));
        return ResponseEntity.ok(accountService.createAccount(acc));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getBalance(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }


    // TODO: Implementar o mecanismo de transferência dos tipos pix, cartão de credito, eletrônico, etc.
}
