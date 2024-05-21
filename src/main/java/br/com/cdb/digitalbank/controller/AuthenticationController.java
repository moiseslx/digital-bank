package br.com.cdb.digitalbank.controller;

import br.com.cdb.digitalbank.dto.AuthenticationDTO;
import br.com.cdb.digitalbank.dto.TokenDTO;
import br.com.cdb.digitalbank.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<TokenDTO> get(@RequestBody AuthenticationDTO dto) {

        var token = authenticationService.login(dto.login(), dto.password());
        if (token != null) {
            return ResponseEntity.ok(new TokenDTO(token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
