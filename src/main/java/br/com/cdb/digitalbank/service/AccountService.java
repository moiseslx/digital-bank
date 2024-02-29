package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.enums.AccountType;
import br.com.cdb.digitalbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        Long accountNumber = generateAccountNumber(accountRepository.findAll());
        Integer agency = generateAgency();

        account.setNumber(accountNumber);
        account.setAgency(agency);
        return accountRepository.save(account);
    }


    private Long generateAccountNumber(List<Account> accounts) {
        Random random = new Random();
        Long number = Math.abs(random.nextLong() % 100000000L); // Garante que o número tenha 8 dígitos

        boolean exists = false;
        for (Account account : accounts) {
            if (account.getNumber().equals(number)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            return generateAccountNumber(accounts);
        }

        return number;
    }


    private Integer generateAgency() {
        return Math.abs(new Random().nextInt() % 10000);
    }
}
