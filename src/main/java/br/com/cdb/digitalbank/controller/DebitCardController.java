package br.com.cdb.digitalbank.controller;


import br.com.cdb.digitalbank.dto.CardDTO;
import br.com.cdb.digitalbank.model.DebitCard;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/debit")
public class DebitCardController {

    @Autowired
    private DebitCardService debitCardService;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<DebitCard> save(@RequestBody CardDTO dto) {
        return ResponseEntity.ok(debitCardService.save(accountService.findById(dto.accountId()), dto.password()));
    }

    @PostMapping("/disable/{id}")
    public void disable(@PathVariable Long id) {
        debitCardService.disable(id);
    }
}
