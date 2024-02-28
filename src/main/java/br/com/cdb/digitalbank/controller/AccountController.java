package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.AccountDTO;
import br.com.cdb.digitalbank.dto.CustomerDTO;
import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO: Implementar o mecanismo de transferência dos tipos pix, cartão de credito, eletrônico, etc.
}
