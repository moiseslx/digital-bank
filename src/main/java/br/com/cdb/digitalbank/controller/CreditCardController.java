package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.CardDTO;
import br.com.cdb.digitalbank.dto.ChangePasswordDTO;
import br.com.cdb.digitalbank.model.CreditCard;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/credit")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<CreditCard> save(@RequestBody CardDTO dto) {
        return ResponseEntity.ok(creditCardService.save(accountService.findById(dto.accountId()), dto.password()));
    }

    @PostMapping("/disable/{id}")
    public void disable(@PathVariable Long id) {
        creditCardService.disable(id);
    }

    @PostMapping("/change-password/{id}")
    public void changePassword(@PathVariable Long id, @RequestBody ChangePasswordDTO dto) {
        creditCardService.changePassword(id, dto.password(), dto.newPassword());
    }

    @PostMapping("/daily-limit/{id}")
    public ResponseEntity<CreditCard> updateDailyLimit(@PathVariable Long id, @RequestBody BigDecimal dailyLimit) {
        return ResponseEntity.ok(creditCardService.updateDailyLimit(id, dailyLimit));
    }

    //TODO: Retornar o valor atual do limite de crédito
    @GetMapping("/limit/{id}")
    public BigDecimal getDailyLimit(@PathVariable Long id) {
        return creditCardService.getLimit(id);
    }
}
