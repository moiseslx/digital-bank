package br.com.cdb.digitalbank.service.authentication;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.service.AccountService;
import br.com.cdb.digitalbank.service.exceptions.IncorrectPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String login, String password) {
        Account account = accountService.findByCpf(login);
        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new IncorrectPasswordException("Senha inválida");
        }
        return jwtService.encode(account);
    }
}
