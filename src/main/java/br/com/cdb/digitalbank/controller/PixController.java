package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.PixDTO;
import br.com.cdb.digitalbank.model.Pix;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Pix> save(@RequestBody PixDTO pixDTO) {
        Pix obj = new Pix();
        obj.setType(pixDTO.type());
        obj.setAccount(accountService.findById(pixDTO.accountId()));
        return ResponseEntity.ok(pixService.save(obj));
    }
}
