package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.enums.AccountType;
import br.com.cdb.digitalbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private final int AGENCY = 123456;

    public Account createAccount(Account account) {
        account.setAgency(String.valueOf(AGENCY));

        // TODO: Implementar o gerador de números de conta

        return accountRepository.save(account);
    }
}
