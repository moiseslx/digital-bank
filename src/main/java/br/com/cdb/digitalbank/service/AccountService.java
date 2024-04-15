package br.com.cdb.digitalbank.service;

import br.com.cdb.digitalbank.model.Account;
import br.com.cdb.digitalbank.model.enums.AccountType;
import br.com.cdb.digitalbank.repository.AccountRepository;
import br.com.cdb.digitalbank.service.exceptions.DuplicateDataException;
import br.com.cdb.digitalbank.service.exceptions.EntityNotFoundException;
import br.com.cdb.digitalbank.service.util.GenerateNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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

        account.setNumber(GenerateNumber.generateAccountNumber());
        account.setAgency(GenerateNumber.generateAgencyNumber());
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

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findByAccountsCompleteAnotherMonth() {
        return accountRepository.findByAccountsCompleteAnotherMonth();
    }
}
