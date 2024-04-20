package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.AccountDTO;
import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Account> save(@Valid @RequestBody AccountDTO accountDTO) {
        var customerToSave = customerService.save(accountDTO.toCustomer());
        var account = accountService.createAccount(new Account(accountDTO.accountType(), customerToSave));
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getBalance(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }
}
