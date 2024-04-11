package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.repository.AccountRepository;
import br.com.cdb.digitalbank.service.exceptions.DuplicateDataException;
import br.com.cdb.digitalbank.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (accountRepository.findByCustomerId(account.getCustomer().getId()) != null) {
            throw new DuplicateDataException("Conta existente para o cliente: " + account.getCustomer().getId());
        }

        account.setNumber(generateNumber());
        account.setAgency(generateAgency());
        return accountRepository.save(account);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Conta não encontrada com id: " + id));
    }

    public BigDecimal getBalance(Long id) {
        Account account = findById(id);
        return account.getBalance();
    }

    private Long generateNumber() {
        Random random = new Random();
        Long number = Math.abs(random.nextLong() % 100000000L);

        if (accountRepository.findByNumber(number) != null) {
            return generateNumber();
        }

        return number;
    }

    private Integer generateAgency() {
        Random random = new Random();
        return Math.toIntExact(Math.abs(random.nextLong() % 10000L));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
